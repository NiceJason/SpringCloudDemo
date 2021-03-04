package customConfig;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: NiceBin
 * @description: 用来测试用Spring.factories来控制加载顺序
 * @date: 2021/2/22 20:02
 */
@AutoConfigureOrder(10)
@Configuration
public class AConfig {

    @Bean
    public void getA(){
        System.out.println("A加载了");
    }
}
