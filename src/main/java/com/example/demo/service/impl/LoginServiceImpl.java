package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.exception.ReportServiceException;
import com.example.demo.common.exception.ReportSystemErrorCode;
import com.example.demo.common.redis.RedisService;
import com.example.demo.entity.User;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.common.redis.RedisConstant;
import com.example.demo.request.RegisterRequest;
import com.example.demo.service.LoginService;
import java.util.concurrent.ExecutionException;
import javax.xml.transform.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户模块
 * @author 99200
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {


    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void register(RegisterRequest registerRequest) {

        //检验两次密码是否一致
        if(!registerRequest.getUserPassword().equals(registerRequest.getPass())){
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_RE_PASSWORD);
        }

        User user;

        //检验用户名是否注册过
        user=baseMapper.selectByUserName(registerRequest.getUserAccount());
        if(user!=null){
          throw  new ReportServiceException(ReportSystemErrorCode.REGISTER_USERNAME_EXIST);
        }
        //检验手机号是否注册过
        user=baseMapper.selectByPhone(registerRequest.getUserPhone());
        if(user!=null){
            throw  new ReportServiceException(ReportSystemErrorCode.REGISTER_PHONE_EXIST);
        }
        //校验短信验证码
        checkSms(registerRequest.getUserPhone(),registerRequest.getSmsCode());

        user = new User();

        BeanUtils.copyProperties(registerRequest,user);
        //密码加密
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));

        baseMapper.insert(user);
    }


    /**
     * 校验图形验证码
     * @param key   验证码key
     * @param value 验证码value
     */
    public void checkCaptcha(String key,String value){
        //获取redis的key
        String captchaKey=RedisConstant.getGlobalKey(key);
        //缓存拿出redis的key
        String captchaValue= redisService.get(captchaKey);
        //key为空
        if(captchaValue==null){
            //提示验证码无效
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_CAPTCHA_INVALID);
        }else if(!captchaValue.equals(value.toLowerCase())){
            //提示验证码不正确
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_CAPTCHA_ERROR);
        }
        //缓存删除验证码
        redisService.delete(captchaValue);
    }

    /**
     * 校验短信验证码
     * @param phone 手机号
     * @param code 验证码
     */
    public void checkSms(String phone,String code){

        //获取redis的key
        String smsKey= RedisConstant.getSMSKey(phone);
        //缓存拿出redis的key
        String smsCode=redisService.get(smsKey);

        //等于空
        if(smsCode==null){
            //提示验证码无效
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_SMS_INVALID);
        }else if(!smsCode.equals(code)){
            //提示验证码错误
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_SMS_ERROR);
        }
        //删除缓存
        redisService.delete(smsCode);

    }


}
