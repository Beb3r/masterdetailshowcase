package com.gromo.masterdetailshowcase.core.characters.domain.models

data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String,
    val episodes: List<String>,
) {

    companion object {
        const val DEFAULT_NAME = ""
        const val DEFAULT_STATUS = ""
        const val DEFAULT_SPECIES = ""
        const val DEFAULT_TYPE = ""
        const val DEFAULT_GENDER = ""
        const val DEFAULT_IMAGE_URL = ""
        val DEFAULT_EPISODES = listOf<String>()
    }
}
