package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gromo.masterdetailshowcase.features.characters.domain.use_cases.FetchAllCharactersUseCase
import com.gromo.masterdetailshowcase.features.episodes.domain.use_cases.FetchAllEpisodesUseCase
import com.gromo.masterdetailshowcase.features.home.domain.use_cases.ObserveHomeDataUseCase
import com.gromo.masterdetailshowcase.features.home.navigation.HomeNavigation
import com.gromo.masterdetailshowcase.features.home.presentation.mappers.toHomeViewState
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionTypeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import com.gromo.masterdetailshowcase.features.session.domain.use_cases.ObserveHasSeenOnboardingUseCase
import com.gromo.masterdetailshowcase.features.session.domain.use_cases.SetHasSeenOnboardingUseCase
import com.gromo.masterdetailshowcase.libraries.common.combines
import com.gromo.masterdetailshowcase.libraries.common.dispatchers.AppCoroutineDispatchers
import com.gromo.masterdetailshowcase.libraries.common.stateIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
class HomeViewModel(
    observeHomeDataUseCase: ObserveHomeDataUseCase,
    observeHasSeenOnboardingUseCase: ObserveHasSeenOnboardingUseCase,
    private val coroutineDispatcher: AppCoroutineDispatchers,
    private val fetchAllCharactersUseCase: FetchAllCharactersUseCase,
    private val fetchAllEpisodesUseCase: FetchAllEpisodesUseCase,
    private val navigation: HomeNavigation,
    private val setHasSeenOnboardingUseCase: SetHasSeenOnboardingUseCase,
) : ViewModel() {

    private var fetchCharactersJob: Job? = null
    private var fetchEpisodesJob: Job? = null

    private val charactersFetchStatusFlow =
        MutableStateFlow<FetchStatusUiModel>(FetchStatusUiModel.Idle)
    private val episodesFetchStatusFlow =
        MutableStateFlow<FetchStatusUiModel>(FetchStatusUiModel.Idle)
    private val shouldShowOnboardingFromUserFlow = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<HomeViewStateUiModel> =
        combines(
            observeHomeDataUseCase(),
            observeHasSeenOnboardingUseCase(),
            shouldShowOnboardingFromUserFlow,
            charactersFetchStatusFlow,
            episodesFetchStatusFlow,
        ).mapLatest { (data, hasSeenOnboarding, shouldShowOnboardingFromUser, charactersFetchStatus, episodesFetchStatus) ->
            toHomeViewState(
                onRefreshTriggered = { fetchData() },
                hasSeenOnboarding = hasSeenOnboarding,
                shouldShowOnboardingFromUser = shouldShowOnboardingFromUser,
                onTopBarActionClicked = ::onTopAppBarActionClicked,
                data = data,
                charactersFetchStatus = charactersFetchStatus,
                episodesFetchStatus = episodesFetchStatus,
                onCharacterClicked = ::onCharacterClicked,
                onEpisodeClicked = ::onEpisodeClicked,
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = HomeViewStateUiModel.DEFAULT,
        )


    fun onViewInitialised() {
        fetchData()
    }

    private fun fetchData() {
        fetchAllCharacters()
        fetchAllEpisodes()
    }

    private fun fetchAllCharacters() {
        fetchCharactersJob?.cancel()
        fetchCharactersJob = viewModelScope.async(coroutineDispatcher.io) {
            charactersFetchStatusFlow.value = FetchStatusUiModel.Loading
            fetchAllCharactersUseCase()
                .onSuccess {
                    charactersFetchStatusFlow.value = FetchStatusUiModel.Idle
                }
                .onFailure { error ->
                    Timber.w("Error fetching characters: $error")
                    charactersFetchStatusFlow.value = FetchStatusUiModel.Error
                }
        }
    }


    private fun fetchAllEpisodes() {
        fetchEpisodesJob?.cancel()
        fetchEpisodesJob = viewModelScope.async(coroutineDispatcher.io) {
            episodesFetchStatusFlow.value = FetchStatusUiModel.Loading
            fetchAllEpisodesUseCase()
                .onSuccess {
                    episodesFetchStatusFlow.value = FetchStatusUiModel.Idle
                }
                .onFailure { error ->
                    Timber.w("Error fetching episodes: $error")
                    episodesFetchStatusFlow.value = FetchStatusUiModel.Error
                }
        }
    }

    private fun onTopAppBarActionClicked(type: HomeTopBarActionTypeUiModel) {
        when (type) {
            is HomeTopBarActionTypeUiModel.Help -> {
                shouldShowOnboardingFromUserFlow.value = true
            }

            is HomeTopBarActionTypeUiModel.Close -> {
                if (type.openedByUser) {
                    shouldShowOnboardingFromUserFlow.value = false
                } else {
                    setHasSeenOnboardingUseCase(hasSeen = true)
                }
            }
        }
    }

    private fun onCharacterClicked(id: Int) {
        navigation.navigateToCharacterDetails(id = id)
    }

    private fun onEpisodeClicked(id: Int) {
        navigation.navigateToEpisodeDetails(id = id)
    }

    @Immutable
    sealed interface FetchStatusUiModel {
        data object Loading : FetchStatusUiModel
        data object Idle : FetchStatusUiModel

        @Immutable
        data object Error : FetchStatusUiModel
    }
}
