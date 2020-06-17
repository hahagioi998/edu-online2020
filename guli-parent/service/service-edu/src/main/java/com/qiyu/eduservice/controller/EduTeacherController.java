package com.qiyu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiyu.commonutils.R;
import com.qiyu.eduservice.entity.EduTeacher;
import com.qiyu.eduservice.entity.vo.TeacherQuery;
import com.qiyu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-06-16
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }


    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable("id") String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) return R.ok();else return R.error();
    }


    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                           @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }
    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }
}

