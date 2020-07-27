package com.qiyu.eduservice.controller;


import com.qiyu.commonutils.R;
import com.qiyu.eduservice.client.VodClient;
import com.qiyu.eduservice.entity.EduVideo;
import com.qiyu.eduservice.service.EduVideoService;
import com.qiyu.servicebase.handler.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据小节id查询小节信息
    @GetMapping("getVideoById/{videoId}")
    public R getVideoById(@PathVariable("videoId")String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("item",eduVideo);
    }
    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }
    //删除小节
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable("videoId")String videoId){
        //删除视频资源 TODOVodClient
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }

        eduVideoService.removeById(videoId);
        return R.ok();
    }

}

