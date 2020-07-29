package com.qiyu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiyu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyu.eduservice.entity.frontvo.CourseFrontVo;
import com.qiyu.eduservice.entity.frontvo.CourseWebVo;
import com.qiyu.eduservice.entity.vo.CourseInfoForm;
import com.qiyu.eduservice.entity.vo.CoursePublishVo;
import com.qiyu.eduservice.entity.vo.CourseQuery;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 新增课程
     *
     * @param courseInfoForm
     * @return
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoForm courseInfoVo);

    //查询课程发布信息
    CoursePublishVo getCoursePublishVoById(String courseId);

    Boolean publishCourseById(String courseId);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
