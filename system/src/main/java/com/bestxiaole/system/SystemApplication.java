package com.bestxiaole.system;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.bestxiaole")
@MapperScan("com.bestxiaole.server.mapper")
public class SystemApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SystemApplication.class);

    /**
     * RestTemplate动态路由整合
     */
    @Bean
    @LoadBalanced
    public RestTemplate create() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SystemApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("System地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

    @Bean
    public Random get() {
        return new Random();
    }
}
