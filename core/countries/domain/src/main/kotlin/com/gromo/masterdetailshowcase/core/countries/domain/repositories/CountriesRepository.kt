package com.gromo.masterdetailshowcase.core.countries.domain.repositories

import com.gromo.masterdetailshowcase.core.countries.domain.data_sources.CountriesLocalDataSource
import com.gromo.masterdetailshowcase.core.countries.domain.data_sources.CountriesRemoteDataSource
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class CountriesRepository(
    private val remoteDataSource: CountriesRemoteDataSource,
    private val localDataSource: CountriesLocalDataSource,
) {

    suspend fun fetchAllCountries(): Result<Unit> =
        remoteDataSource.fetchAllCountries().map {
            localDataSource.saveCountries(it)
        }

    fun observeAllCountries(): Flow<List<CountryDomainModel>> =
        localDataSource.observeAllCountries()

    fun observeCountryById(id: String): Flow<CountryDomainModel?> =
        localDataSource.observeCountryById(id)

}
