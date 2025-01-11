package com.gromo.masterdetailshowcase.core.countries.domain.use_cases

import com.gromo.masterdetailshowcase.core.countries.domain.repositories.CountriesRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveCountryByIdUseCase(private val repository: CountriesRepository) {

    operator fun invoke(id: String) = repository.observeCountryById(id)

}
