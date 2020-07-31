package com.qiyu.staservice.controller;


import com.qiyu.commonutils.R;
import com.qiyu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/staservice/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return R.ok();
    }
}

