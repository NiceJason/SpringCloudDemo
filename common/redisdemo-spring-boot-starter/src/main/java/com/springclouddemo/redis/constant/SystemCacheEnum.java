package com.springclouddemo.redis.constant;

import lombok.Data;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/2/26 16:39
 */
public  enum SystemCacheEnum {
    //每日缓存,有效时间24小时
    DAY("dailyCache",24*60*60),
    //半日缓存，有效时间12小时
    HALF_DAY("halfDayCache",12*60*60),
    //1小时缓存
    ONE_HOUR("oneHour",1*60*60),
    //半小时缓存
    HALF_HOUR("halfHour",30*60),
    //测试缓存
    TEST_TIME("testTime",15);

    private String cacheName;
    private long surviveTime;

    SystemCacheEnum(String cacheName,long surviveTime){
        this.cacheName = cacheName;
        this.surviveTime = surviveTime;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public long getSurviveTime() {
        return surviveTime;
    }

    public void setSurviveTime(long surviveTime) {
        this.surviveTime = surviveTime;
    }
}

