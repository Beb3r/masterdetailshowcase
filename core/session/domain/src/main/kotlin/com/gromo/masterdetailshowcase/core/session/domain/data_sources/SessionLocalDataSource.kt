package com.gromo.masterdetailshowcase.core.session.domain.data_sources

import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {

    var hasSeenOnboarding: Boolean

    fun observeHasSeenOnboarding(): Flow<Boolean>
}
