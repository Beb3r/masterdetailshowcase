package com.gromo.masterdetailshowcase.libraries.network.api_impl

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
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.gromo.masterdetailshowcase.libraries.network")
class NetworkModule

@Single
internal fun provideHttpClient() = HttpClient(Android) {
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
        url("https://rickandmortyapi.com/api/")
    }

    install(Logging) {
        level = LogLevel.INFO
    }
}
