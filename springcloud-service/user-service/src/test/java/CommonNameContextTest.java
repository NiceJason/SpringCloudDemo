import org.junit.Assert;
import org.junit.Test;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

public class CommonNameContextTest {

    private static final String PROPERTY_NAME = "test.context.name";

    @Test
    public void test() {
        
        //创建 parent context
        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        //添加 BaseConfig 相关配置
        parent.register(BaseConfig.class);
        //初始化 parent
        parent.refresh();
        //创建 testClient1，默认配置使用 ClientCommonConfig
        TestClient testClient1 = new TestClient(ClientCommonConfig.class);
        //创建 service1 与 service2 以及指定对应额外的配置类
        TestSpec testSpec1 = new TestSpec("service1", new Class[]{Service1Config1.class, Service1Config2.class});
        TestSpec testSpec2 = new TestSpec("service2", new Class[]{Service2Config.class});
        //设置 parent ApplicationContext 为 parent
        testClient1.setApplicationContext(parent);
        //将 service1 与 service2 的配置加入 testClient1
        ArrayList list = new ArrayList();
        list.add(testSpec1);
        list.add(testSpec2);
        testClient1.setConfigurations(list);
        BaseBean baseBean = testClient1.getInstance("service1", BaseBean.class);
        System.out.println(baseBean);
        //验证正常获取到了 baseBean
        Assert.assertNotNull(baseBean);
        ClientCommonBean commonBean = testClient1.getInstance("service1", ClientCommonBean.class);
        System.out.println(commonBean);
        //验证正常获取到了 commonBean
        Assert.assertNotNull(commonBean);
        Service1Bean1 service1Bean1 = testClient1.getInstance("service1", Service1Bean1.class);
        System.out.println(service1Bean1);
        //验证正常获取到了 service1Bean1
        Assert.assertNotNull(service1Bean1);
        Service1Bean2 service1Bean2 = testClient1.getInstance("service1", Service1Bean2.class);
        System.out.println(service1Bean2);
        //验证正常获取到了 service1Bean2
        Assert.assertNotNull(service1Bean2);
        BaseBean baseBean2 = testClient1.getInstance("service2", BaseBean.class);
        System.out.println(baseBean2);
        //验证正常获取到了 baseBean2 并且 baseBean2 就是 baseBean
        Assert.assertEquals(baseBean, baseBean2);
        ClientCommonBean commonBean2 = testClient1.getInstance("service2", ClientCommonBean.class);
        System.out.println(commonBean2);
        //验证正常获取到了 commonBean2 并且 commonBean 和 commonBean2 不是同一个
        Assert.assertNotNull(commonBean2);
        Assert.assertNotEquals(commonBean, commonBean2);
        Service2Bean service2Bean = testClient1.getInstance("service2", Service2Bean.class);
        System.out.println(service2Bean);
        //验证正常获取到了 service2Bean
        Assert.assertNotNull(service2Bean);
    }

    @Configuration(proxyBeanMethods = false)
    static class BaseConfig {
        @Bean
        BaseBean baseBean() {
            return new BaseBean();
        }
    }

    static class BaseBean {
    }

    @Configuration(proxyBeanMethods = false)
    static class ClientCommonConfig {
        @Bean
        ClientCommonBean clientCommonBean(Environment environment, BaseBean baseBean) {
            //在创建 NamedContextFactory 里面的子 ApplicationContext 的时候，会指定 name，这个 name 对应的属性 key 即 PROPERTY_NAME
            return new ClientCommonBean(environment.getProperty(PROPERTY_NAME), baseBean);
        }
    }

    static class ClientCommonBean {
        private final String name;
        private final BaseBean baseBean;

        ClientCommonBean(String name, BaseBean baseBean) {
            this.name = name;
            this.baseBean = baseBean;
        }

        @Override
        public String toString() {
            return "ClientCommonBean{" +
                    "name='" + name + '\'' +
                    ", baseBean=" + baseBean +
                    '}';
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class Service1Config1 {
        @Bean
        Service1Bean1 service1Bean1(ClientCommonBean clientCommonBean) {
            return new Service1Bean1(clientCommonBean);
        }
    }

    static class Service1Bean1 {
        private final ClientCommonBean clientCommonBean;

        Service1Bean1(ClientCommonBean clientCommonBean) {
            this.clientCommonBean = clientCommonBean;
        }

        @Override
        public String toString() {
            return "Service1Bean1{" +
                    "clientCommonBean=" + clientCommonBean +
                    '}';
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class Service1Config2 {
        @Bean
        Service1Bean2 service1Bean2() {
            return new Service1Bean2();
        }
    }

    static class Service1Bean2 {
    }

    @Configuration(proxyBeanMethods = false)
    static class Service2Config {
        @Bean
        Service2Bean service2Bean(ClientCommonBean clientCommonBean) {
            return new Service2Bean(clientCommonBean);
        }
    }

    static class Service2Bean {
        private final ClientCommonBean clientCommonBean;

        Service2Bean(ClientCommonBean clientCommonBean) {
            this.clientCommonBean = clientCommonBean;
        }

        @Override
        public String toString() {
            return "Service2Bean{" +
                    "clientCommonBean=" + clientCommonBean +
                    '}';
        }
    }

    static class TestSpec implements NamedContextFactory.Specification {
        private final String name;
        private final Class<?>[] configurations;

        public TestSpec(String name, Class<?>[] configurations) {
            this.name = name;
            this.configurations = configurations;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Class<?>[] getConfiguration() {
            return configurations;
        }
    }

    static class TestClient extends NamedContextFactory<TestSpec> {

        public TestClient(Class<?> defaultConfigType) {
            super(defaultConfigType, "testClient", PROPERTY_NAME);
        }
    }
}