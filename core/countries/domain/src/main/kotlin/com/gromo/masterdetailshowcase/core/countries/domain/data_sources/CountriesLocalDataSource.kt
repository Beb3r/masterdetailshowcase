package com.gromo.masterdetailshowcase.core.countries.domain.data_sources

import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel
import kotlinx.coroutines.flow.Flow

interface CountriesLocalDataSource {

    fun observeAllCountries(): Flow<List<CountryDomainModel>>

    fun observeCountryById(id: String): Flow<CountryDomainModel?>

    suspend fun saveCountries(countries: List<CountryDomainModel>)

}
