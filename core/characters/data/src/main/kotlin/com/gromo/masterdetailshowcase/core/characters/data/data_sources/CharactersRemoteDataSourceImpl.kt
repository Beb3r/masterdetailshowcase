package com.gromo.masterdetailshowcase.core.characters.data.data_sources

import com.gromo.masterdetailshowcase.core.characters.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.core.characters.domain.data_sources.CharactersRemoteDataSource
import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.core.characters.domain.models.errors.CharacterParsingErrorDomainModel
import com.gromo.masterdetailshowcase.core.characters.domain.models.errors.NoCharacterErrorDomainModel
import com.gromo.masterdetailshowcase.core.common.ServerErrorDomainModel
import com.gromo.masterdetailshowcase.core.common.UnknownHostException
import com.gromo.masterdetailshowcase.core.network.api.models.CharactersResponseApiModel
import com.gromo.masterdetailshowcase.core.network.api.services.CharactersApiService
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.koin.core.annotation.Single

@Single(binds = [CharactersRemoteDataSource::class])
class CharactersRemoteDataSourceImpl(
    private val service: CharactersApiService,
) : CharactersRemoteDataSource {

    override suspend fun fetchAllCharacters(): Result<List<CharacterDomainModel>> =
        // TODO create a wrapper to handle responses
        try {
            val response = service.getAllCharacters()
            if (response.status.isSuccess()) {
                val body = response.body<CharactersResponseApiModel>()
                val countries = body.results?.mapNotNull { it.toDomainModel() } ?: emptyList()

                if (countries.isEmpty()) {
                    Result.failure(NoCharacterErrorDomainModel())
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
                    Result.failure(CharacterParsingErrorDomainModel(e.message))
                }

                else -> {
                    Result.failure(Throwable(e.message))
                }
            }
        }
}
