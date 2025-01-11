package com.gromo.masterdetailshowcase.core.network.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryApiModel(
    @SerialName("cca3")
    val id: String? = null,
    val name: CountryNameApiModel? = null,
    val independent: Boolean? = null,
    val unMember: Boolean? = null,
    val capital: List<String>? = null,
    val altSpellings: List<String>? = null,
    val continents: List<String>? = null,
    val region: String? = null,
    val subregion: String? = null,
    val flags: FlagsApiModel? = null,
    val area: Float? = null,
    val population: Int? = null,
)

@Serializable
data class CountryNameApiModel(
    val common: String? = null,
    val official: String? = null,
)

@Serializable
data class FlagsApiModel(
    val pngUrl: String? = null,
)
