package me.paul.yiblog_ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by paul on 17-9-4.
 */
@Configuration
@ComponentScan(basePackages = {"me.paul.yiblog_ssm.service"})
public class ServiceConfig {

}
