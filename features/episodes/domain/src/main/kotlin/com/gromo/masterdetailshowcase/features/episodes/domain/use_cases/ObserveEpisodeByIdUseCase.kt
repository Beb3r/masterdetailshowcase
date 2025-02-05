package com.gromo.masterdetailshowcase.features.episodes.domain.use_cases

import com.gromo.masterdetailshowcase.features.episodes.domain.repositories.EpisodesRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveEpisodeByIdUseCase(private val repository: EpisodesRepository) {

    operator fun invoke(id: Int) = repository.observeEpisodeById(id = id)
}
