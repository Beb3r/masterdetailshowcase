package com.gromo.masterdetailshowcase.features.session.domain.data_sources

import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {

    suspend fun getHasSeenOnboarding(): Boolean

    suspend fun setHasSeenOnboarding(hasSeen: Boolean)

    fun observeHasSeenOnboarding(): Flow<Boolean>
}
