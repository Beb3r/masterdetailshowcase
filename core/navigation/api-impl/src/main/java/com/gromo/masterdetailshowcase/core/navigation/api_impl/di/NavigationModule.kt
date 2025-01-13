package com.gromo.masterdetailshowcase.core.navigation.api_impl.di

import com.gromo.masterdetailshowcase.core.navigation.api.NavControllerSetter
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManager
import com.gromo.masterdetailshowcase.core.navigation.api_impl.manager.NavigationManagerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {

    singleOf(::NavigationManagerImpl) {
        bind<NavigationManager>()
        bind<NavControllerSetter>()
    }
}
