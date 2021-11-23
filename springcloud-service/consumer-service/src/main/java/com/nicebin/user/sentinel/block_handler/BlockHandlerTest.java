package com.nicebin.user.sentinel.block_handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.nicebin.user.entity.TestResource;

/**
 * @Author DiaoJianBin
 * @Description sentinel外部限流降级处理
 *              具体在SentinelTestService中使用
 * @Date 2021/3/18 15:54
 */
public class BlockHandlerTest {
    /**
     * resource2限流降级的处理方法
     * @param name
     * @param count
     * @param resource
     * @param blockException
     * @return
     */
    public static String resource2BH(String name, int count, TestResource resource, BlockException blockException) {

        System.out.println("外部的限流降级");
        System.out.println("resource2被限流降级了，具体为");

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
}
