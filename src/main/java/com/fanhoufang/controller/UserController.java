package com.fanhoufang.controller;


import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.User;
import com.fanhoufang.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanhoufang
 * @since 2021-07-02
 */
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    UserService userService;
 private UserService userService;

 public  void setUserService(UserService userService){
     this.userService = userService;
 }


    @RequiresAuthentication
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Result index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }
    @PostMapping(value = "save")
    public Result save(@Validated @RequestBody User user){

        return Result.success(user);
    }

}
