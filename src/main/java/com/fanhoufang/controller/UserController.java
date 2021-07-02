package com.fanhoufang.controller;


import com.fanhoufang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("index")
    public Object index(){
        return UserService.getById(1L);
    }

}
