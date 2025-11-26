package com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gromo.masterdetailshowcase.features.home.navigation.HomeScreenKey
import com.gromo.masterdetailshowcase.libraries.navigation.api.NavControllerAccessor
import com.gromo.masterdetailshowcase.libraries.navigation.api.NavigationKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Single
import timber.log.Timber

@Single(binds = [NavigationManager::class, NavControllerAccessor::class])
class NavigationManagerImpl() : NavigationManager, NavControllerAccessor {

    private val backStack : SnapshotStateList<NavigationKey> = mutableStateListOf(HomeScreenKey)

    override fun navigate(command: NavigationCommand) {
        backStack.handleNavigationCommand(command = command)
    }

    override fun getBackStack(): SnapshotStateList<NavigationKey> = backStack

    override fun goBack() {
        backStack.handleNavigationCommand(command = NavigationCommand.NavigateUp)
    }

    private fun SnapshotStateList<NavigationKey>.handleNavigationCommand(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.NavigateToKey -> {
                command.withOperations?.invoke(this)
                add(command.key)
            }

            NavigationCommand.NavigateUp -> {
                removeLastOrNull()
            }

            is NavigationCommand.PopUpToKey -> {
                val index = indexOfFirst { it == command.route }
                if (index != -1) {
                    val targetIndex = if (command.inclusive) index else index + 1
                    while (size > targetIndex) {
                        removeLastOrNull()
                    }
                } else {
                    while (size > 1) {
                        removeLastOrNull()
                    }
                    if (this.firstOrNull() != command.fallBackRoute) {
                        add(command.fallBackRoute)
                    }
                }
            }
        }
    }
}
