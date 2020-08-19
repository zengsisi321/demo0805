package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * 忘记密码request
 * @author 99200
 */
@Data
public class ForgetPassWordRequest {

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("短信验证码")
    @NotBlank(message = "短信验证码不能为空")
    private String smsCoed;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^[0-9,A-z]{6,20}$", message = "密码格式：6-20个字符，由数字或字母组成")
    private String newPassword;

}
