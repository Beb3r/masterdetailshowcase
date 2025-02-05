package com.gromo.masterdetailshowcase.features.home.domain.use_cases

import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.ObserveAllCharactersUseCase
import com.gromo.masterdetailshowcase.core.common.combines
import com.gromo.masterdetailshowcase.core.episodes.domain.use_cases.ObserveAllEpisodesUseCase
import com.gromo.masterdetailshowcase.features.home.domain.models.HomeDataDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.annotation.Factory

@Factory
class ObserveHomeDataUseCase(
    private val observeAllCharactersUseCase: ObserveAllCharactersUseCase,
    private val observeAllEpisodesUseCase: ObserveAllEpisodesUseCase,
) {
    operator fun invoke(): Flow<HomeDataDomainModel> =
        combines(
            observeAllCharactersUseCase(),
            observeAllEpisodesUseCase()
        ).mapLatest { (characters, episodes) ->
            HomeDataDomainModel(
                characters = characters,
                episodes = episodes,
            )
        }
}
