package com.nicebin.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * Dubbo的自定义Filter
 *
 * @Author DiaoJianBin
 * @Date 2021/11/26 9:11
 */
@Activate(group = CommonConstants.PROVIDER)
public class DubboCustomFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("Dubbo自定义Filter执行，打印此次请求信息");
        System.out.println(invocation.toString());
        System.out.println("DubboCustomFilter放行前");
        Result result = invoker.invoke(invocation);
        System.out.println("DubboCustomFilter放行后，执行结果="+result.getValue());
        return result;
    }
}
