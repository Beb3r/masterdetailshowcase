package com.gromo.masterdetailshowcase.libraries.navigation.api_impl

import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationCommand
import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsScreenKey
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsScreenKey
import com.gromo.masterdetailshowcase.features.home.navigation.HomeNavigation
import org.koin.core.annotation.Single

@Single(binds = [HomeNavigation::class])
class HomeNavigationImpl(
    private val navigationManager: NavigationManager
) : HomeNavigation {

    override fun navigateToCharacterDetails(id: Int) {
        navigationManager.navigate(
            command = NavigationCommand.NavigateToKey(
                key = CharacterDetailsScreenKey(id = id),
                // can be useful to keep only one instance of detail screen in the back stack (when multiple panels)
                // but the composable is recreated each time we navigate to it
                /*withOperations = { backStack ->
                    backStack.removeIf { it is CharacterDetailsScreenKey }
                }*/
            )
        )
    }

    override fun navigateToEpisodeDetails(id: Int) {
        navigationManager.navigate(
            command = NavigationCommand.NavigateToKey(
                key = EpisodeDetailsScreenKey(id = id)
            )
        )
    }
}
