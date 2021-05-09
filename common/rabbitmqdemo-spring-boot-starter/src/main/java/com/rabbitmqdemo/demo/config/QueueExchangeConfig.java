package com.rabbitmqdemo.demo.config;

import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author DiaoJianBin
 * @Description 队列和交换器生成和绑定的地方
 *              头部交换机因为性能较差，不就测试了
 * @Date 2021/2/23 14:25
 */
@Configuration
@ConfigurationProperties(prefix = "sunspring.dlx")
@Setter
public class QueueExchangeConfig {

    private String dlxExchange;

    private String dlxQueue;

    private String dlxRoutingKey;

    /**
     * 用来测试直连交换器
     * @return
     */
    @Bean
    public Queue directQueueA(){
        //默认是持久化的
        return new Queue("directQueue-A");
    }

    /**
     * 用来测试直连交换器
     * 非持久化，用来测试RabbitMQ宕机后恢复
     * @return
     */
    @Bean
    public Queue directQueueB(){
        return new Queue("directQueue-B",false);
    }

    /**
     * 用来测试扇形交换器
     * @return
     */
    @Bean
    public Queue fanoutQueueA(){
        //默认是持久化的
        return new Queue("fanoutQueue-A");
    }

    /**
     * 用来测试扇形交换器
     * @return
     */
    @Bean
    public Queue fanoutQueueB(){
        //默认是持久化的
        return new Queue("fanoutQueue-B");
    }

    /**
     * 用来测试主题交换器
     * @return
     */
    @Bean
    public Queue topicQueueA(){
        Map<String,Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange",dlxExchange);
        arguments.put("x-dead-letter-routing-key",dlxRoutingKey);
        //默认是持久化的
        return QueueBuilder.durable("topicQueue-A").withArguments(arguments).build();
    }

    /**
     * 用来测试手动ack交换器
     * @return
     */
    @Bean
    public Queue ackQueueA(){
        Map<String,Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange",dlxExchange);
        arguments.put("x-dead-letter-routing-key",dlxRoutingKey);
        //默认是持久化的
        return QueueBuilder.durable("ackQueue-A").withArguments(arguments).build();
    }

    /**
     * 用来测试死信队列，和ack队列配合
     * @return
     */
    @Bean
    public Queue deadQueueA(){
        //默认是持久化的
        return new Queue(dlxQueue,false);
    }

    /**
     * 延时队列
     * @return
     */
    @Bean
    public Queue delayedQueueA(){
        return new Queue("delayedQueue-A");
    }

    /**
     * 错误测试队列
     * @return
     */
    @Bean
    public Queue errorTestQueueA(){
        return new Queue("errorTestQueue-A");
    }

    //----------------------------------以下为交换机生成----------------------

    /**
     * 直接交换机
     * @return
     */
    @Bean
    public DirectExchange directExchangeA(){
        //默认持久化
        return new DirectExchange("directExchangeA");
    }

    /**
     * 扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchangeA(){
        //默认持久化
        return new FanoutExchange("fanoutExchangeA");
    }

    /**
     * 话题交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchangeA(){
        //默认持久化
        return new TopicExchange("topicExchangeA");
    }

    /**
     * 手动ack交换机
     * @return
     */
    @Bean
    public DirectExchange ackDirectExchangeA(){
        //默认持久化
        return new DirectExchange("ackDirectExchangeA");
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange deadDirectExchangeA(){
        //默认持久化
        return new DirectExchange(dlxExchange);
    }

    /**
     * 延时交换机
     * 类型为direct
     * @return
     */
    @Bean
    public CustomExchange delayedDirectExchangeA(){
        Map arguments = new HashMap(3);
        arguments.put("x-delayed-type","direct");
        return new CustomExchange("delayedDirectExchangeA","x-delayed-message",true,false,arguments);
    }

    @Bean
    public DirectExchange errorTestExchangeA(){
        return new DirectExchange("errorTestExchangeA");
    }

    //-------------------------以下为Binding--------------------

    /**
     * 下面和directBindB绑定的routing key是相同的
     * 此时有消息进来，会复制消息，分别到队列A和队列B
     * @return
     */
    @Bean
    public Binding directBindA(){
        return BindingBuilder.bind(directQueueA()).to(directExchangeA()).with("directExchange");
    }

    @Bean
    public Binding directBindB(){
        return BindingBuilder.bind(directQueueB()).to(directExchangeA()).with("directExchange");
    }

    @Bean
    public Binding fanoutBindA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchangeA());
    }

    @Bean
    public Binding fanoutBindB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchangeA());
    }

    @Bean
    public Binding topicBindA(){
        return BindingBuilder.bind(topicQueueA()).to(topicExchangeA()).with("*.topicA.#");
    }

    @Bean
    public Binding ackDirectBingA(){
        return BindingBuilder.bind(ackQueueA()).to(ackDirectExchangeA()).with("ackA");
    }

    @Bean
    public Binding deadDirectBindA(){
        return BindingBuilder.bind(deadQueueA()).to(deadDirectExchangeA()).with(dlxRoutingKey);
    }

    @Bean
    public Binding delayedBindingA(){
        return BindingBuilder.bind(delayedQueueA()).to(delayedDirectExchangeA()).with("delayedA").noargs();
    }

    @Bean
    public Binding errorTestBindingA(){
        return BindingBuilder.bind(errorTestQueueA()).to(errorTestExchangeA()).with("errorTestA");
    }
}
