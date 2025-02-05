package com.gromo.masterdetailshowcase.core.network.api.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class EpisodesApiService(private val httpClient: HttpClient) {

    suspend fun getAllEpisodes(): HttpResponse =
        httpClient.get("episode/")
}