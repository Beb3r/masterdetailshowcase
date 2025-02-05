package com.gromo.masterdetailshowcase.features.home.presentation.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList
import java.util.Objects

@Immutable
data class HomeViewStateUiModel(
    val isRefreshing: Boolean,
    val onRefreshTriggered: () -> Unit,
    val topBarActionViewState: HomeTopBarActionViewStateUiModel,
    val onboardingViewState: HomeOnboardingViewStateUiModel,
    val characterListViewState: HomeCharacterListViewStateUiModel,
    val episodeListViewState: HomeEpisodeListViewStateUiModel,
) {

    companion object {
        val DEFAULT = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Help(
                fromDefault = true,
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
            episodeListViewState = HomeEpisodeListViewStateUiModel.Empty,
        )
    }
}

@Immutable
sealed interface HomeTopBarActionViewStateUiModel {

    @Immutable
    data class Help(val fromDefault: Boolean, val onClick: () -> Unit) :
        HomeTopBarActionViewStateUiModel {
        override fun hashCode(): Int = Objects.hash(fromDefault)

        override fun equals(other: Any?): Boolean =
            other is Help && other.fromDefault == fromDefault
    }

    @Immutable
    data class Close(val fromDefault: Boolean, val onClick: () -> Unit) :
        HomeTopBarActionViewStateUiModel {
        override fun hashCode(): Int = Objects.hash(fromDefault)

        override fun equals(other: Any?): Boolean =
            other is Close && other.fromDefault == fromDefault
    }
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
) {
    override fun hashCode(): Int = Objects.hash(id, name)

    override fun equals(other: Any?): Boolean =
        other is CharacterUiModel && other.id == id && other.name == name
}

@Immutable
sealed interface HomeOnboardingViewStateUiModel {
    data object Hidden : HomeOnboardingViewStateUiModel

    @Immutable
    data class Visible(val overlayColor: Color) : HomeOnboardingViewStateUiModel
}
