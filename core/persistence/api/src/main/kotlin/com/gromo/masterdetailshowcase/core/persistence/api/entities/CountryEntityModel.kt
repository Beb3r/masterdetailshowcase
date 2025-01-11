package com.gromo.masterdetailshowcase.core.persistence.api.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntityModel(
    @PrimaryKey
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
)
