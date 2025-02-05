package com.gromo.masterdetailshowcase.features.episodes.data.data_sources

import com.gromo.masterdetailshowcase.libraries.common.ServerErrorDomainModel
import com.gromo.masterdetailshowcase.libraries.common.UnknownHostException
import com.gromo.masterdetailshowcase.features.episodes.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.features.episodes.domain.data_sources.EpisodesRemoteDataSource
import com.gromo.masterdetailshowcase.features.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.features.episodes.domain.models.errors.EpisodeParsingErrorDomainModel
import com.gromo.masterdetailshowcase.features.episodes.domain.models.errors.NoEpisodeErrorDomainModel
import com.gromo.masterdetailshowcase.libraries.network.api.models.EpisodesResponseApiModel
import com.gromo.masterdetailshowcase.libraries.network.api.services.EpisodesApiService
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.koin.core.annotation.Single

@Single(binds = [EpisodesRemoteDataSource::class])
class EpisodesRemoteDataSourceImpl(
    private val service: EpisodesApiService,
) : EpisodesRemoteDataSource {

    override suspend fun fetchAllEpisodes(): Result<List<EpisodeDomainModel>> =
        // TODO create a wrapper to handle responses
        try {
            val response = service.getAllEpisodes()
            if (response.status.isSuccess()) {
                val body = response.body<EpisodesResponseApiModel>()
                val countries = body.results?.mapNotNull { it.toDomainModel() } ?: emptyList()

                if (countries.isEmpty()) {
                    Result.failure(NoEpisodeErrorDomainModel())
                } else {
                    Result.success(countries)
                }
            } else {
                Result.failure(
                    ServerErrorDomainModel(
                        response.status.value
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is java.net.UnknownHostException -> {
                    Result.failure(UnknownHostException())
                }

                is NoTransformationFoundException -> {
                    Result.failure(EpisodeParsingErrorDomainModel(e.message))
                }

                else -> {
                    Result.failure(Throwable(e.message))
                }
            }
        }
}
