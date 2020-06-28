package com.qiyu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
@Data
public class ExcelSubjectVo {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
