package com.gromo.masterdetailshowcase.core.countries.data.data_sources

import com.gromo.masterdetailshowcase.core.persistence.api.daos.CountryDao
import com.gromo.masterdetailshowcase.core.countries.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.core.countries.data.mappers.toEntityModel
import com.gromo.masterdetailshowcase.core.countries.domain.data_sources.CountriesLocalDataSource
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [CountriesLocalDataSource::class])
class CountriesLocalDataSourceImpl(
    private val countryDao: CountryDao,
) : CountriesLocalDataSource {

    override fun observeAllCountries(): Flow<List<CountryDomainModel>> =
        countryDao.observeAll().map { it.map { country -> country.toDomainModel() } }

    override fun observeCountryById(id: String): Flow<CountryDomainModel?> =
        countryDao.observeById(id).map { it?.toDomainModel() }

    override suspend fun saveCountries(countries: List<CountryDomainModel>) =
        countryDao.saveCountries(countries.map { it.toEntityModel() })

}
