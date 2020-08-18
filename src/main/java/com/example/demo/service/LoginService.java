package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.request.RegisterRequest;
import javax.xml.transform.Result;

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
}
