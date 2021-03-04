package customConfig;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: NiceBin
 * @description: 用来测试用Spring.factories来控制加载顺序
 * @date: 2021/2/22 20:03
 */
@AutoConfigureOrder(0)
@Configuration
public class BConfig {

    @Bean
    public void getB(){
        System.out.println("B加载了");
    }
}
