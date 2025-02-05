package com.gromo.masterdetailshowcase.libraries.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponseApiModel(
    val results: List<EpisodeApiModel>? = null,
)