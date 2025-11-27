package com.gromo.masterdetailshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsScreenKey
import com.gromo.masterdetailshowcase.features.character_details.presentation.CharacterDetailsScreen
import com.gromo.masterdetailshowcase.features.character_details.presentation.CharacterDetailsViewModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.composables.CharacterDetailsUnselected
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsScreenKey
import com.gromo.masterdetailshowcase.features.episode_details.presentation.EpisodeDetailsScreen
import com.gromo.masterdetailshowcase.features.episode_details.presentation.EpisodeDetailsViewModel
import com.gromo.masterdetailshowcase.features.home.navigation.HomeScreenKey
import com.gromo.masterdetailshowcase.features.home.presentation.HomeScreen
import com.gromo.masterdetailshowcase.libraries.design.MasterDetailShowCaseTheme
import com.gromo.masterdetailshowcase.libraries.design.scenes.ListDetailSceneStrategyCustom
import com.gromo.masterdetailshowcase.libraries.design.scenes.LocalWithinScene
import com.gromo.masterdetailshowcase.libraries.design.scenes.rememberListDetailSceneStrategyCustom
import com.gromo.masterdetailshowcase.libraries.navigation.api.NavControllerAccessor
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TEEST onCreate")
        enableEdgeToEdge()
        setContent {
            MasterDetailShowCaseTheme {
                val navigationManager: NavControllerAccessor = get()
                val strategy = rememberListDetailSceneStrategyCustom<Any>()

                NavDisplay(
                    backStack = navigationManager.getBackStack(),
                    onBack = { navigationManager.goBack() },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    sceneStrategy = strategy,
                    entryProvider = entryProvider {
                        entry<HomeScreenKey>(
                            metadata = ListDetailSceneStrategyCustom.listPane {
                                CharacterDetailsUnselected()
                            }
                        ) {
                            HomeScreen()
                        }
                        entry<CharacterDetailsScreenKey>(
                            metadata = ListDetailSceneStrategyCustom.detailPane()
                        ) { key ->
                            val withinScene = LocalWithinScene.current

                            val viewModel = koinViewModel<CharacterDetailsViewModel> {
                                parametersOf(key.id, withinScene)
                            }

                            CharacterDetailsScreen(
                                viewModel = viewModel,
                                characterId = key.id,
                                withinScene = withinScene,
                            )
                        }
                        entry<EpisodeDetailsScreenKey> { id ->
                            val viewModel = koinViewModel<EpisodeDetailsViewModel> {
                                parametersOf(id)
                            }
                            EpisodeDetailsScreen(viewModel = viewModel)
                        }
                    },
                    transitionSpec = {
                        slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
                    },
                    popTransitionSpec = {
                        slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
                    },
                    predictivePopTransitionSpec = {
                        slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
                    }
                )
            }
        }
    }
}
