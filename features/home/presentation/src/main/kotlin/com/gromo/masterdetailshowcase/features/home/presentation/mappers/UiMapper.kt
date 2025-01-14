package com.gromo.masterdetailshowcase.features.home.presentation.mappers

import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeCharacterListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.collections.immutable.toPersistentList

fun toHomeViewState(
    isRefreshing: Boolean,
    onRefreshTriggered: () -> Unit,
    hasSeenOnboarding: Boolean,
    onTopBarActionHelpClicked: () -> Unit,
    onTopBarActionCloseClicked: (Boolean) -> Unit,
    shouldShowOnboardingFromUser: Boolean,
    characters: List<CharacterDomainModel>,
    fetchError: Throwable?,
    onCharacterClicked: (Int) -> Unit,
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
    val characterListView = getCharacterListViewState(
        characters = characters,
        fetchError = fetchError,
        onCharacterClicked = onCharacterClicked,
    )

    return HomeViewStateUiModel(
        isRefreshing = isRefreshing,
        onRefreshTriggered = onRefreshTriggered,
        topBarActionViewState = topBarActionViewState,
        onboardingViewState = onboardingViewState,
        characterListViewState = characterListView,
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
        !hasSeenOnboarding || shouldShowOnboardingFromUser -> HomeOnboardingViewStateUiModel.Visible
        else -> HomeOnboardingViewStateUiModel.Hidden
    }

private fun getCharacterListViewState(
    characters: List<CharacterDomainModel>,
    fetchError: Throwable?,
    onCharacterClicked: (Int) -> Unit
): HomeCharacterListViewStateUiModel =
    when {
        characters.isEmpty() -> {
            if (fetchError != null) {
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
