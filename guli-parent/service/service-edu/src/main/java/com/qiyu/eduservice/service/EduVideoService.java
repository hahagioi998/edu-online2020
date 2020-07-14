package com.qiyu.eduservice.service;

import com.qiyu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
public interface EduVideoService extends IService<EduVideo> {

    Boolean removeByCourseId(String courseId);
}
