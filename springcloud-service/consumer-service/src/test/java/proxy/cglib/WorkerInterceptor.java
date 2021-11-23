package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author DiaoJianBin
 * @Date 2021/8/25 17:13
 */
public class WorkerInterceptor implements MethodInterceptor {

    public Worker worker;

    public WorkerInterceptor(Worker worker){
        this.worker = worker;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("代理工人正在"+ Arrays.toString(args));

        //return method.invoke(worker,args);
        return proxy.invokeSuper(obj,args);
        //return proxy.invoke(obj,args);
    }
}
