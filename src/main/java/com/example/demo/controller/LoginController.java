package com.example.demo.controller;

import com.example.demo.common.redis.RedisConstant;
import com.example.demo.common.redis.RedisService;
import com.example.demo.dto.CaptchaDTO;
import com.example.demo.dto.TokenDOT;
import com.example.demo.entity.Result;
import com.example.demo.request.ForgetPassWordRequest;
import com.example.demo.request.LoginByPhoneRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.request.SendSmsRequest;
import com.example.demo.service.LoginService;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块控制层
 * @author 99200
 */

@RestController
@Api(tags = "注册登录")
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;


    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(RegisterRequest registerRequest){
        loginService.register(registerRequest);
        return Result.ok("注册成功");
    }


    @ApiOperation("发送短信")
    @GetMapping("/sendSms")
    public Result sendSms(SendSmsRequest sendSmsRequest){
        loginService.sendSms(sendSmsRequest);
        return  Result.ok("发送成功");
    }


    @ApiOperation(("获取图形验证码"))
    @GetMapping("/getImgCoed")
    public Result<CaptchaDTO> getImgCoed(){
        //生产验证码
        SpecCaptcha specCaptcha=new SpecCaptcha(130,48,5);
        String verCoed=specCaptcha.text().toLowerCase();
        String key= UUID.randomUUID().toString();

        //存入redis并设置过期时间为5分钟
        redisService.set(RedisConstant.getCaptchaKey(key),verCoed,RedisConstant.EXIST_MIN_5);

        //将key和base64返回给前端
        CaptchaDTO captchaDTO=new CaptchaDTO();
        captchaDTO.setCaptchaKey(key);
        captchaDTO.setCaptchaImg(specCaptcha.toBase64());

        return  Result.ok(captchaDTO);
    }


    @ApiOperation("登录(用户名/账号)")
    @PostMapping("/login")
    public Result<TokenDOT> login(LoginRequest loginRequest){
        loginService.loginByUser(loginRequest);
        return Result.ok("登录成功");

    }


    @ApiOperation("手机短信验证码登录")
    @PostMapping("/loginByPhone")
    public Result<TokenDOT> loginByPhone(LoginByPhoneRequest loginByPhoneRequest){
        loginService.loginByPhone(loginByPhoneRequest);
        return Result.ok("登录成功");
    }


    @ApiOperation("忘记密码")
    @PostMapping("/forgetPassword")
    public Result forgetPassword(ForgetPassWordRequest forgetPassWordRequest){

        loginService.forgetPassword(forgetPassWordRequest);

        return Result.ok("密码重设成功,请重新登录");

    }



}
