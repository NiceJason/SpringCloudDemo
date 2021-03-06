package com.springclouddemo.redis.cache;

import lombok.Getter;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author NiceBin
 * @description: 记录被 {@link Cacheable} 注解过的方法信息，为了主动更新缓存去调用对应方法
 * @date 2019/11/26 16:28
 */
@Getter
public class CacheInvocation {
    private Object key;
    private final Object targetBean;
    private final Method targetMethod;
    private Object[] arguments;

    public CacheInvocation(Object key, Object targetBean, Method targetMethod, Object[] arguments) {
        this.key = key;
        this.targetBean = targetBean;
        this.targetMethod = targetMethod;
        //反射时不用检查修饰符，略微提高性能
        this.targetMethod.setAccessible(true);
        if (arguments != null && arguments.length != 0) {
            this.arguments = Arrays.copyOf(arguments, arguments.length);
        }
    }
}
