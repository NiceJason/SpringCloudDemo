package com.nicebin.service.config.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.datasource.DataSourceProxyHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author DiaoJianBin
 * @Description Mybatis-plus配置的初始化
 * @Date 2021/4/15 15:19
 */
@Configuration(proxyBeanMethods = false)
@MapperScan("com.nicebin.*.mapper")
public class MybatisPlusConfig {

//    @Bean
//    public DataSourceProxy dataSourceProxy(DataSource dataSource,SqlSessionFactoryBean sqlSessionFactoryBean) {
//        DataSourceProxy dataSourceProxy = new DataSourceProxy(dataSource);
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        return dataSourceProxy;
//    }

    /**
     * 分页插件
     * @return 分页插件的实例
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     * @return 乐观锁插件的实例
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
