package com.example.demo.entity;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 前后端交互
 * @author 99200
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long code = 0L;
    private Long count;
    /**
     * 状态
     */
    private Integer state = 200;
    private String msg = "请求成功";
    private Object data;

    private boolean success = true;

    /**
     * 请求通过
     */
    public static final int SUCCESS = 200;
    /**
     * 请求无效
     */
    public static final int ERROR_BAD_REQUEST = 400;
    /**
     * 禁止访问
     */
    public static final int ERROR_FORBIDDEN = 403;
    /**
     * 无法找到文件
     */
    public static final int ERROR_NOT_FOUND = 404;
    /**
     * 资源被禁止
     */
    public static final int ERROR_METHOD_NOT_ALLOWED = 405;
    /**
     * 请求超时
     */
    public static final int ERROR_TIMEOUT = 408;
    /**
     * 服务器异常
     */
    public static final int ERROR_SERVER = 500;


    public static Result ok(){
        return ok(null);
    }

    public static Result ok(Object data){
        return new Result().setData(data);
    }

    public static Result error(int state, String msg){
        return error(state, msg,null);
    }

    public static Result error(int state, String msg, Object data){
        return new Result().setState(state).setSuccess(false).setMsg(msg).setData(data);
    }

}

