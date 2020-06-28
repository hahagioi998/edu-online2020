package com.qiyu.eduoss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadFileToOss(MultipartFile multipartFile);
}
