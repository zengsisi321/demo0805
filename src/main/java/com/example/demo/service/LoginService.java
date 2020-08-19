package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.TokenDOT;
import com.example.demo.entity.User;
import com.example.demo.request.ForgetPassWordRequest;
import com.example.demo.request.LoginByPhoneRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.request.SendSmsRequest;

/**
 * 用户模块
 * @author 99200
 */
public interface LoginService extends IService<User> {


    /**
     * 注册
     * @param registerRequest
     * @return
     */
    void register(RegisterRequest registerRequest);


    /**
     * 发送手机短信
     * @param sendSmsRequest
     */
    void sendSms(SendSmsRequest sendSmsRequest);

    /**
     * 账号/手机号登录
     * @param loginRequest
     * @return token
     */
    TokenDOT loginByUser(LoginRequest loginRequest);

    /**
     * 手机验证码登录
     * @param loginByPhoneRequest
     * @return
     */
    TokenDOT loginByPhone(LoginByPhoneRequest loginByPhoneRequest);


    /**
     * 忘记密码
     * @param forgetPassWordRequest
     */
    void forgetPassword(ForgetPassWordRequest forgetPassWordRequest);
}
