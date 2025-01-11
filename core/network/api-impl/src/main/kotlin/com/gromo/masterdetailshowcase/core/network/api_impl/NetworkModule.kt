package com.gromo.masterdetailshowcase.core.network.api_impl

import com.gromo.masterdetailshowcase.core.network.api.services.CountriesApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }

            defaultRequest {
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json)
                }
            }

            install(Logging) {
                level = LogLevel.HEADERS
            }
        }
    }

    singleOf(::CountriesApiService)
}
