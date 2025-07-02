package com.gromo.masterdetailshowcase.features.home.presentation.mappers

import android.os.Build
import androidx.compose.ui.graphics.Color
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.features.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.features.home.domain.models.HomeDataDomainModel
import com.gromo.masterdetailshowcase.features.home.presentation.HomeViewModel.FetchStatusUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.EpisodeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeCharacterListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeEpisodeListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionDataUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionTypeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.collections.immutable.toPersistentList
import com.gromo.masterdetailshowcase.libraries.design.R.drawable as drawables

fun toHomeViewState(
    onRefreshTriggered: () -> Unit,
    hasSeenOnboarding: Boolean,
    onTopBarActionClicked: (HomeTopBarActionTypeUiModel) -> Unit,
    shouldShowOnboardingFromUser: Boolean,
    data: HomeDataDomainModel,
    charactersFetchStatus: FetchStatusUiModel,
    episodesFetchStatus: FetchStatusUiModel,
    onCharacterClicked: (Int) -> Unit,
    onEpisodeClicked: (Int) -> Unit,
): HomeViewStateUiModel {
    val topBarActionData = getTopBarActionData(
        hasSeenOnboarding = hasSeenOnboarding,
        shouldShowOnboardingFromUser = shouldShowOnboardingFromUser,
        onTopBarActionClicked = onTopBarActionClicked,
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
        topBarActionData = topBarActionData,
        onboardingViewState = onboardingViewState,
        characterListViewState = characterListViewState,
        episodeListViewState = episodeListViewState,
    )
}

private fun getTopBarActionData(
    hasSeenOnboarding: Boolean,
    shouldShowOnboardingFromUser: Boolean,
    onTopBarActionClicked: (HomeTopBarActionTypeUiModel) -> Unit,
): HomeTopBarActionDataUiModel {

    val (iconResId, type) = when {
        !hasSeenOnboarding -> drawables.ic_close_24 to HomeTopBarActionTypeUiModel.Close(
            openedByUser = false
        )

        shouldShowOnboardingFromUser -> drawables.ic_close_24 to HomeTopBarActionTypeUiModel.Close(
            openedByUser = true
        )

        else -> drawables.ic_help_outline_24 to HomeTopBarActionTypeUiModel.Help
    }

    return HomeTopBarActionDataUiModel(
        iconResId = iconResId,
        type = type,
        onClick = onTopBarActionClicked,
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
                        onClick = onCharacterClicked
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
                        onClick = onEpisodeClicked
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
