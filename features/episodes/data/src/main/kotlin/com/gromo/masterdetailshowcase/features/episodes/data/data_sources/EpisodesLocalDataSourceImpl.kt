package com.gromo.masterdetailshowcase.features.episodes.data.data_sources

import com.gromo.masterdetailshowcase.features.episodes.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.features.episodes.data.mappers.toEntityModel
import com.gromo.masterdetailshowcase.features.episodes.domain.data_sources.EpisodesLocalDataSource
import com.gromo.masterdetailshowcase.features.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.libraries.persistence.api.daos.EpisodeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [EpisodesLocalDataSource::class])
class EpisodesLocalDataSourceImpl(
    private val dao: EpisodeDao,
) : EpisodesLocalDataSource {


    override fun observeAllEpisodes(): Flow<List<EpisodeDomainModel>> =
        dao.observeAll().map { it.map { episode -> episode.toDomainModel() } }

    override fun observeEpisodeById(id: Int): Flow<EpisodeDomainModel?> =
        dao.observeById(id = id).map { it?.toDomainModel() }.distinctUntilChanged()

    override suspend fun saveEpisodes(episodes: List<EpisodeDomainModel>) =
        dao.saveEpisodes(episodes = episodes.map { it.toEntityModel() })
}
