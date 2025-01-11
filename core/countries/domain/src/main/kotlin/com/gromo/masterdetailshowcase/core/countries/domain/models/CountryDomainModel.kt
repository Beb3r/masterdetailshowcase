package com.gromo.masterdetailshowcase.core.countries.domain.models

data class CountryDomainModel(
    val id: String,
    val nameCommon: String,
    val nameOfficial: String,
    val independent: Boolean,
    val unMember: Boolean,
    val capital: List<String>,
    val altSpellings: List<String>,
    val continents: List<String>,
    val region: String,
    val subregion: String,
    val flagUrl: String,
    val area: Float,
    val population: Int,
) {
    companion object {
        const val DEFAULT_NAME_COMMON = ""
        const val DEFAULT_NAME_OFFICIAL = ""
        const val DEFAULT_INDEPENDENT = false
        const val DEFAULT_UN_MEMBER = false
        val DEFAULT_CAPITAL = listOf<String>()
        val DEFAULT_ALT_SPELLINGS = listOf<String>()
        val DEFAULT_CONTINENTS = listOf<String>()
        const val DEFAULT_REGION = ""
        const val DEFAULT_SUBREGION = ""
        const val DEFAULT_FLAG_URL = ""
        const val DEFAULT_AREA = 0f
        const val DEFAULT_POPULATION = 0
    }
}
