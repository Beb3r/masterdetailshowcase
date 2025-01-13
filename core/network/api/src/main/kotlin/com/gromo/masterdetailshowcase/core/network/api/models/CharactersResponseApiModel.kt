package com.gromo.masterdetailshowcase.core.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseApiModel(
    val results: List<CharacterApiModel>? = null,
)
