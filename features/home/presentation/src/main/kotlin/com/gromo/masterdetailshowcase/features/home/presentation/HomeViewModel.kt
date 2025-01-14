package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.FetchAllCharactersUseCase
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.ObserveAllCharactersUseCase
import com.gromo.masterdetailshowcase.core.common.combines
import com.gromo.masterdetailshowcase.core.common.dispatchers.AppCoroutineDispatchers
import com.gromo.masterdetailshowcase.core.common.stateIn
import com.gromo.masterdetailshowcase.core.session.domain.use_cases.ObserveHasSeenOnboardingUseCase
import com.gromo.masterdetailshowcase.core.session.domain.use_cases.SetHasSeenOnboardingUseCase
import com.gromo.masterdetailshowcase.features.home.navigation.HomeNavigation
import com.gromo.masterdetailshowcase.features.home.presentation.mappers.toHomeViewState
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
class HomeViewModel(
    observeAllCharactersUseCase: ObserveAllCharactersUseCase,
    observeHasSeenOnboardingUseCase: ObserveHasSeenOnboardingUseCase,
    private val coroutineDispatcher: AppCoroutineDispatchers,
    private val fetchAllCharactersUseCase: FetchAllCharactersUseCase,
    private val navigation: HomeNavigation,
    private val setHasSeenOnboardingUseCase: SetHasSeenOnboardingUseCase,
) : ViewModel() {

    private val fetchErrorFlow = MutableStateFlow<Throwable?>(null)
    private val isRefreshingFlow = MutableStateFlow(false)
    private val shouldShowOnboardingFromUserFlow = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<HomeViewStateUiModel> =
        combines(
            observeAllCharactersUseCase(),
            observeHasSeenOnboardingUseCase(),
            shouldShowOnboardingFromUserFlow,
            fetchErrorFlow,
            isRefreshingFlow,
        ).mapLatest { (characters, hasSeenOnboarding, shouldShowOnboardingFromUser, fetchError, isRefreshing) ->
            Timber.d("Characters: $characters")
            toHomeViewState(
                isRefreshing = isRefreshing,
                onRefreshTriggered = { fetchAllCharacters() },
                hasSeenOnboarding = hasSeenOnboarding,
                shouldShowOnboardingFromUser = shouldShowOnboardingFromUser,
                onTopBarActionHelpClicked = {
                    shouldShowOnboardingFromUserFlow.value = true
                },
                onTopBarActionCloseClicked = { fromUser ->
                    if (fromUser) {
                        shouldShowOnboardingFromUserFlow.value = false
                    } else {
                        setHasSeenOnboardingUseCase(hasSeen = true)
                    }
                },
                characters = characters,
                fetchError = fetchError,
                onCharacterClicked = { id ->
                    onCharacterClicked(id = id)
                }
            )
        }.flowOn(coroutineDispatcher.main)
            .stateIn(
                scope = viewModelScope,
                initialValue = HomeViewStateUiModel.DEFAULT
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
                    Timber.w("Error fetching characters: $error")
                    isRefreshingFlow.value = false
                    fetchErrorFlow.value = error
                }
        }
    }

    private fun onCharacterClicked(id: Int) {
        navigation.navigateToCharacterDetails(id = id)
    }
}
