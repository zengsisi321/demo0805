package com.example.demo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 图形验证码DTO
 * @author 99200
 */
@Data
@ApiModel("图形验证码")
public class CaptchaDTO {


    @ApiModelProperty("图形验证码-key")
    private String captchaKey;

    @ApiModelProperty("图形验证码-Img（base64位）")
    private String captchaImg;
}
