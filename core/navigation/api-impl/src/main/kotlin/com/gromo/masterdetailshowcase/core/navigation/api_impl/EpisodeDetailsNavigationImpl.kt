package com.gromo.masterdetailshowcase.core.navigation.api_impl

import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsNavigation

class EpisodeDetailsNavigationImpl(
    private val navigationManager: NavigationManager
) : EpisodeDetailsNavigation {

    override fun navigateBack() {
        navigationManager.navigate(command = NavigationCommand.NavigateUp)
    }
}
