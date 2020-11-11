package com.qiyu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiyu.commonutils.R;
import com.qiyu.eduservice.entity.EduCourse;
import com.qiyu.eduservice.entity.vo.CourseInfoForm;
import com.qiyu.eduservice.entity.vo.CoursePublishVo;
import com.qiyu.eduservice.entity.vo.CourseQuery;
import com.qiyu.eduservice.service.EduCourseService;
import com.qiyu.servicebase.idempotent.annotation.Idempotent;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Idempotent(key = "#courseId")
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
            @PathVariable("courseId") String courseId) {

        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(courseId);
        return R.ok().data("item", courseInfoForm);
    }


    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{courseId}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("courseId") String courseId) {

        courseService.publishCourseById(courseId);
        return R.ok();
    }


    @ApiOperation(value = "分页课程列表")
    @GetMapping("pageQuery/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery) {

        Page<EduCourse> pageParam = new Page<>(page, limit);
        System.out.println(courseQuery);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("removeById/{courseId}")
    public R removeById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {

        boolean result = courseService.removeCourseById(courseId);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }

}

