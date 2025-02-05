package com.gromo.masterdetailshowcase.core.episodes.domain.models.errors

class EpisodeParsingErrorDomainModel(errorMessage: String?) :
    Throwable("Error parsing episodes $errorMessage")