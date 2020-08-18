package com.example.demo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * 注册参数
 * @author 99200
 */

@Data
@ApiModel("注册参数")
public class RegisterRequest {

    @ApiModelProperty("用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String userAccount;

    @ApiModelProperty("用户手机号")
    @NotBlank(message = "手机号 不能为空")
    @Pattern(regexp = "`^1[0-9]{10}$", message = "手机号格式错误")
    private String userPhone;

    @ApiModelProperty("用户密码")
    @NotBlank(message = "密码 不能为空")
    @Pattern(regexp = "^[0-9,A-z]{6,20}$", message = "密码格式：6-20个字符，由数字或字母组成")
    private String userPassword;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "确认密码 不能为空")
    private String pass;

    @ApiModelProperty("用户邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "/^\\w{3,12}@\\w{1,5}\\.[a-z]{2,3}$/",message = "邮箱格式：abc@qq.com")
    private String userMail;

    @ApiModelProperty("短信验证码")
    @NotBlank(message = "短信验证码 不能为空")
    private String smsCode;




}
