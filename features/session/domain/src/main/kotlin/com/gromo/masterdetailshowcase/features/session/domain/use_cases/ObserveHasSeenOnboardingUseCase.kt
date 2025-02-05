package com.gromo.masterdetailshowcase.features.session.domain.use_cases

import com.gromo.masterdetailshowcase.features.session.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class ObserveHasSeenOnboardingUseCase(
    private val repository: SessionRepository
) {

    operator fun invoke(): Flow<Boolean> = repository.observeHasSeenOnboarding()
}
