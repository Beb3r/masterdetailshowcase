package com.gromo.masterdetailshowcase.core.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterApiModel(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val image: String? = null,
    val episode: List<String>? = null,
)
