package me.paul.yiblog_ssm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * Created by paul on 17-9-4.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class DaoConfig {

    @Bean
    public ComboPooledDataSource comboPooledDataSource(
            @Value("${jdbc.driverClass}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password,
            @Value("${jdbc.maxPoolSize}") int maxPoolSize,
            @Value("${jdbc.maxIdleTime}") int maxIdleTime) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(username);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setMaxPoolSize(maxPoolSize);
        comboPooledDataSource.setMaxIdleTime(maxIdleTime);
        return comboPooledDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(ComboPooledDataSource dataSource,@Value("classpath:mybatisConfig.xml") Resource location){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(location);
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("me.paul.yiblog_ssm.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(ComboPooledDataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
