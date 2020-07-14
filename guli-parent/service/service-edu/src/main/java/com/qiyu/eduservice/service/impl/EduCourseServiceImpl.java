package com.qiyu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiyu.eduservice.entity.EduCourse;
import com.qiyu.eduservice.entity.EduCourseDescription;
import com.qiyu.eduservice.entity.vo.CourseInfoForm;
import com.qiyu.eduservice.entity.vo.CoursePublishVo;
import com.qiyu.eduservice.entity.vo.CourseQuery;
import com.qiyu.eduservice.mapper.EduCourseMapper;
import com.qiyu.eduservice.service.EduChapterService;
import com.qiyu.eduservice.service.EduCourseDescriptionService;
import com.qiyu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyu.eduservice.service.EduVideoService;
import com.qiyu.servicebase.handler.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;


    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean resultCourseInfo = this.save(eduCourse);
        if (!resultCourseInfo) {
            throw new GuliException(20001, "课程信息保存失败");
        }
        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(eduCourse.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if (!resultDescription) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }

        return eduCourse.getId();
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = this.getById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.selectPublishVoById(courseId);
        return coursePublishVo;
    }

    @Override
    public Boolean publishCourseById(String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(EduCourse.COURSE_NORMAL);
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }



        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);




    }

    @Override
    public boolean removeCourseById(String courseId) {
        //根据id删除所有视频
        eduVideoService.removeByCourseId(courseId);

        //根据id删除所有章节
        eduChapterService.removeByCourseId(courseId);

        Integer result = baseMapper.deleteById(courseId);
        return null != result && result > 0;
    }
}
