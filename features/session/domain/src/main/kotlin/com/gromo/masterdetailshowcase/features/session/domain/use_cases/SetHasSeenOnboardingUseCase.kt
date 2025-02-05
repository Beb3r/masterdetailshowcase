package com.gromo.masterdetailshowcase.features.session.domain.use_cases

import com.gromo.masterdetailshowcase.features.session.domain.repositories.SessionRepository
import org.koin.core.annotation.Factory

@Factory
class SetHasSeenOnboardingUseCase(
    private val repository: SessionRepository
) {

    operator fun invoke(hasSeen: Boolean) = repository.setHasSeenOnboarding(hasSeen = hasSeen)
}
