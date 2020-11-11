package com.qiyu.servicebase.idempotent.expression;

import com.qiyu.servicebase.idempotent.annotation.Idempotent;
import org.aspectj.lang.JoinPoint;

/**
 * 唯一标志处理器
 *
 * @author
 * @date 2020/11/10
 */
public interface KeyResolver {

    /**
     * 解析处理 key
     * @param idempotent 接口注解标识
     * @param point 接口切点信息
     * @return 处理结果
     */
    String resolver(Idempotent idempotent, JoinPoint point);

}
