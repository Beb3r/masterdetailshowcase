package com.gromo.masterdetailshowcase.features.home.domain.models

import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.features.episodes.domain.models.EpisodeDomainModel

data class HomeDataDomainModel(
    val characters: List<CharacterDomainModel>,
    val episodes: List<EpisodeDomainModel>,
)
