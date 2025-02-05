package com.gromo.masterdetailshowcase.features.home.presentation.mappers

import android.os.Build
import androidx.compose.ui.graphics.Color
import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.features.home.domain.models.HomeDataDomainModel
import com.gromo.masterdetailshowcase.features.home.presentation.HomeViewModel.FetchStatusUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.EpisodeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeCharacterListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeEpisodeListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.collections.immutable.toPersistentList

fun toHomeViewState(
    onRefreshTriggered: () -> Unit,
    hasSeenOnboarding: Boolean,
    onTopBarActionHelpClicked: () -> Unit,
    onTopBarActionCloseClicked: (Boolean) -> Unit,
    shouldShowOnboardingFromUser: Boolean,
    data: HomeDataDomainModel,
    charactersFetchStatus: FetchStatusUiModel,
    episodesFetchStatus: FetchStatusUiModel,
    onCharacterClicked: (Int) -> Unit,
    onEpisodeClicked: (Int) -> Unit,
): HomeViewStateUiModel {
    val topBarActionViewState = getTopBarActionViewState(
        hasSeenOnboarding = hasSeenOnboarding,
        shouldShowOnboardingFromUser = shouldShowOnboardingFromUser,
        onTopBarActionCloseClicked = onTopBarActionCloseClicked,
        onTopBarActionHelpClicked = onTopBarActionHelpClicked,
    )
    val onboardingViewState = getOnboardingViewState(
        hasSeenOnboarding = hasSeenOnboarding,
        shouldShowOnboardingFromUser = shouldShowOnboardingFromUser,
    )
    val characterListViewState = getCharacterListViewState(
        characters = data.characters,
        fetchStatus = charactersFetchStatus,
        onCharacterClicked = onCharacterClicked,
    )

    val episodeListViewState = getEpisodeListViewState(
        episodes = data.episodes,
        fetchStatus = charactersFetchStatus,
        onEpisodeClicked = onEpisodeClicked,
    )

    val isLoading = charactersFetchStatus is FetchStatusUiModel.Loading ||
            episodesFetchStatus is FetchStatusUiModel.Loading

    return HomeViewStateUiModel(
        isRefreshing = isLoading,
        onRefreshTriggered = onRefreshTriggered,
        topBarActionViewState = topBarActionViewState,
        onboardingViewState = onboardingViewState,
        characterListViewState = characterListViewState,
        episodeListViewState = episodeListViewState,
    )
}

private fun getTopBarActionViewState(
    hasSeenOnboarding: Boolean,
    shouldShowOnboardingFromUser: Boolean,
    onTopBarActionHelpClicked: () -> Unit,
    onTopBarActionCloseClicked: (Boolean) -> Unit,
): HomeTopBarActionViewStateUiModel =
    when {
        !hasSeenOnboarding -> HomeTopBarActionViewStateUiModel.Close(
            onClick = { onTopBarActionCloseClicked(false) }
        )

        shouldShowOnboardingFromUser -> HomeTopBarActionViewStateUiModel.Close(
            onClick = { onTopBarActionCloseClicked(true) }
        )

        else -> HomeTopBarActionViewStateUiModel.Help(
            onClick = onTopBarActionHelpClicked
        )
    }

private fun getOnboardingViewState(
    hasSeenOnboarding: Boolean,
    shouldShowOnboardingFromUser: Boolean,
): HomeOnboardingViewStateUiModel =
    when {
        !hasSeenOnboarding || shouldShowOnboardingFromUser -> {
            HomeOnboardingViewStateUiModel.Visible(
                overlayColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Color.Transparent
                } else {
                    Color.Black.copy(alpha = 0.5f)
                },
            )
        }

        else -> HomeOnboardingViewStateUiModel.Hidden
    }

private fun getCharacterListViewState(
    characters: List<CharacterDomainModel>,
    fetchStatus: FetchStatusUiModel,
    onCharacterClicked: (Int) -> Unit,
): HomeCharacterListViewStateUiModel =
    when {
        characters.isEmpty() -> {
            if (fetchStatus is FetchStatusUiModel.Error) {
                HomeCharacterListViewStateUiModel.Error
            } else {
                HomeCharacterListViewStateUiModel.Empty
            }
        }

        else -> {
            HomeCharacterListViewStateUiModel.Filled(
                characters = characters.map {
                    it.toUiModel(
                        onClick = { characterId -> onCharacterClicked(characterId) }
                    )
                }.toPersistentList()
            )
        }
    }

fun CharacterDomainModel.toUiModel(
    onClick: (Int) -> Unit,
) = CharacterUiModel(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    onClick = onClick,
)

private fun getEpisodeListViewState(
    episodes: List<EpisodeDomainModel>,
    fetchStatus: FetchStatusUiModel,
    onEpisodeClicked: (Int) -> Unit,
): HomeEpisodeListViewStateUiModel =
    when {
        episodes.isEmpty() -> {
            if (fetchStatus is FetchStatusUiModel.Error) {
                HomeEpisodeListViewStateUiModel.Error
            } else {
                HomeEpisodeListViewStateUiModel.Empty
            }
        }

        else -> {
            HomeEpisodeListViewStateUiModel.Filled(
                episodes = episodes.map {
                    it.toUiModel(
                        onClick = { episodeId -> onEpisodeClicked(episodeId) }
                    )
                }.toPersistentList()
            )
        }
    }

fun EpisodeDomainModel.toUiModel(
    onClick: (Int) -> Unit,
) = EpisodeUiModel(
    id = this.id,
    name = this.episode,
    onClick = onClick,
)
