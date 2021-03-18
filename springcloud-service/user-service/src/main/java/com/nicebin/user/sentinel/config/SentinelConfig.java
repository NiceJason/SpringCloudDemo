package com.nicebin.user.sentinel.config;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.ProcessorSlotEntryCallback;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.statistic.StatisticSlotCallbackRegistry;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Description SentinelConfig的全局配置
 * @Date 2021/3/18 16:37
 */
@Configuration
public class SentinelConfig {

    static {
        StatisticSlotCallbackRegistry.addEntryCallback("defaultCallback", new ProcessorSlotEntryCallback() {
            @Override
            public void onPass(Context context, ResourceWrapper resourceWrapper, Object param, int count, Object... args) throws Exception {
                System.out.println("全局callback记录");
                System.out.println("资源名称："+resourceWrapper.getName()+"通过了");
            }

            @Override
            public void onBlocked(BlockException ex, Context context, ResourceWrapper resourceWrapper, Object param, int count, Object... args) {
                System.out.println("全局callback记录");
                System.out.println("资源名称："+resourceWrapper.getName()+"被限流了 "+ex.getMessage());
            }
        });
    }
}
