package com.gromo.masterdetailshowcase.core.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponseApiModel(
    val results: List<EpisodeApiModel>? = null,
)