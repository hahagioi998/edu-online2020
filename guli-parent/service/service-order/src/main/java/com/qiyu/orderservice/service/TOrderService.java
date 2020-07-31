package com.qiyu.orderservice.service;

import com.qiyu.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-07-29
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String courseId, String memberIdByJwtToken);
}
