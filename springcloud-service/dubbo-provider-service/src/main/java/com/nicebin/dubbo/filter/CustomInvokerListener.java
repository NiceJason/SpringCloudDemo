package com.nicebin.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;

/**
 * 自定义服务引用监听
 * 当有服务引用时，触发该事件
 * 实际情况没测试出怎么用的
 * @Author DiaoJianBin
 * @Date 2021/11/26 17:01
 */
@Activate
public class CustomInvokerListener implements InvokerListener {
    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        System.out.println("服务引用："+invoker.getUrl().getParameter("application"));
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
        System.out.println("服务断掉引用："+invoker.getUrl().getParameter("application"));
    }
}
