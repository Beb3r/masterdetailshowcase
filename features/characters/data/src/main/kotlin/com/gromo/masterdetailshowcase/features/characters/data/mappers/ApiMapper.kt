package com.gromo.masterdetailshowcase.features.characters.data.mappers

import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_EPISODES
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_GENDER
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_IMAGE_URL
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_NAME
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_SPECIES
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_STATUS
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel.Companion.DEFAULT_TYPE
import com.gromo.masterdetailshowcase.libraries.network.api.models.CharacterApiModel

fun CharacterApiModel?.toDomainModel(): CharacterDomainModel? =
    // discarding items with no id
    this?.id?.let {
        CharacterDomainModel(
            id = it,
            name = this.name ?: DEFAULT_NAME,
            status = this.status ?: DEFAULT_STATUS,
            species = this.species ?: DEFAULT_SPECIES,
            type = this.type ?: DEFAULT_TYPE,
            gender = this.gender ?: DEFAULT_GENDER,
            imageUrl = this.image ?: DEFAULT_IMAGE_URL,
            episodes = this.episode ?: DEFAULT_EPISODES,
        )
    }
