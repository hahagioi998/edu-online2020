package com.qiyu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
@Data
@ToString
public class ExcelSubjectDto {

    //设置表头名称
    @ExcelProperty("一级分类")
    private String sno;

    //设置表头名称
    @ExcelProperty("二级分类")
    private String sname;
}
