package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.exception.ReportServiceException;
import com.example.demo.common.exception.ReportSystemErrorCode;
import com.example.demo.common.redis.RedisConstant;
import com.example.demo.common.redis.RedisService;
import com.example.demo.dto.TokenDOT;
import com.example.demo.entity.User;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.request.ForgetPassWordRequest;
import com.example.demo.request.LoginByPhoneRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.request.SendSmsRequest;
import com.example.demo.service.LoginService;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户模块
 *
 * @author 99200
 */
@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {


    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void register(RegisterRequest registerRequest) {

        //检验两次密码是否一致
        if (!registerRequest.getUserPassword().equals(registerRequest.getPass())) {
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_RE_PASSWORD);
        }

        User user;

        //检验用户名是否注册过
        user = baseMapper.selectByUserName(registerRequest.getUserAccount());
        if (user != null) {
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_USERNAME_EXIST);
        }
        //检验手机号是否注册过
        user = baseMapper.selectByPhone(registerRequest.getUserPhone());
        if (user != null) {
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_PHONE_EXIST);
        }
        //校验短信验证码
        checkSms(registerRequest.getUserPhone(), registerRequest.getSmsCode());

        user = new User();

        user.setUserTime(new Date());

        BeanUtils.copyProperties(registerRequest, user);
        //密码加密
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));

        baseMapper.insert(user);
    }


    /**
     * 发送手机短信
     */
    @Override
    public void sendSms(SendSmsRequest sendSmsRequest) {

        checkCaptcha(sendSmsRequest.getCaptchaKey(), sendSmsRequest.getCaptchaValue());
        String verCoed = "9999";
        //        String verCoed=String.valueOf((1000+new Random().nextInt()));
        log.warn("模拟验证码是：{}", verCoed);

        //存入redis并设置过期时间为五分钟
        redisService.set(RedisConstant.getSMSKey(sendSmsRequest.getPhone()), verCoed, RedisConstant.EXIST_MIN_5);

    }

    /**
     * 登录
     */
    @Override
    public TokenDOT loginByUser(LoginRequest loginRequest) {
        //校验图形验证码
        checkCaptcha(loginRequest.getCaptchaKey(), loginRequest.getCaptchaValue());

        User user;
        //先检验账号 再检验手机号
        String userAccount = loginRequest.getUserAccount();
        user = baseMapper.selectByUserName(userAccount);
        if (user == null) {
            baseMapper.selectByPhone(userAccount);
        }
        if (user == null || !user.getUserPassword()
            .equals(DigestUtils.md5DigestAsHex(loginRequest.getPassWord().getBytes()))) {
            throw new ReportServiceException(ReportSystemErrorCode.LOGIN_BY_USER_ERROR);
        }
        //创建token
        return createTokenDto(user.getUserId());
    }

    /**
     * 手机短信登录
     */
    @Override
    public TokenDOT loginByPhone(LoginByPhoneRequest loginByPhoneRequest) {
        String phone = loginByPhoneRequest.getPhone();

        //校验手机验证码
        checkSms(phone, loginByPhoneRequest.getSmsCoed());

        //查找用户
        User user = baseMapper.selectByPhone(phone);

        //创建token
        return createTokenDto(user.getUserId());
    }


    /**
     * 忘记密码
     */
    @Override
    public void forgetPassword(ForgetPassWordRequest forgetPassWordRequest) {
        String phone = forgetPassWordRequest.getPhone();

        checkSms(phone, forgetPassWordRequest.getSmsCoed());

        //查询客户
        User user = baseMapper.selectByPhone(phone);

        User entity = new User();
        entity.setUserId(user.getUserId());
        entity.setUserPassword(DigestUtils.md5DigestAsHex(forgetPassWordRequest.getNewPassword().getBytes()));
        baseMapper.updateById(entity);
    }


    /**
     * 校验图形验证码
     *
     * @param key   验证码key
     * @param value 验证码value
     */
    public void checkCaptcha(String key, String value) {
        //获取redis的key
        String captchaKey = RedisConstant.getCaptchaKey(key);
        //缓存拿出redis的key
        String captchaValue = redisService.get(captchaKey);
        //key为空
        if (captchaValue == null) {
            //提示验证码无效
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_CAPTCHA_INVALID);
        } else if (!captchaValue.equals(value.toLowerCase())) {
            //提示验证码不正确
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_CAPTCHA_ERROR);
        }
        //缓存删除验证码
        redisService.delete(captchaValue);
    }

    /**
     * 校验短信验证码
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public void checkSms(String phone, String code) {

        //获取redis的key
        String smsKey = RedisConstant.getSMSKey(phone);
        //缓存拿出redis的key
        String smsCode = redisService.get(smsKey);

        //等于空
        if (smsCode == null) {
            //提示验证码无效
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_SMS_INVALID);
        } else if (!smsCode.equals(code)) {
            //提示验证码错误
            throw new ReportServiceException(ReportSystemErrorCode.REGISTER_SMS_ERROR);
        }
        //删除缓存
        redisService.delete(smsCode);

    }


    /**
     * 创建token并设置过期时间
     *
     * @return tokenDTO
     */
    public TokenDOT createTokenDto(Integer userId) {
        //创建token默认七天
        //生成随机token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //把token放redis 过期时间为七天
        redisService.set(RedisConstant.getTokenKey(token), userId, RedisConstant.EXIST_DAY_7);
        TokenDOT tokenDOT = new TokenDOT();
        tokenDOT.setToken(token);
        //刷新token时间
        tokenDOT.setExpires(new Date(System.currentTimeMillis() + 1000 * RedisConstant.EXIST_DAY_7));

        tokenDOT.setUserId(userId);
        return tokenDOT;
    }


}
