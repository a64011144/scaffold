package org.lord.scaffold.controllers

import io.swagger.annotations.ApiOperation
import org.lord.scaffold.exception.GlobalExceptionHandler
import org.lord.scaffold.services.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    String index() {
        println "[HelloController] " + Thread.currentThread().name

        helloService.sleepForAWhile(10000)

        "Hello World"
    }

    @ApiOperation(value = "统一异常处理", notes = "测试异常处理，返回json格式")
    @GetMapping("/exception")
    String exception() {
        println "/exception" + Thread.currentThread().name
        try {
            throw new GlobalExceptionHandler.InternalServerError("Internal server exception!")
        } catch (Exception e) {
            println 'exception block'
        }
        println 'finally!'
    }

}
