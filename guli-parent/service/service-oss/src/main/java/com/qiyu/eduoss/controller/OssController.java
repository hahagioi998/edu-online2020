package com.qiyu.eduoss.controller;

import com.qiyu.commonutils.R;
import com.qiyu.eduoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
@RestController
@RequestMapping("eduoss")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;


    @PostMapping("upload")
    public R uploadFile(@RequestParam("file") MultipartFile multipartFile){
       String url = ossService.uploadFileToOss(multipartFile);
       return R.ok().data("url",url);
    }

}
