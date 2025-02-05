package com.gromo.masterdetailshowcase.core.episodes.domain.repositories

import com.gromo.masterdetailshowcase.core.episodes.domain.data_sources.EpisodesLocalDataSource
import com.gromo.masterdetailshowcase.core.episodes.domain.data_sources.EpisodesRemoteDataSource
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single


@Single
class EpisodesRepository(
    private val remoteDataSource: EpisodesRemoteDataSource,
    private val localDataSource: EpisodesLocalDataSource,
) {

    suspend fun fetchAllEpisodes(): Result<Unit> =
        remoteDataSource.fetchAllEpisodes().map {
            localDataSource.saveEpisodes(episodes = it)
        }

    fun observeAllEpisodes(): Flow<List<EpisodeDomainModel>> =
        localDataSource.observeAllEpisodes()

    fun observeEpisodeById(id: Int): Flow<EpisodeDomainModel?> =
        localDataSource.observeEpisodeById(id = id)
}
