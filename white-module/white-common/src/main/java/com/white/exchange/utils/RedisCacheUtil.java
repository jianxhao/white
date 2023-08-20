package com.white.exchange.utils;

import cn.hutool.json.JSONUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @author xh
 * @create 2023-08-20  13:41
 */
@Component
public class RedisCacheUtil {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisCacheUtil(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setKey(String key, Object obj) {
        setKey(key, obj, NameOrTimeUtil.REDIS_LOGIN_TTL, TimeUnit.SECONDS);
    }

    public void setKey(String key, Object obj, Long ttl, TimeUnit timeUnit){
        String value = JSONUtil.parse(obj).toString();
        // 序列化value
        stringRedisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    public <T> T getKey(String key, Class<T> clzz){

        String jsonObject = stringRedisTemplate.opsForValue().get(key);

        T obj = JSONUtil.toBean(jsonObject, clzz);

        return obj;

    }

}
