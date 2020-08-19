package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发送短信request
 * @author 99200
 */
@Data
@AllArgsConstructor
public class SendSmsRequest {

      @ApiModelProperty("手机号")
      @NotBlank(message = "手机号不能为空")
      @Pattern(regexp = "`^1[0-9]{10}$", message = "请输入正确的手机号")
      private String phone;

      @ApiModelProperty("图形验证码 - key")
      @NotBlank(message = "图形验证码key 不能为空")
      private String captchaKey;

      @ApiModelProperty("图形验证码 -value")
      @NotBlank(message = "图形验证码 不能为空")
      private String captchaValue;

}
