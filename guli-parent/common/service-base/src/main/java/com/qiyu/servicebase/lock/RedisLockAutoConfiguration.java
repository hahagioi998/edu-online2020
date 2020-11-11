package com.qiyu.servicebase.lock;

import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式锁自动化配置
 *
 * @author
 * @date 2020/11/5
 */
@Configuration
@ConditionalOnClass(RedissonClient.class)
public class RedisLockAutoConfiguration {

}
