package com.gromo.masterdetailshowcase.core.countries.domain.models.errors

class NetworkErrorDomainModel(errorCode: Int): Throwable("Server error $errorCode")
