package com.gromo.masterdetailshowcase.libraries.network.api.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.koin.core.annotation.Single

@Single
class EpisodesApiService(private val httpClient: HttpClient) {

    suspend fun getAllEpisodes(): HttpResponse =
        httpClient.get("episode/")
}