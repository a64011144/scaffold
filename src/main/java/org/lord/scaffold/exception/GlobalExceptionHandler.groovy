package org.lord.scaffold.exception

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

    @ExceptionHandler(Exception)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionInfo exceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace()

        def errorInfo = new ExceptionInfo()
        errorInfo.message = e.message
        errorInfo.url = req.requestURL.toString()

        errorInfo
    }

}
