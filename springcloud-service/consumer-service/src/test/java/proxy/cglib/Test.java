package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @Author DiaoJianBin
 * @Date 2021/8/25 11:49
 */
public class Test {

    @org.junit.Test
    public void test(){
        Worker zhangsan = new Worker();
        zhangsan.setName("张三");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Worker.class);
        enhancer.setCallback(new WorkerInterceptor(zhangsan));
        Worker o = (Worker)enhancer.create();
        o.setName("李四");
        o.doWork("做作业");
    }
}
