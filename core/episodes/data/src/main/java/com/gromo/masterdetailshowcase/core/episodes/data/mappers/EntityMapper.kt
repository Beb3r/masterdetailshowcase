package com.gromo.masterdetailshowcase.core.episodes.data.mappers

import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.core.persistence.api.entities.EpisodeEntityModel

fun EpisodeDomainModel.toEntityModel(): EpisodeEntityModel =
    EpisodeEntityModel(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
    )

fun EpisodeEntityModel.toDomainModel(): EpisodeDomainModel =
    EpisodeDomainModel(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
    )
