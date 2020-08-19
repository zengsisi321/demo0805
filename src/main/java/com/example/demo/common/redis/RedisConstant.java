package com.example.demo.common.redis;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * redis常量类
 * @author 99200
 */
@Data
@AllArgsConstructor
public class RedisConstant {

    /**
     * app
     */
    public final static String APP="demo0805";


    /**
     * 过期时间  30天  单位为秒
     */
    public final static long EXIST_DAY_30=60*60*24*30;


    /**
     * 过期时间  7天  单位为秒
     */
    public final static long EXIST_DAY_7=60*60*24*7;


    /**
     * 过期时间  1天  单位为秒
     */
    public final static long EXIST_DAY_1=60*60*24;


    /**
     * 过期时间  一小时  单位为秒
     */
    public final static long EXIST_HOUSE_2=60*60;


    /**
     * 过期时间 五分钟 单位为秒
     */
    public final static long EXIST_MIN_5=5*60;


    /**
     * 不设置过期时间
     */
    public final static long EXIST_FOREVER=-1;


    /**
     * 获取带全局前缀的key
     * @param key
     * @return
     */
    public static String getGlobalKey(String key){
        return String.format("%s-%s",APP,key);
    }


    /**
     * 客户token
     * @param token
     * @return  customer:token+UUID
     */
    public static String getTokenKey(String token){
        return String.format("customer:token:%s",token);
    }

    /**
     * 图形验证码
     * @param uuid
     * @return captcha:+key
     */
    public static String getCaptchaKey(String uuid){
        return String.format("captcha:%s",uuid);
    }


    /**
     * 短信验证码
     * @param phone
     * @return sms:+phone
     */
    public static String getSMSKey(String phone){
        return  String.format("sms:%s",phone);
    }

}
