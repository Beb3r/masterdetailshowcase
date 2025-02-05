package com.gromo.masterdetailshowcase.features.characters.data.mappers

import com.gromo.masterdetailshowcase.libraries.persistence.api.entities.CharacterEntityModel
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel

fun CharacterDomainModel.toEntityModel(): CharacterEntityModel =
    CharacterEntityModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        imageUrl = this.imageUrl,
        episodes = this.episodes,

    )

fun CharacterEntityModel.toDomainModel(): CharacterDomainModel =
    CharacterDomainModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        imageUrl = this.imageUrl,
        episodes = this.episodes,
    )
