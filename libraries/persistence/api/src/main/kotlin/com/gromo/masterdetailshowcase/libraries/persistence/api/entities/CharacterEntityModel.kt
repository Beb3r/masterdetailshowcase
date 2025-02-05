package com.gromo.masterdetailshowcase.libraries.persistence.api.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntityModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String,
    val episodes: List<String>,
)
