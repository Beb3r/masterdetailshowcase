package com.gromo.masterdetailshowcase.features.episode_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gromo.masterdetailshowcase.libraries.common.stateIn
import com.gromo.masterdetailshowcase.features.episodes.domain.use_cases.ObserveEpisodeByIdUseCase
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsNavigation
import com.gromo.masterdetailshowcase.features.episode_details.navigation.EpisodeDetailsScreenRoute
import com.gromo.masterdetailshowcase.features.episode_details.presentation.models.EpisodeDetailsViewStateUiModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EpisodeDetailsViewModel(
    handle: SavedStateHandle,
    private val observeEpisodeByIdUseCase: ObserveEpisodeByIdUseCase,
    private val navigation: EpisodeDetailsNavigation,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<EpisodeDetailsViewStateUiModel> =
        flowOf(handle.toRoute<EpisodeDetailsScreenRoute>().id).flatMapLatest {
            observeEpisodeByIdUseCase(it)
        }.mapLatest { episode ->
            if (episode == null) {
                // display error message and navigate back
                EpisodeDetailsViewStateUiModel.DEFAULT
            } else {
                EpisodeDetailsViewStateUiModel(
                    id = episode.id,
                    name = episode.name,
                    airDate = episode.airDate,
                    episode = episode.episode,
                    characters = episode.characters.toPersistentList(),
                )
            }
        }.stateIn(
            scope = viewModelScope,
            initialValue = EpisodeDetailsViewStateUiModel.DEFAULT
        )

    fun onBackClicked() {
        navigation.navigateBack()
    }
}
