package com.gromo.masterdetailshowcase.core.characters.domain.models.errors

class CharacterParsingErrorDomainModel(errorMessage: String?) :
    Throwable("Error parsing characters $errorMessage")
