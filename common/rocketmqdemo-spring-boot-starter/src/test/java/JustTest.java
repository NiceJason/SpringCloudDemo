import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author DiaoJianBin
 * @Date 2022/2/25 10:44
 */
public class JustTest {
    @Test
    public void test(){
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
        BeanPostProcessor a;
    }
}
