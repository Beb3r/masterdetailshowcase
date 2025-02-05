package com.gromo.masterdetailshowcase.features.episodes.domain.use_cases

import com.gromo.masterdetailshowcase.features.episodes.domain.repositories.EpisodesRepository
import org.koin.core.annotation.Factory

@Factory
class FetchAllEpisodesUseCase(private val repository: EpisodesRepository) {

    suspend operator fun invoke() = repository.fetchAllEpisodes()
}
