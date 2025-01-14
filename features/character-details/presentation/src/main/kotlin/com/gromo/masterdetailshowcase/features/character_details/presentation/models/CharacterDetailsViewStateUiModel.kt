package com.gromo.masterdetailshowcase.features.character_details.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CharacterDetailsViewStateUiModel(
    val id: Int,
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
