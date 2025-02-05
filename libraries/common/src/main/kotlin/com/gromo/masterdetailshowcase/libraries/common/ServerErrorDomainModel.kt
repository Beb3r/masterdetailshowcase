package com.gromo.masterdetailshowcase.libraries.common

class ServerErrorDomainModel(errorCode: Int): Throwable("Server error $errorCode")
