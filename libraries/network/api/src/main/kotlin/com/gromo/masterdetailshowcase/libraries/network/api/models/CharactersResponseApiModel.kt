package com.gromo.masterdetailshowcase.libraries.network.api.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseApiModel(
    val results: List<CharacterApiModel>? = null,
)
