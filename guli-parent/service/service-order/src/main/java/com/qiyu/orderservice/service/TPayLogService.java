package com.qiyu.orderservice.service;

import com.qiyu.orderservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-07-29
 */
@Mapper
public interface TPayLogService extends IService<TPayLog> {

    Map createNatvie(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
