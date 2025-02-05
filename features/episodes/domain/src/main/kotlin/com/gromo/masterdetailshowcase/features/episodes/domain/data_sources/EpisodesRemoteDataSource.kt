package com.gromo.masterdetailshowcase.features.episodes.domain.data_sources

import com.gromo.masterdetailshowcase.features.episodes.domain.models.EpisodeDomainModel

interface EpisodesRemoteDataSource {

    suspend fun fetchAllEpisodes(): Result<List<EpisodeDomainModel>>
}
