package com.example.demo.common.token;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 设置客户上下文 ， 保存客户信息
 */
@Slf4j
public class UserContext {


    /**
     * 当前线程的客户信息
     */
    private static ThreadLocal<User> userThreadLocal=new ThreadLocal<>();


    /**
     * 获取客户id
     * @return
     */
    public static Integer getUserId(){
        return getUser().getUserId();
    }

    /**
     * 获取客户token
     * @return
     */
    public static String getToken(){
        return  getUser().getToken();
    }

    /**
     * 获取客户昵称
     * @return
     */
    public static String getUserAccount(){
        return getUser().getUserAccount();
    }

    /**
     * 获取用户手机号
     * @return
     */
    public static String getUserPhone(){
        return getUser().getUserPhone();
    }


    /**
     * 获取客户信息
     * @return
     */
    public static User getUser(){
     return userThreadLocal.get();
    }

    /**
     * 设置客户信息
     * @param user
     */
    public static void setUser(User user){
        userThreadLocal.set(user);
    }

    /**
     * 清楚数据
     */
    public static void clearUser(){
        userThreadLocal.remove();
    }


}
