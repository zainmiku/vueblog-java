package com.fanhoufang.controller;


import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.User;
import com.fanhoufang.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService UserService;

    @RequiresAuthentication
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Result index(){
        User user = UserService.getById(1L);
        return Result.success(user);
    }

}
