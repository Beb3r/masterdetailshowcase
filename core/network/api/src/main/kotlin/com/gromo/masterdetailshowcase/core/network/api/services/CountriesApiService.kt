package com.gromo.masterdetailshowcase.core.network.api.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class CountriesApiService(private val httpClient: HttpClient) {
    suspend fun getAllCountries(): HttpResponse =
        httpClient.get("https://restcountries.com/v3.1/all")
}
