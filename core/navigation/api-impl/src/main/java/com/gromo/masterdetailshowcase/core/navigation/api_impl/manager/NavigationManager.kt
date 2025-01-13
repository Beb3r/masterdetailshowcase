package com.gromo.masterdetailshowcase.core.navigation.api_impl.manager

interface NavigationManager {

    fun navigate(command: NavigationCommand)
    fun clear()
}
