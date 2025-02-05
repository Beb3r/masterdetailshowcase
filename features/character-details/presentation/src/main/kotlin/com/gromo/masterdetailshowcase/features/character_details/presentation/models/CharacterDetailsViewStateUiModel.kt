package com.gromo.masterdetailshowcase.features.character_details.presentation.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CharacterDetailsViewStateUiModel(
    val id: Int,
    val topBarColor: Color,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String,
    val episodes: PersistentList<String>,
) {
    companion object {
        val DEFAULT = CharacterDetailsViewStateUiModel(
            id = -1,
            topBarColor = Color.Transparent,
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
