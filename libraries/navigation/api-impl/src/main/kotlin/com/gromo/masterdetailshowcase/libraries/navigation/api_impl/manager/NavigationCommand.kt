package com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gromo.masterdetailshowcase.libraries.navigation.api.NavigationKey

sealed interface NavigationCommand {

    data object NavigateUp : NavigationCommand

    data class NavigateToKey(
        val key: NavigationKey,
        val withOperations: ((SnapshotStateList<NavigationKey>) -> Unit)? = null
    ) : NavigationCommand

    data class PopUpToKey(
        val route: NavigationKey,
        val inclusive: Boolean,
        val fallBackRoute: NavigationKey,
    ) : NavigationCommand
}