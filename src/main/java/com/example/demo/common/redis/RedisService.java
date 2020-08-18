package com.example.demo.common.redis;


import com.example.demo.common.util.GsonUtil;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * @author 99200
 */
@Component
public class RedisService {


    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 缓存放入  时间设置为永久
     * @param key   键
     * @param value 值
     * @param <T>
     * @return true成功 false失败
     */
    public <T> boolean set(String key,T value){
        return set(key,value,RedisConstant.EXIST_FOREVER);
    }


    /**
     * 缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time 时间要大于0 如果小于0将设置为无期限
     * @param <T>
     * @return
     */
    public <T> boolean set(String key,T value,long time){

        //key加上全局全缀
        key=RedisConstant.getGlobalKey(key);

        try {
            String cacheValue;
            if(value instanceof String){
                cacheValue=(String) value;
            }else{
                cacheValue= GsonUtil.getGsonString(value);
            }
            if(time>0){
              redisTemplate.opsForValue().set(key,cacheValue,time, TimeUnit.SECONDS);
            }else{
                redisTemplate.opsForValue().set(key,cacheValue);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 缓存拿出并刷新时间
     * @param key  键
     * @param clazz 取出对象
     * @param time 时间（秒） time要大于0，如果time小于0将设置为无期限
     * @param <T>
     * @return true成功，false失败
     */
   public <T> T get(String key,Class<T> clazz,Long time) {

       //key加上全局前缀
       key = RedisConstant.getGlobalKey(key);

       //获取key
       Object value = redisTemplate.opsForValue().get(key);

       //如果时间不等于空
       if (time != null) {
           //时间大于0
           if (time > 0) {
               //刷新时间
               redisTemplate.expire(key, time, TimeUnit.SECONDS);
           }
       }
       //value 是String类型
       if (value instanceof String) {
           return (T) value;
       }
       return value==null?null:GsonUtil.gsonToBean(String.valueOf(value),clazz);
   }


    /**
     * 缓存拿出，转成泛型
     * @param key  key
     * @param clazz 取出对象
     * @param <T>
     * @return true成功，false失败
     */
   public <T> T get(String key,Class<T> clazz){

       return get(key,clazz,null);
   }


    /**
     * 缓存拿出
     * @param key key
     * @return true成功，false失败
     */
   public String get(String key){
       return get(key,String.class,null);
   }

    /**
     * 缓存删除
     * @param key 键
     */
   public void delete(String key){
       redisTemplate.delete(key);
   }

    /**
     * 缓存刷新
     * @param key 键
     * @param time 时间
     */
   public void expire(String key,long time){
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
   }


}
