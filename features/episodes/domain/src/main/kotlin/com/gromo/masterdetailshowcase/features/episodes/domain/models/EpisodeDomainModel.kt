package com.gromo.masterdetailshowcase.features.episodes.domain.models

data class EpisodeDomainModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
) {

    companion object {
        const val DEFAULT_NAME = "-"
        const val DEFAULT_AIR_DATE = "-"
        const val DEFAULT_EPISODE = "-"
        const val DEFAULT_URL = "-"
        val DEFAULT_CHARACTERS = listOf<String>()
    }
}