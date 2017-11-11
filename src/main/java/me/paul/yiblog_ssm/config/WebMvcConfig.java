package me.paul.yiblog_ssm.config;

import me.paul.yiblog_ssm.controller.interceptor.AdminInterceptor;
import me.paul.yiblog_ssm.controller.interceptor.IndexInterceptor;
import me.paul.yiblog_ssm.controller.interceptor.LoadInterceptor;
import me.paul.yiblog_ssm.controller.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by paul on 17-9-4.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"me.paul.yiblog_ssm.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(10485760L);
        commonsMultipartResolver.setMaxInMemorySize(4096);
        return commonsMultipartResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
        super.configureViewResolvers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/static/");

        super.addResourceHandlers(registry);
    }

    @Autowired
    private LoadInterceptor loadInterceptor;

    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private IndexInterceptor indexInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor).addPathPatterns("/");
        registry.addInterceptor(loadInterceptor);
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/cates")
                .addPathPatterns("/link/pass/*")
                .addPathPatterns("/upload")
                .addPathPatterns("/adminOperation");
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/passage/edit/*")
                .addPathPatterns("/passage/save")
                .addPathPatterns("/operation");
        super.addInterceptors(registry);
    }
}
