package com.gromo.masterdetailshowcase.features.session.domain.repositories

import com.gromo.masterdetailshowcase.features.session.domain.data_sources.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class SessionRepository(
    private val localDataSource: SessionLocalDataSource,
) {

    fun setHasSeenOnboarding(hasSeen: Boolean) {
        localDataSource.hasSeenOnboarding = hasSeen
    }

    fun observeHasSeenOnboarding(): Flow<Boolean> = localDataSource.observeHasSeenOnboarding()
}
