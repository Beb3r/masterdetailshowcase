package com.gromo.masterdetailshowcase.features.session.domain.repositories

import com.gromo.masterdetailshowcase.features.session.domain.data_sources.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class SessionRepository(
    private val localDataSource: SessionLocalDataSource,
) {

    suspend fun setHasSeenOnboarding(hasSeen: Boolean) {
        localDataSource.setHasSeenOnboarding(hasSeen = hasSeen)
    }

    fun observeHasSeenOnboarding(): Flow<Boolean> = localDataSource.observeHasSeenOnboarding()
}
