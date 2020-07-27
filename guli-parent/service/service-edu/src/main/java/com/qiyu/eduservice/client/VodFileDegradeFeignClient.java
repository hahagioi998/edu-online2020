package com.qiyu.eduservice.client;

import com.qiyu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiyu
 * @create 2020-07-17
 * @Description:
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {


    @Override
    public R removeAlyVideo(String videoId) {
        return R.error().message("删除小节视频失败");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("批量删除小节视频失败");
    }
}