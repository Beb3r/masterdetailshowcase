package com.gromo.masterdetailshowcase.core.navigation.api_impl

import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsScreenRoute
import com.gromo.masterdetailshowcase.features.home.navigation.HomeNavigation

class HomeNavigationImpl(
    private val navigationManager: NavigationManager
) : HomeNavigation {

    override fun navigateToCharacterDetails(id: Int) {
        navigationManager.navigate(
            NavigationCommand.NavigateToRoute(
                route = CharacterDetailsScreenRoute(id = id)
            )
        )
    }
}
