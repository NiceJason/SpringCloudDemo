package com.springclouddemo.redis.utils;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: NiceBin
 * @description: reids里的一些方法封装
 *               1.scan命令
 * @date: 2021/3/1 21:58
 */
public class RedisUtils {

    /**
     * 用此方法来代替keys
     * @param matchKey key的匹配值
     *                 有3个通配符 *, ? ,[]
     *                 *: 通配任意多个字符
     *                 ?: 通配单个字符
     *                 []: 通配括号内的某1个字符
     * @param redisTemplate
     * @return
     */
    public static Set<String> scan(String matchKey, RedisTemplate redisTemplate){
        Set<String> keys = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keyItems  = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(20).match(matchKey).build());
            while (cursor.hasNext()){
                keyItems.add(new String(cursor.next()));
            }
            return keyItems;
        });
        return keys;
    }
}
