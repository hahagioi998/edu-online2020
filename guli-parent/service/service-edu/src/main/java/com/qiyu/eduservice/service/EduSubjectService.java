package com.qiyu.eduservice.service;

import com.qiyu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyu.eduservice.entity.vo.SubjectOneVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-23
 */
public interface EduSubjectService extends IService<EduSubject> {

    //将数据库内容导出到excel
    void excelWriteFile(String path);
    //导入excel文件内容到数据库
    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);
    //显示课程信息tree结构
    List<SubjectOneVo> getAllSubject();
}
