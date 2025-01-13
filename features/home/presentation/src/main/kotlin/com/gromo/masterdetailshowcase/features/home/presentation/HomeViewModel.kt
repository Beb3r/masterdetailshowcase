package com.gromo.masterdetailshowcase.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.FetchAllCharactersUseCase
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.ObserveAllCharactersUseCase
import com.gromo.masterdetailshowcase.core.common.combines
import com.gromo.masterdetailshowcase.core.common.dispatchers.AppCoroutineDispatchers
import com.gromo.masterdetailshowcase.core.common.stateIn
import com.gromo.masterdetailshowcase.features.home.presentation.mappers.toUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    observeAllCharactersUseCase: ObserveAllCharactersUseCase,
    private val coroutineDispatcher: AppCoroutineDispatchers,
    private val fetchAllCharactersUseCase: FetchAllCharactersUseCase,
) : ViewModel() {

    private val fetchErrorFlow = MutableStateFlow<Throwable?>(null)
    private val isRefreshingFlow = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<HomeViewStateUiModel> =
        combines(
            observeAllCharactersUseCase(),
            fetchErrorFlow,
            isRefreshingFlow,
        ).mapLatest { (characters, fetchError, isRefreshing) ->
            Log.d("HomeViewModel", "Characters: $characters")
            when {
                characters.isEmpty() -> {
                    if (fetchError != null) {
                        HomeViewStateUiModel.Error(
                            isRefreshing = isRefreshing,
                            onRefreshTriggered = { fetchAllCharacters() }
                        )
                    } else {
                        HomeViewStateUiModel.Empty(
                            isRefreshing = isRefreshing,
                            onRefreshTriggered = { fetchAllCharacters() }
                        )
                    }
                }

                else -> {
                    HomeViewStateUiModel.Filled(
                        isRefreshing = isRefreshing,
                        onRefreshTriggered = { fetchAllCharacters() },
                        characters = characters.map {
                            it.toUiModel(
                                onClick = { characterId -> onCharacterClicked(characterId) }
                            )
                        }.toPersistentList()
                    )
                }
            }
        }.flowOn(coroutineDispatcher.main)
            .stateIn(
                scope = viewModelScope,
                initialValue = HomeViewStateUiModel.Empty(
                    isRefreshing = false,
                    onRefreshTriggered = { fetchAllCharacters() }
                )
            )


    fun onViewInitialised() {
        fetchAllCharacters()
    }

    private fun fetchAllCharacters() {
        viewModelScope.launch(coroutineDispatcher.io) {
            isRefreshingFlow.value = true
            fetchAllCharactersUseCase()
                .onSuccess {
                    isRefreshingFlow.value = false
                }
                .onFailure { error ->
                    Log.d("HomeViewModel", "Error: $error")
                    isRefreshingFlow.value = false
                    fetchErrorFlow.value = error
                }
        }
    }

    private fun onCharacterClicked(characterId: Int) {
        Log.d("HomeViewModel", "Character clicked: $characterId")
    }
}
