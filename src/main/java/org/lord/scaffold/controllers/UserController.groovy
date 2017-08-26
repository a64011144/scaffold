package org.lord.scaffold.controllers

import groovy.util.logging.Slf4j
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.lord.scaffold.model.User
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

/**
 *
 * Created by Yuan Chaochao on 2017/8/26
 */
@RestController
// 通过这里配置使下面的映射都在/users下，可去除
@RequestMapping(value = "/users")
@Slf4j
class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表", notes = "")
    // 等价于@RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping("")
    List<User> getUserList() {
        log.info("getUserList in.")
        new ArrayList<User>(users.values())
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "", method = RequestMethod.POST)
    String postUser(@RequestBody User user) {
        users.put(user.getId(), user)
        "success"
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable Long id) {
        users.get(id)
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    ])
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id)
        u.setName(user.getName())
        u.setAge(user.getAge())
        users.put(id, u)
        "success"
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    String deleteUser(@PathVariable Long id) {
        users.remove(id)
        "success"
    }

    @ApiIgnore
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        "Hello World"
    }

}