package com.gromo.masterdetailshowcase.core.episodes.domain.data_sources

import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel
import kotlinx.coroutines.flow.Flow

interface EpisodesLocalDataSource {

    fun observeAllEpisodes(): Flow<List<EpisodeDomainModel>>

    fun observeEpisodeById(id: Int): Flow<EpisodeDomainModel?>

    suspend fun saveEpisodes(episodes: List<EpisodeDomainModel>)
}