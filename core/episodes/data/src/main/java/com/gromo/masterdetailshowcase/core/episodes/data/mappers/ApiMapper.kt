package com.gromo.masterdetailshowcase.core.episodes.data.mappers

import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel.Companion.DEFAULT_AIR_DATE
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel.Companion.DEFAULT_CHARACTERS
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel.Companion.DEFAULT_EPISODE
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel.Companion.DEFAULT_NAME
import com.gromo.masterdetailshowcase.core.episodes.domain.models.EpisodeDomainModel.Companion.DEFAULT_URL
import com.gromo.masterdetailshowcase.core.network.api.models.EpisodeApiModel

fun EpisodeApiModel?.toDomainModel(): EpisodeDomainModel? =
    // discarding items with no id
    this?.id?.let {
        EpisodeDomainModel(
            id = it,
            name = this.name ?: DEFAULT_NAME,
            airDate = this.airDate ?: DEFAULT_AIR_DATE,
            episode = this.episode ?: DEFAULT_EPISODE,
            characters = this.characters ?: DEFAULT_CHARACTERS,
            url = this.url ?: DEFAULT_URL,
        )
    }
