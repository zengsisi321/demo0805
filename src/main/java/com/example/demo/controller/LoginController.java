package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.request.RegisterRequest;
import com.example.demo.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户模块控制层
 * @author 99200
 */

@Controller
@ResponseBody
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(RegisterRequest registerRequest){
        loginService.register(registerRequest);
        return Result.ok("注册成功");
    }

}
