package com.gromo.masterdetailshowcase.core.navigation.api_impl.di

import com.gromo.masterdetailshowcase.core.navigation.api.NavControllerAccessor
import com.gromo.masterdetailshowcase.core.navigation.api_impl.CharacterDetailsNavigationImpl
import com.gromo.masterdetailshowcase.core.navigation.api_impl.HomeNavigationImpl
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManagerImpl
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsNavigation
import com.gromo.masterdetailshowcase.features.home.navigation.HomeNavigation
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {

    singleOf(::NavigationManagerImpl) {
        bind<NavigationManager>()
        bind<NavControllerAccessor>()
    }

    singleOf(::HomeNavigationImpl) {
        bind<HomeNavigation>()
    }

    singleOf(::CharacterDetailsNavigationImpl) {
        bind<CharacterDetailsNavigation>()
    }
}
