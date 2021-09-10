package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author DiaoJianBin
 * @Date 2021/8/26 8:52
 */
public class LogInvocationHandler implements InvocationHandler {
    private Hello hello;
    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
            System.out.println("代理说: " + Arrays.toString(args));
        }
        //return method.invoke(hello, args);
        return ((Hello)proxy).sayHello(Arrays.toString(args));
    }
}
