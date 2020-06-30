package com.qiyu.eduservice.controller;


import com.qiyu.commonutils.R;
import com.qiyu.eduservice.entity.vo.CourseInfoForm;
import com.qiyu.eduservice.entity.vo.CoursePublishVo;
import com.qiyu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;


    @ApiOperation(value = "新增课程")
    @PostMapping("saveCourseInfo")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        String courseId = courseService.saveCourseInfo(courseInfoForm);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }

    }


    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoForm courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }


    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("getCoursePublishVoById/{courseId}")
    public R getCoursePublishVoById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable("courseId") String courseId){

        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(courseId);
        return R.ok().data("item", courseInfoForm);
    }


    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{courseId}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("courseId") String courseId){

        courseService.publishCourseById(courseId);
        return R.ok();
    }
}

