package com.gromo.masterdetailshowcase.features.home.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import java.util.Objects

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

    @Immutable
    data class Help(val onClick: () -> Unit) : HomeTopBarActionViewStateUiModel

    @Immutable
    data class Close(val onClick: () -> Unit) : HomeTopBarActionViewStateUiModel
}

@Immutable
sealed interface HomeCharacterListViewStateUiModel {

    data object Error : HomeCharacterListViewStateUiModel

    data object Empty : HomeCharacterListViewStateUiModel

    @Immutable
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
) {
    override fun hashCode(): Int = Objects.hash(id, name, imageUrl)

    override fun equals(other: Any?): Boolean =
        other is CharacterUiModel && other.id == id && other.name == name && other.imageUrl == imageUrl
}

@Immutable
sealed interface HomeOnboardingViewStateUiModel {
    data object Hidden : HomeOnboardingViewStateUiModel
    data object Visible : HomeOnboardingViewStateUiModel
}
