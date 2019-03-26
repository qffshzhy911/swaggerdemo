package com.swagger.swaggerdemo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.User;

import java.util.*;


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

    @ApiOperation(value = "獲取單個用戶", notes = "根據ID獲取單個用戶")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "查询类型", name = "status",paramType = "query"),
            @ApiImplicitParam(value = "查询的用户参数", name = "user",paramType = "query"),
            @ApiImplicitParam(value = "开始时间", name = "start",paramType = "query"),
            @ApiImplicitParam(value = "结束时间", name = "end",paramType = "query")
    })
    @PostMapping("/byuser")
    public User getUserByUser(@RequestParam(value = "status[]" ,required = false) List<Integer> status,User user,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                              @DateTimeFormat(pattern ="yyyy-MM-dd") Date end
                             ){
        System.err.println(user);
        return user;
    }


    @ApiOperation(value = "添加用户", notes = "添加單個用戶")
    @ApiImplicitParam(value = "用户对象", paramType = "query")
    @PostMapping("/adduser")
    public boolean add(User user){
        return userList.add(user);
    }
}
