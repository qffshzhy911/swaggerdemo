package com.swagger.swaggerdemo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Api(value = "用户測試模块", description = "用戶模塊接口")
public class UserController {
    public static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("Albert", "123456"));
        userList.add(new User("Back", "123456"));
    }

    @ApiOperation(value = "獲得用戶列表", notes = "用戶列表")
    @GetMapping("users")
    public Object users() {
        Map<String, Object> map = new HashMap<>();
        map.put("users", userList);
        return map;
    }

    @ApiOperation(value = "獲取單個用戶", notes = "根據ID獲取單個用戶")
    @ApiImplicitParam(value = "用戶ID", paramType = "path")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id")int id){
        return userList.get(id);
    }
}
