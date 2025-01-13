package com.gromo.masterdetailshowcase.features.home.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList

@Immutable
sealed interface HomeViewStateUiModel {

    abstract val isRefreshing: Boolean
    abstract val onRefreshTriggered: () -> Unit

    data class Error(
        override val isRefreshing: Boolean,
        override val onRefreshTriggered: () -> Unit
    ) : HomeViewStateUiModel

    data class Empty(
        override val isRefreshing: Boolean,
        override val onRefreshTriggered: () -> Unit
    ) : HomeViewStateUiModel

    data class Filled(
        override val isRefreshing: Boolean,
        override val onRefreshTriggered: () -> Unit,
        val characters: PersistentList<CharacterUiModel>
    ) : HomeViewStateUiModel
}

@Immutable
data class CharacterUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val onClick: (Int) -> Unit,
)
