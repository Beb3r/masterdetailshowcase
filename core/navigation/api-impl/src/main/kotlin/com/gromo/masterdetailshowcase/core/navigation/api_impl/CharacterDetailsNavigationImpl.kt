package com.gromo.masterdetailshowcase.core.navigation.api_impl

import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsNavigation

class CharacterDetailsNavigationImpl(
    private val navigationManager: NavigationManager
) : CharacterDetailsNavigation {

    override fun navigateBack() {
        navigationManager.navigate(NavigationCommand.NavigateUp)
    }
}
