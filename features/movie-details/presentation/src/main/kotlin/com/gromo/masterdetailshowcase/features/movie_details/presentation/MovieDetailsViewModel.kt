package com.gromo.masterdetailshowcase.features.movie_details.presentation

import androidx.lifecycle.ViewModel
import com.gromo.masterdetailshowcase.core.countries.domain.use_cases.ObserveCountryByIdUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MovieDetailsViewModel(
    private val observeCountryByIdUseCase: ObserveCountryByIdUseCase,
): ViewModel() {

}
