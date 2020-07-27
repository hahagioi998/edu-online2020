package com.qiyu.cmsservice.controller;

import com.qiyu.cmsservice.entity.CrmBanner;
import com.qiyu.cmsservice.service.CrmBannerService;
import com.qiyu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  * <p>
 *  * 前台bannber显示
 *  * </p>
 * @author qiyu
 * @create 2020-07-20
 * @Description:
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }

}
