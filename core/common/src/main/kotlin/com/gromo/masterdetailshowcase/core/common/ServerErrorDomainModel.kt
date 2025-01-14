package com.gromo.masterdetailshowcase.core.common

class ServerErrorDomainModel(errorCode: Int): Throwable("Server error $errorCode")
