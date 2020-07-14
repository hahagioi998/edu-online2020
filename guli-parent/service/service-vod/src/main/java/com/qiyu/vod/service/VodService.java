package com.qiyu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadVideoAly(MultipartFile file);



    /**
     * 删除多个云端视频
     * @param videoIdList
     */
    void removeMoreAlyVideo(List<String> videoIdList);
}
