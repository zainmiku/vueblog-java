package com.fanhoufang.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fanhoufang.common.dto.LoginDto;
import com.fanhoufang.common.lang.Result;
import com.fanhoufang.entity.User;
import com.fanhoufang.service.UserService;
import com.fanhoufang.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: vueblog-java
 * @description: 登录接口
 * @author: fan
 * @create: 2021-07-03 18:20
 **/
@Slf4j
@RestController
@Api(tags = "登录相关接口")
public class AccountController {
/*    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;*/

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AccountController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @ApiOperation(value = "用户登录接口",httpMethod = "POST")
    @PostMapping ("login")
    public Result login(@Validated @RequestBody LoginDto loginDto , HttpServletResponse response){
        log.info("登录接口入参--------------{}"+loginDto);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );

    }

    @RequiresAuthentication
    @ApiOperation(value = "用户注销接口",httpMethod = "POST")
    @PostMapping ("logout")
    public Result logout(){
        log.info("注销接口入参--------------{}");
        SecurityUtils.getSubject().logout();
        return Result.success(null);

    }
}
