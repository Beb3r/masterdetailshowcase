package com.gromo.masterdetailshowcase.libraries.navigation.api_impl.manager

import androidx.navigation.NavOptions
import com.gromo.masterdetailshowcase.libraries.navigation.api.NavigationRoute

sealed interface NavigationCommand {

    data object NavigateUp : NavigationCommand

    data class NavigateToRoute(val route: NavigationRoute, val options: NavOptions? = null) :
        NavigationCommand

    data class PopUpToRoute(
        val route: NavigationRoute,
        val inclusive: Boolean,
        val fallBackRoute: NavigationRoute,
    ) : NavigationCommand
}