package com.gromo.masterdetailshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gromo.masterdetailshowcase.core.navigation.api.NavControllerAccessor
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsScreenRoute
import com.gromo.masterdetailshowcase.features.character_details.presentation.CharacterDetailsScreen
import com.gromo.masterdetailshowcase.features.home.navigation.HomeScreenRoute
import com.gromo.masterdetailshowcase.features.home.presentation.HomeScreen
import com.gromo.masterdetailshowcase.core.design.MasterDetailShowCaseTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MasterDetailShowCaseTheme {
                val navController = rememberNavController()
                val navigationManager: NavControllerAccessor = get()

                DisposableEffect(navController) {
                    navigationManager.setController(navController)
                    onDispose {
                        navigationManager.clear()
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = HomeScreenRoute,
                    enterTransition = { slideInHorizontally { it } },
                    exitTransition = { slideOutHorizontally { -it } },
                    popEnterTransition = { slideInHorizontally { -it } },
                    popExitTransition = { slideOutHorizontally { it } },
                ) {
                    composable<HomeScreenRoute> {
                        HomeScreen()
                    }
                    composable<CharacterDetailsScreenRoute> {
                        CharacterDetailsScreen()
                    }
                }
            }
        }
    }
}
