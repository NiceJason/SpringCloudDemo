package com.nicebin.api.dubbo.fallback;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/29 10:12
 */
public class ProviderDubboFallback implements DubboFallback {
    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
        System.out.println("Provider降级了");
        return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("Provider降级了"), invocation);
    }
}
