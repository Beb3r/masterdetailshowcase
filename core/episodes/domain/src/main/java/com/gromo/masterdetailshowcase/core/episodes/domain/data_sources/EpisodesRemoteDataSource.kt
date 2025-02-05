package com.gromo.masterdetailshowcase.core.episodes.domain.data_sources

import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel

interface EpisodesRemoteDataSource {

    suspend fun fetchAllEpisodes(): Result<List<EpisodeDomainModel>>
}
