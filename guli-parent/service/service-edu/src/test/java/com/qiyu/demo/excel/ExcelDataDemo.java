package com.qiyu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
@Data
@ToString
public class ExcelDataDemo {
    //设置表头名称
    @ExcelProperty(index = 0)
    private int sno;

    //设置表头名称
    @ExcelProperty(index = 1)
    private String sname;
}
