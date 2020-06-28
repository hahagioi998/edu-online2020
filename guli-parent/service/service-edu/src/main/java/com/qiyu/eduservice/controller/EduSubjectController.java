package com.qiyu.eduservice.controller;


import com.qiyu.commonutils.R;
import com.qiyu.eduservice.entity.vo.SubjectOneVo;
import com.qiyu.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-06-23
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @GetMapping("{fileName}")
    public R excelWrite(@PathVariable("fileName") String fileName){
        String path = "E:\\onlinedu\\test\\" + fileName;
        eduSubjectService.excelWriteFile(path);
        return R.ok();
    }

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(@RequestParam("file") MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.importSubjectData(file,eduSubjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    //课程分类显示tree结构
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        List<SubjectOneVo> subjectOneVoList = eduSubjectService.getAllSubject();
        return R.ok().data("list", subjectOneVoList);
    }
}

