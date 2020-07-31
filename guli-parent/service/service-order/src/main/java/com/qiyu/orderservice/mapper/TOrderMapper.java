package com.qiyu.orderservice.mapper;

import com.qiyu.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author qiyu
 * @since 2020-07-29
 */
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

}
