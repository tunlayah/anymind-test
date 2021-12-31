package com.anymind.anymindtest.resource.rest.dto

import java.lang.RuntimeException
import org.springframework.http.HttpStatus

class ErrorDTO(status: HttpStatus, exception: RuntimeException) {

    var status: Int? = null
    var error: String? = null
    var message: String? = null
    var trace: String? = null

    init {
        this.status = status.ordinal
        this.error = status.name
        this.message = exception.message
        this.trace = exception.stackTrace.joinToString("\n ")
    }
}
