package me.paul.yiblog_ssm.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.paul.yiblog_ssm.util.JavaMailUtil;
import me.paul.yiblog_ssm.util.MemcachedUtil;
import net.spy.memcached.MemcachedClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

/**
 * Created by paul on 17-9-4.
 */
@Configuration
public class UtilConfig {

    @Bean
    public JavaMailUtil javaMailUtil() {
        JavaMailUtil javaMailUtil = new JavaMailUtil();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls", "true");
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        javaMailUtil.setProps(properties);
        javaMailUtil.setAccount("m15521294948@163.com");
        javaMailUtil.setAccountName("Paul");
        javaMailUtil.setPassword("229267643aa");
        return javaMailUtil;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }

//    @Bean
//    public MemcachedClient memcachedClient() throws IOException {
//        InetSocketAddress inetSocketAddress = new InetSocketAddress("memcached", 11211);
//        return new MemcachedClient(inetSocketAddress);
//    }
//
//    @Bean
//    public MemcachedUtil memcachedUtil(Gson gson, MemcachedClient mmc) {
//        MemcachedUtil memcachedUtil = new MemcachedUtil();
//        memcachedUtil.setGson(gson);
//        memcachedUtil.setMmc(mmc);
//        memcachedUtil.setExp(1800);
//        return memcachedUtil;
//    }
}
