package com.nicebin.user.service;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.nicebin.common.exception.SystemException;
import com.nicebin.user.entity.TestResource;
import com.nicebin.user.feign.feign_client.BlankServiceTestClient;
import com.nicebin.user.feign.feign_client.BusinessServiceTestClient;
import com.nicebin.user.sentinel.block_handler.BlockHandlerTest;
import com.nicebin.user.sentinel.fallback.FallbackTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author DiaoJianBin
 * @Description Sentinel测试类
 * 在此以服务方法作为资源进行测试
 * @Date 2021/3/18 11:08
 */
@Service

public class SentinelTestService {

    @Autowired
    BlankServiceTestClient blankServiceTestClient;

    @Autowired
    BusinessServiceTestClient businessServiceTestClient;

    /**
     * 测试限流，熔断，热点参数
     * 使用类里面的blockHandler，fallback
     * 测试抛出异常
     *
     * @param name
     * @param count
     * @param resource
     * @return
     */
    @SentinelResource(value = "resource1", blockHandler = "resource1BH",fallback = "resource1FB")
    public String getResource1(String name, int count, TestResource resource) {
        //抛出调用异常测试
        return businessServiceTestClient.throwExceptionTest(name);
    }

    /**
     * resource1限流降级的处理方法
     * @param name
     * @param count
     * @param resource
     * @param blockException
     * @return
     */
    public String resource1BH(String name, int count, TestResource resource, BlockException blockException) {
        System.out.println("resource1被限流降级了，具体为");

        if (blockException instanceof FlowException) {
            System.out.println("流控异常");
        }else if(blockException instanceof DegradeException){
            System.out.println("熔断异常");
        }else if(blockException instanceof SystemBlockException){
            //具体异常有特殊的方法
            SystemBlockException systemBlockException = (SystemBlockException)blockException;
            System.out.println("系统保护异常 resourceName="+systemBlockException.getResourceName()+" limitType="+systemBlockException.getLimitType());
        }else if(blockException instanceof ParamFlowException){
            //具体异常有特殊的方法
            ParamFlowException paramFlowException = (ParamFlowException)blockException;
            System.out.println("热点参数异常 resourceName="+paramFlowException.getResourceName()+" limitParam="+paramFlowException.getLimitParam());
        }

        System.out.println("异常信息为 "+blockException.getMessage());

        return blockException.getMessage();
    }

    /**
     * resource1回退的方法
     * @param name
     * @param count
     * @param resource
     * @param throwable
     * @return
     */
    public String resource1FB(String name, int count, TestResource resource,Throwable throwable) {
          if(throwable instanceof BlockException){
              System.out.println("BlockException也会进入到fallback方法");
          }

          if(throwable instanceof SystemException){
              System.out.println("原来是SystemException");
          }

          return throwable.getMessage();
    }

    /**
     * 测试限流，熔断，热点参数
     * 使用类默认的fabllbackHandler和指定的blockHandler
     * 测试慢调用
     *
     * @param name
     * @param count
     * @param resource
     * @return
     */
    @SentinelResource(value = "resource2",
            blockHandlerClass = BlockHandlerTest.class,blockHandler = "resource2BH",
            fallbackClass = FallbackTest.class,defaultFallback = "stringdeDaultFallback")
    public String getResource2(String name, int count, TestResource resource) {
        System.out.println("进入getResource2，开始休眠");
        try{
            Thread.sleep(20 * 1000);
        }catch (Exception e){

        }
        System.out.println("休眠结束");
        return "获得资源";
    }

    /**
     * 测试限流，熔断，热点参数
     * 采用最原始的资源定义
     * 使用异步调用
     * @param name
     * @param count
     * @param resource
     * @return
     */
    public String getResource3(String name, int count, TestResource resource) {

        try{
            final AsyncEntry asyncEntry = SphU.asyncEntry("resource3");
            System.out.println("resource3启动异步处理");
            CompletableFuture.runAsync(()->{
                ContextUtil.runOnContext(asyncEntry.getAsyncContext(),()->{
                    try{
                        System.out.println("进行异步处理resource3");
                        Thread.sleep(10* 1000);
                        System.out.println("异步处理resource3完毕");
                    }catch (Exception e){

                    }finally {
                        if(asyncEntry!=null){
                            asyncEntry.exit();
                        }
                    }
                });
            });
            System.out.println("正在进行其他业务");
            Thread.sleep(5 * 1000);
            System.out.println("其他业务处理完毕");
        }catch (BlockException blockException){
            System.out.println("resource3被限流降级 "+blockException.getMessage());
        }catch (Exception e){
            System.out.println("resource3抛出其他异常 "+e.getMessage());
        }
        return "请求resource3完毕";
    }
}
