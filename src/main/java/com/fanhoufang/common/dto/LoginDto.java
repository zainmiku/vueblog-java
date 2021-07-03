package com.fanhoufang.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: vueblog-java
 * @description: 登录DTO 用于接收账号密码
 * @author: fan
 * @create: 2021-07-03 18:16
 **/
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空!")
    private String username;

    @NotBlank(message = "密码不能为空!")
    private String password;
}
