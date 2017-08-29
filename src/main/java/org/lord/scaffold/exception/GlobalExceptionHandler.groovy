package org.lord.scaffold.exception

import groovy.transform.InheritConstructors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import javax.servlet.http.HttpServletRequest

/**
 *
 * Created by Yuan Chaochao on 2017/8/27
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerError)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionInfo handleException(HttpServletRequest req, InternalServerError e) {
        handleException(e, req, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @InheritConstructors
    static class InternalServerError extends RuntimeException {}

    @ExceptionHandler(BadRequestException)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionInfo handleBadRequestException(HttpServletRequest req, BadRequestException e) {
        handleException(e, req, HttpStatus.BAD_REQUEST)
    }

    @InheritConstructors
    static class BadRequestException extends RuntimeException {}

    ExceptionInfo handleException(Exception e, HttpServletRequest req, HttpStatus status) {
        e.printStackTrace()

        def exceptionInfo = new ExceptionInfo()
        exceptionInfo.timestamp = System.currentTimeMillis()
        exceptionInfo.status = status.value()
        exceptionInfo.message = e.message
        exceptionInfo.path = req.requestURI

        exceptionInfo
    }

}
