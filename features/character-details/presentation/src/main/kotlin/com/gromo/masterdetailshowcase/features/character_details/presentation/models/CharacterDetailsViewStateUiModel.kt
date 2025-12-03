package com.gromo.masterdetailshowcase.features.character_details.presentation.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Immutable
data class CharacterDetailsViewStateUiModel(
    val topBarColor: Color,
    val navigationIconViewState: CharacterDetailsNavigationIconViewStateUiModel,
    val title: String,
    val body: CharacterDetailsBodyUiModel,

    ) {
    companion object {
        val DEFAULT = CharacterDetailsViewStateUiModel(
            topBarColor = Color.Transparent,
            navigationIconViewState = CharacterDetailsNavigationIconViewStateUiModel.Hidden,
            title = "-",
            body = CharacterDetailsBodyUiModel.DEFAULT,
        )
    }
}

@Immutable
sealed interface CharacterDetailsNavigationIconViewStateUiModel {
    data object Hidden : CharacterDetailsNavigationIconViewStateUiModel

    @Immutable
    data class Visible(
        val onBackClicked: () -> Unit,
    ) : CharacterDetailsNavigationIconViewStateUiModel
}

@OptIn(ExperimentalContracts::class)
fun CharacterDetailsNavigationIconViewStateUiModel.isVisible(): Boolean {
    contract {
        returns(true) implies (this@isVisible is CharacterDetailsNavigationIconViewStateUiModel.Visible)
    }
    return this is CharacterDetailsNavigationIconViewStateUiModel.Visible
}

@Immutable
data class CharacterDetailsBodyUiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String,
    val episodes: PersistentList<String>,
) {
    companion object Companion {
        val DEFAULT = CharacterDetailsBodyUiModel(
            id = -1,
            name = "-",
            status = "-",
            species = "-",
            type = "-",
            gender = "-",
            imageUrl = "",
            episodes = persistentListOf(),
        )
    }
}
