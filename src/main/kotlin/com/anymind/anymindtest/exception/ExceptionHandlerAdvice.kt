package com.anymind.anymindtest.exception

import com.anymind.anymindtest.resource.rest.dto.ErrorDTO
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandlerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [Exception::class])
    fun handleIllegalArgumentException(
        ex: RuntimeException,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        return handleExceptionInternal(ex, ErrorDTO(status, ex),
                HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}
