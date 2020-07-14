package com.qiyu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.qiyu.commonutils.R;
import com.qiyu.servicebase.handler.exception.GuliException;
import com.qiyu.vod.service.VodService;
import com.qiyu.vod.utils.ConstantVodUtils;
import com.qiyu.vod.utils.InitVodCilent;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qiyu
 * @create 2020-07-13
 * @Description:
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }
    @DeleteMapping("removeAlyVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable String videoId) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(videoId);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new GuliException(20001,"获取凭证失败");
        }
    }

}
