package com.gromo.masterdetailshowcase.libraries.navigation.api_impl

import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsNavigation
import org.koin.core.annotation.Single

@Single(binds = [EpisodeDetailsNavigation::class])
class EpisodeDetailsNavigationImpl(
    private val navigationManager: NavigationManager
) : EpisodeDetailsNavigation {

    override fun navigateBack() {
        navigationManager.navigate(command = NavigationCommand.NavigateUp)
    }
}
