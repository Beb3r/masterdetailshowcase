package com.gromo.masterdetailshowcase.libraries.navigation.api_impl

import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsNavigation
import com.gromo.masterdetailshowcase.features.home.navigation.HomeScreenKey
import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationManager
import org.koin.core.annotation.Single

@Single(binds = [CharacterDetailsNavigation::class])
class CharacterDetailsNavigationImpl(
    private val navigationManager: NavigationManager
) : CharacterDetailsNavigation {

    override fun navigateBack() {
        navigationManager.navigate(
            command = NavigationCommand.PopUpToKey(
                route = HomeScreenKey,
                inclusive = false,
                fallBackRoute = HomeScreenKey,
            )
        )
    }
}
