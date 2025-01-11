package com.gromo.masterdetailshowcase.core.countries.domain.models.errors

class CountryParsingErrorDomainModel(errorMessage: String?) :
    Throwable("Error parsing countries $errorMessage")
