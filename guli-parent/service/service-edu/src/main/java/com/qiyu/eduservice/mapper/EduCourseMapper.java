package com.qiyu.eduservice.mapper;

import com.qiyu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiyu.eduservice.entity.frontvo.CourseWebVo;
import com.qiyu.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo selectPublishVoById(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
