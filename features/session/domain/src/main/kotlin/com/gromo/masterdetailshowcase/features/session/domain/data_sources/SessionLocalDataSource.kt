package com.gromo.masterdetailshowcase.features.session.domain.data_sources

import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {

    var hasSeenOnboarding: Boolean

    fun observeHasSeenOnboarding(): Flow<Boolean>
}
