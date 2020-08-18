package com.example.demo.common.token;

import com.example.demo.common.exception.ReportServiceException;
import com.example.demo.common.exception.ReportSystemErrorCode;
import com.example.demo.common.redis.RedisConstant;
import com.example.demo.common.redis.RedisService;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * token拦截器
 */
@Component
@AllArgsConstructor
@Slf4j
public class TokenInterceptor extends HandlerFunctionAdapter {

    private final RedisService redisService;
    private final LoginService loginService;


//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
//
//        //如果对应controller的url，Handler是HandlerMethod
//        //否则是ResourceHttpRequestHandler
//        if(handler instanceof ResourceHttpRequestHandler){
//            return true;
//        }
//        //跳过不拦截的url
//        if(!hitUrl(handler)){
//            return true;
//        }
//
//        /**
//         * 验证token
//         */
//        String token=request.getHeader("token");
//        token=token!=null?token:request.getParameter("token");
//        if(token==null){
//            throw new ReportServiceException(ReportSystemErrorCode.TOKEN_NULL);
//        }
//        String userId=redisService.get(RedisConstant.getGlobalKey(token.trim()));
//        if(userId==null){
//            throw new ReportServiceException(ReportSystemErrorCode.TOKEN_EXPIRE);
//        }
//
//        //设置上下文
//        User user=loginService.getById(Integer.parseInt(userId));
//        user.setUserPassword(null);
//        UserContext.setUser(user);
//
//        //刷新token
//        redisService.expire(token,RedisConstant.EXIST_DAY_7);
//
//    }


//    /**
//     * 拦截有Login注解的  有Login注解的就拦截让登录
//     * @param handler
//     * @return
//     */
//    private boolean hitUrl(Object handler){
//        if(handler instanceof HandlerMethod){
//            HandlerMethod handlerMethod=(HandlerMethod) handler;
//            Method method=handlerMethod.getMethod();
//            Login login=method.getAnnotation(Login.class);
//            return login!=null;
//        }
//    }


}
