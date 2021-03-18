package com.nicebin.user.sentinel.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nicebin.common.exception.SystemException;

/**
 * @Author DiaoJianBin
 * @Description Sentinel的fallback外部测试
 *              具体在SentinelTestService中使用
 * @Date 2021/3/18 15:58
 */
public class FallbackTest {

    public static String stringdeDaultFallback(Throwable throwable){
        System.out.println("进入默认的 stringdeDaultFallback");
        if(throwable instanceof BlockException){
            System.out.println("BlockException也会进入到fallback方法");
        }

        if(throwable instanceof SystemException){
            System.out.println("原来是SystemException");
        }

        return throwable.getMessage();
    }
}
