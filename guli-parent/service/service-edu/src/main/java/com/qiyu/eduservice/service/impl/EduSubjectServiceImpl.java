package com.qiyu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiyu.eduservice.entity.EduSubject;
import com.qiyu.eduservice.entity.excel.ExcelSubjectDto;
import com.qiyu.eduservice.entity.excel.ExcelSubjectVo;
import com.qiyu.eduservice.entity.vo.SubjectOneVo;
import com.qiyu.eduservice.entity.vo.SubjectTwoVo;
import com.qiyu.eduservice.mapper.EduSubjectMapper;
import com.qiyu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyu.servicebase.handler.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    EduSubjectMapper eduSubjectMapper;

    @Override
    public void excelWriteFile(String path) {
        List<ExcelSubjectDto> excelSubjectDtoList = new ArrayList<>();
        List<EduSubject> eduSubjectList = eduSubjectMapper.selectList(null);

        for (EduSubject eduSubject : eduSubjectList) {
            QueryWrapper<EduSubject> QueryWrapper = new QueryWrapper<>();
            ExcelSubjectDto excelSubjectDto = new ExcelSubjectDto();
            String parentId = eduSubject.getParentId();
            if(!"0".equals(parentId)){
                QueryWrapper.eq("id",parentId);
                EduSubject eduSubjectParent = eduSubjectMapper.selectOne(QueryWrapper);
                excelSubjectDto.setSno(eduSubjectParent.getTitle());
                excelSubjectDto.setSname(eduSubject.getTitle());
                excelSubjectDtoList.add(excelSubjectDto);
            }
        }
        EasyExcel.write(path, ExcelSubjectDto.class).sheet("edu_subject").doWrite(excelSubjectDtoList);
    }

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectVo.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<SubjectOneVo> getAllSubject() {

        //最终要的到的数据列表
        ArrayList<SubjectOneVo> finalOneSubjectList = new ArrayList<>();


        //查询所有一级分类信息
        QueryWrapper<EduSubject> queryOneWrapper = new QueryWrapper<>();
        queryOneWrapper.eq("parent_id",0);
        queryOneWrapper.orderByAsc("sort", "id");
        List<EduSubject> eduOneSubjects = baseMapper.selectList(queryOneWrapper);


        //查询所有二级分类信息
        QueryWrapper<EduSubject> queryTwoWrapper = new QueryWrapper<>();
        queryTwoWrapper.ne("parent_id", 0);
        queryTwoWrapper.orderByAsc("sort", "id");
        List<EduSubject> eduTwoSubjects = baseMapper.selectList(queryTwoWrapper);

        eduOneSubjects.forEach(eduOneSubject ->{
            //填充一级分类vo数据
            SubjectOneVo subjectOneVo = new SubjectOneVo();
            BeanUtils.copyProperties(eduOneSubject,subjectOneVo);

            //填充二级分类vo数据
            ArrayList<SubjectTwoVo> subjectTwoVoArrayList = new ArrayList<>();
            eduTwoSubjects.forEach(eduTwoSubject->{
                if(eduTwoSubject.getParentId().equals(eduOneSubject.getId())){
                    SubjectTwoVo subjectTwoVo = new SubjectTwoVo();
                    BeanUtils.copyProperties(eduTwoSubject,subjectTwoVo);
                    subjectTwoVoArrayList.add(subjectTwoVo);
                }
            });

            //组装返回对象集合
            subjectOneVo.setChildren(subjectTwoVoArrayList);
            finalOneSubjectList.add(subjectOneVo);

        });
        return finalOneSubjectList;
    }
}
