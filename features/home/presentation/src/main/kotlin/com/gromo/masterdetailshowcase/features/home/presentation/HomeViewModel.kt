package com.gromo.masterdetailshowcase.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gromo.masterdetailshowcase.core.common.combines
import com.gromo.masterdetailshowcase.core.common.dispatchers.AppCoroutineDispatchers
import com.gromo.masterdetailshowcase.core.common.stateIn
import com.gromo.masterdetailshowcase.core.countries.domain.use_cases.FetchAllCountriesUseCase
import com.gromo.masterdetailshowcase.core.countries.domain.use_cases.ObserveAllCountriesUseCase
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    coroutineDispatcher: AppCoroutineDispatchers,
    observeAllCountriesUseCase: ObserveAllCountriesUseCase,
    private val fetchAllCountriesUseCase: FetchAllCountriesUseCase,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<HomeViewStateUiModel> =
        combines(
            observeAllCountriesUseCase(),
        ).mapLatest { (countries) ->
            HomeViewStateUiModel(
                id = "toto",
            )
        }.flowOn(coroutineDispatcher.main)
            .stateIn(
                scope = viewModelScope,
                initialValue = HomeViewStateUiModel("titi")
            )


    init {
        viewModelScope.launch(coroutineDispatcher.io) {
            fetchAllCountriesUseCase().onFailure { error -> Log.d("HomeViewModel", "Error: $error") }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("HomeViewModel", "onCleared")
    }
}
