package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录request
 * @author 99200
 */
@Data
public class LoginRequest {


    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    private String userAccount;

    @ApiModelProperty("用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String passWord;

    @ApiModelProperty("图形验证码 key")
    @NotBlank(message = "图形验证码key不能为空")
    private String captchaKey;

    @ApiModelProperty("图形验证码 value")
    @NotBlank(message = "图形验证码不能为空")
    private String captchaValue;

}
