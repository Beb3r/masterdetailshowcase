package com.gromo.masterdetailshowcase.features.characters.domain.models.errors

class CharacterParsingErrorDomainModel(errorMessage: String?) :
    Throwable("Error parsing characters $errorMessage")
