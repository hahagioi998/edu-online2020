package com.qiyu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qiyu
 * @create 2020-06-16
 * @Description:
 */
@SpringBootApplication
//@EnableDiscoveryClient  //nacos注册
//@EnableFeignClients
@ComponentScan(basePackages = {"com.qiyu"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
