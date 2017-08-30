package org.lord.scaffold.controllers

import io.swagger.annotations.ApiOperation
import org.lord.scaffold.exception.GlobalExceptionHandler
import org.lord.scaffold.services.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import springfox.documentation.annotations.ApiIgnore

/**
 *
 * Created by Yuan Chaochao on 2017/8/27
 */
@RestController
@RequestMapping("/hello")
class HelloController {

    @Autowired
    HelloService helloService

    @ApiIgnore
    @RequestMapping(value = "/non-blocking", method = RequestMethod.GET)
    DeferredResult<String> index() {
        def deferredResult = new DeferredResult<String>()
        helloService.sleepForAWhile(10000, deferredResult)
        deferredResult
    }

    @ApiOperation(value = "统一异常处理", notes = "测试异常处理，返回json格式")
    @GetMapping("/exception")
    String exception() {
        throw new GlobalExceptionHandler.InternalServerError("Internal server exception!")
    }

}
