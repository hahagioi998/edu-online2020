package com.qiyu.eduservice.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiyu.eduservice.entity.EduSubject;
import com.qiyu.eduservice.entity.excel.ExcelSubjectVo;
import com.qiyu.eduservice.service.EduSubjectService;
import com.qiyu.servicebase.handler.exception.GuliException;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectVo> {
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(ExcelSubjectVo excelSubjectVo, AnalysisContext analysisContext) {
        if(excelSubjectVo == null){
            throw new GuliException(20001,"添加失败");
        }
        //添加一级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService,excelSubjectVo.getOneSubjectName());
        if(existOneSubject == null){//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(excelSubjectVo.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }
        //添加二级分类
        String pid = existOneSubject.getId();
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService,excelSubjectVo.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(excelSubjectVo.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }
    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String twoSubjectName, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",twoSubjectName);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String oneSubjectName) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",oneSubjectName);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
