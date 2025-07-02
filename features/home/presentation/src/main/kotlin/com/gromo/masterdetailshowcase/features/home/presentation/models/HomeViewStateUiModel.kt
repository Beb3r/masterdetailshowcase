package com.gromo.masterdetailshowcase.features.home.presentation.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList
import com.gromo.masterdetailshowcase.libraries.design.R.drawable as drawables

@Immutable
data class HomeViewStateUiModel(
    val isRefreshing: Boolean,
    val onRefreshTriggered: () -> Unit,
    val topBarActionData: HomeTopBarActionDataUiModel,
    val onboardingViewState: HomeOnboardingViewStateUiModel,
    val characterListViewState: HomeCharacterListViewStateUiModel,
    val episodeListViewState: HomeEpisodeListViewStateUiModel,
) {

    companion object {
        val DEFAULT = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionData = HomeTopBarActionDataUiModel(
                iconResId = drawables.ic_help_outline_24,
                type = HomeTopBarActionTypeUiModel.Help,
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
            episodeListViewState = HomeEpisodeListViewStateUiModel.Empty,
        )
    }
}

@Immutable
data class HomeTopBarActionDataUiModel(
    @DrawableRes
    val iconResId: Int,
    val type: HomeTopBarActionTypeUiModel,
    val onClick: (HomeTopBarActionTypeUiModel) -> Unit,
)

@Immutable
sealed interface HomeTopBarActionTypeUiModel {
    data object Help : HomeTopBarActionTypeUiModel
    data class Close(val openedByUser: Boolean) : HomeTopBarActionTypeUiModel
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
)

@Immutable
sealed interface HomeEpisodeListViewStateUiModel {

    data object Error : HomeEpisodeListViewStateUiModel

    data object Empty : HomeEpisodeListViewStateUiModel

    @Immutable
    data class Filled(
        val episodes: PersistentList<EpisodeUiModel>
    ) : HomeEpisodeListViewStateUiModel
}

@Immutable
data class EpisodeUiModel(
    val id: Int,
    val name: String,
    val onClick: (Int) -> Unit,
)

@Immutable
sealed interface HomeOnboardingViewStateUiModel {
    data object Hidden : HomeOnboardingViewStateUiModel

    @Immutable
    data class Visible(val overlayColor: Color) : HomeOnboardingViewStateUiModel
}
