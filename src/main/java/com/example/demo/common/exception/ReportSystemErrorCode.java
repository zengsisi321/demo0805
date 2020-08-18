package com.example.demo.common.exception;

import lombok.Getter;

/**
 * 错误码枚举类
 * @author 99200
 */
public enum ReportSystemErrorCode {
    /**
     * 权限不足报错
     */
    NOT_PERMISSION("NOT_PERMISSION", "权限不足,请联系管理员!"),
    /**
     * 账号或密码错误
     */
    USER_ERROR("USER_ERROR", "账号或密码输入错误!"),
    /**
     * 接口访问异常
     */
    API_ERROR("API5001", "接口访问异常"),
    /**
     * 接口返回错误
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常，请联系管理员"),

    //自定义错误
    TOKEN_NULL("100001", "token为空"),
    TOKEN_EXPIRE("100002", "token已过期"),
    REGISTER_RE_PASSWORD("100003", "两次密码不一致"),
    REGISTER_USERNAME_EXIST("100004", "用户名已注册"),
    REGISTER_PHONE_EXIST("100005", "手机号已注册"),
    REGISTER_CAPTCHA_INVALID("100006", "图形验证码无效"),
    REGISTER_CAPTCHA_ERROR("100007", "图形验证码不正确"),
    REGISTER_SMS_INVALID("100008", "短信验证码无效"),
    REGISTER_SMS_ERROR("100009", "短信验证码不正确"),
    LOGIN_BY_USER_ERROR("100010", "账号或密码错误"),
    PASSWORD_OLD_ERROR("100011", "旧密码不正确"),
    ;


    /**
     * 错误码
     */
    @Getter
    String code;
    /**
     * 错误描述
     */
    @Getter
    String desc;

    ReportSystemErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据错误码返回错误描述
     */
    public static String getErrorMsg(String errorCode) {
        for (ReportSystemErrorCode element : ReportSystemErrorCode.values()) {
            if (element.getCode().equalsIgnoreCase(errorCode)) {
                return element.getDesc();
            }
        }
        return SYSTEM_ERROR.getDesc();
    }

    public static String getErrorCode(String errorCode) {
        for (ReportSystemErrorCode element : ReportSystemErrorCode.values()) {
            if (element.getCode().equalsIgnoreCase(errorCode)) {
                return element.getCode();
            }
        }
        return SYSTEM_ERROR.getDesc();
    }

}
