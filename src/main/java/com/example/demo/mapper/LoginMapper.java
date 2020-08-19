package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;

/**
 * 用户模块
 * @author 99200
 */
public interface LoginMapper extends BaseMapper<User> {

    /**
     * 查询用户名是否存在
     * @param userAccount
     * @return
     */
    User selectByUserName(String userAccount);

    /**
     * 查询用户手机号是否存在
     * @param phone
     * @return
     */
    User selectByPhone(String phone);
}
