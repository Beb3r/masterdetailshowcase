package com.gromo.masterdetailshowcase.features.episode_details.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class EpisodeDetailsViewStateUiModel(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String,
    val characters: PersistentList<String>,
) {
    companion object {
        val DEFAULT = EpisodeDetailsViewStateUiModel(
            id = -1,
            name = "-",
            episode = "-",
            airDate = "-",
            characters = persistentListOf(),
        )
    }
}
