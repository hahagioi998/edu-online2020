package com.qiyu.eduoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.qiyu.commonutils.ResultCodeEnum;
import com.qiyu.eduoss.service.OssService;
import com.qiyu.eduoss.utils.ConstantPropertiesUtil;
import com.qiyu.servicebase.handler.exception.GuliException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileToOss(MultipartFile multipartFile) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endPoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        OSS ossClient = null;
        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }


            // 上传文件流。
            InputStream inputStream = multipartFile.getInputStream();


            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = multipartFile.getOriginalFilename();

            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            String fileUrl = filePath + "/" + fileName + original;

            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
            return uploadUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR.getCode(), ResultCodeEnum.FILE_UPLOAD_ERROR.getValue());
        }
    }
}
