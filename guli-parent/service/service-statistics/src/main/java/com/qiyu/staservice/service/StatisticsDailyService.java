package com.qiyu.staservice.service;

import com.qiyu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-07-30
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

}
