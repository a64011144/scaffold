package org.lord.scaffold.controllers

import io.swagger.annotations.ApiOperation
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

    @ApiIgnore
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        "Hello World"
    }

    @ApiOperation(value = "统一异常处理", notes = "测试异常处理，返回json格式")
    @GetMapping("/exception")
    String exception() {
        throw new RuntimeException("Exception throwable!")
    }

}
