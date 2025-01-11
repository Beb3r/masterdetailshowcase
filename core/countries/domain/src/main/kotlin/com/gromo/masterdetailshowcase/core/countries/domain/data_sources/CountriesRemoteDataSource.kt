package com.gromo.masterdetailshowcase.core.countries.domain.data_sources

import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel

interface CountriesRemoteDataSource {

    suspend fun fetchAllCountries(): Result<List<CountryDomainModel>>

}
