package com.gromo.masterdetailshowcase.core.common

class NetworkErrorDomainModel(errorCode: Int): Throwable("Server error $errorCode")
