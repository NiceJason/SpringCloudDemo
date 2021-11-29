package com.nicebin.api.dubbo.fallback;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/29 10:12
 */
public class ConsumerDubboFallback implements DubboFallback {
    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
        System.out.println("Consumer降级了");
        return AsyncRpcResult.newDefaultAsyncResult("Consumer的Dubbo降级", e.toRuntimeException(), invocation);
    }
}
