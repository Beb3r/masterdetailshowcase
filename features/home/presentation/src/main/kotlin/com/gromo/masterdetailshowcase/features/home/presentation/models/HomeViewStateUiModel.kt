package com.gromo.masterdetailshowcase.features.home.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList

@Immutable
data class HomeViewStateUiModel(
    val isRefreshing: Boolean,
    val onRefreshTriggered: () -> Unit,
    val topBarActionViewState: HomeTopBarActionViewStateUiModel,
    val onboardingViewState: HomeOnboardingViewStateUiModel,
    val characterListViewState: HomeCharacterListViewStateUiModel,
) {
    companion object {
        val DEFAULT = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Help(onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
        )
    }
}

@Immutable
sealed interface HomeTopBarActionViewStateUiModel {
    data class Help(val onClick: () -> Unit) : HomeTopBarActionViewStateUiModel
    data class Close(val onClick: () -> Unit) : HomeTopBarActionViewStateUiModel
}

@Immutable
sealed interface HomeCharacterListViewStateUiModel {
    data object Error : HomeCharacterListViewStateUiModel

    data object Empty : HomeCharacterListViewStateUiModel

    data class Filled(
        val characters: PersistentList<CharacterUiModel>
    ) : HomeCharacterListViewStateUiModel
}

@Immutable
data class CharacterUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val onClick: (Int) -> Unit,
)

@Immutable
sealed interface HomeOnboardingViewStateUiModel {
    data object Hidden : HomeOnboardingViewStateUiModel
    data object Visible : HomeOnboardingViewStateUiModel
}
