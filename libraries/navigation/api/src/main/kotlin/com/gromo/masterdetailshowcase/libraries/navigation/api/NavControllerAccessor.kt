package com.gromo.masterdetailshowcase.libraries.navigation.api

import androidx.compose.runtime.snapshots.SnapshotStateList

interface NavControllerAccessor {

    fun getBackStack(): SnapshotStateList<NavigationKey>

    fun goBack()
}
