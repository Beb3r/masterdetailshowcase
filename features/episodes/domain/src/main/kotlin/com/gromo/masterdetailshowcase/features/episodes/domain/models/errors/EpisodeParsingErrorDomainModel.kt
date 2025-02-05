package com.gromo.masterdetailshowcase.features.episodes.domain.models.errors

class EpisodeParsingErrorDomainModel(errorMessage: String?) :
    Throwable("Error parsing episodes $errorMessage")