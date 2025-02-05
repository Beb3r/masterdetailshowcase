package com.gromo.masterdetailshowcase.libraries.persistence.api.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class EpisodeEntityModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
)
