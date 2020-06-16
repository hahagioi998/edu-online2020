package com.qiyu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiyu
 * @create 2020-06-16
 * @Description:
 */
@Configuration
@MapperScan("com.qiyu.eduservice.mapper")
public class EduConfig {

}
