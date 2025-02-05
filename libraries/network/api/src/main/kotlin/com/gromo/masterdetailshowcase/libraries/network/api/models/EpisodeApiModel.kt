package com.gromo.masterdetailshowcase.libraries.network.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeApiModel(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("air_date")
    val airDate: String? = null,
    val episode: String? = null,
    val characters: List<String>? = null,
    val url: String? = null,
)