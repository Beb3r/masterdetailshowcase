package com.gromo.masterdetailshowcase.core.countries.domain.use_cases

import com.gromo.masterdetailshowcase.core.countries.domain.repositories.CountriesRepository
import org.koin.core.annotation.Factory

@Factory
class FetchAllCountriesUseCase(private val repository: CountriesRepository) {

    suspend operator fun invoke() = repository.fetchAllCountries()

}
