package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@Data
public class TokenDOT {


     @ApiModelProperty("用户id")
     private Integer userId;

     @ApiModelProperty("token")
     private String token;

     @ApiModelProperty("过期时间 每次带token请求接口都会刷新")
     private Date expires;

}
