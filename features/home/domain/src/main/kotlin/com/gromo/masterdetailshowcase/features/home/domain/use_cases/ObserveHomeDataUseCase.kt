package com.gromo.masterdetailshowcase.features.home.domain.use_cases

import com.gromo.masterdetailshowcase.features.characters.domain.use_cases.ObserveAllCharactersUseCase
import com.gromo.masterdetailshowcase.libraries.common.combines
import com.gromo.masterdetailshowcase.features.episodes.domain.use_cases.ObserveAllEpisodesUseCase
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
