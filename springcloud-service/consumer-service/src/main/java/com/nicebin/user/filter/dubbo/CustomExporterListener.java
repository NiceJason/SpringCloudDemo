package com.nicebin.user.filter.dubbo;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.ExporterListener;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;

/**
 * 自定义服务暴露监听
 * 当有服务暴露时，触发该事件
 * 实际情况没测试怎么用的，难怪博客相关内容也少
 * @Author DiaoJianBin
 * @Date 2021/11/26 16:58
 */
@Activate
public class CustomExporterListener implements ExporterListener {
    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        Invoker<?> invoker = exporter.getInvoker();
        System.out.println("服务暴露："+invoker.getUrl().getParameter("application"));
    }

    @Override
    public void unexported(Exporter<?> exporter) throws RpcException {
        Invoker<?> invoker = exporter.getInvoker();
        System.out.println("服务隐藏："+invoker.getUrl().getParameter("application"));
    }
}
