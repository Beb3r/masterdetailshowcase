package com.gromo.masterdetailshowcase.core.countries.data.data_sources

import android.util.Log
import com.gromo.masterdetailshowcase.core.network.api.models.CountryApiModel
import com.gromo.masterdetailshowcase.core.network.api.services.CountriesApiService
import com.gromo.masterdetailshowcase.core.countries.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.core.countries.domain.data_sources.CountriesRemoteDataSource
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.errors.CountryParsingErrorDomainModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.errors.NetworkErrorDomainModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.errors.NoCountryErrorDomainModel
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.koin.core.annotation.Single

@Single(binds = [CountriesRemoteDataSource::class])
class CountriesRemoteDataSourceImpl(
    private val service: CountriesApiService,
) : CountriesRemoteDataSource {

    override suspend fun fetchAllCountries(): Result<List<CountryDomainModel>> {
        Log.d("CountriesRemoteDataSourceImpl", "fetchAllCountries")
        val response = try {
            service.getAllCountries()
        } catch (e: Exception) {
            Log.d("CountriesRemoteDataSourceImpl", "exception:${e.printStackTrace()}")
            null
        }

        return if (response?.status?.isSuccess() == true) {
            try {
                val body = response.body<List<CountryApiModel>>()
                val countries = body.mapNotNull { it.toDomainModel() }
                if (countries.isEmpty()) {
                    Log.d("CountriesRemoteDataSourceImpl", "empty")
                    Result.failure(NoCountryErrorDomainModel())
                } else {
                    Result.success(countries)
                }
            } catch (e: Exception) {
                Log.d("CountriesRemoteDataSourceImpl", "exception:${e.message}")
                Result.failure(CountryParsingErrorDomainModel(e.message))
            }
        } else {
            Log.d("CountriesRemoteDataSourceImpl", "server:${response?.status?.value}")
            Result.failure(NetworkErrorDomainModel(response?.status?.value ?: -1))
        }
    }

}
