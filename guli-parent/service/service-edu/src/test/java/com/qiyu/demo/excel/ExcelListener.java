package com.qiyu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener<ExcelDataDemo> {
    //创建list集合封装最终的数据
    List<ExcelDataDemo> list = new ArrayList<ExcelDataDemo>();
    @Override
    public void invoke(ExcelDataDemo excelDataDemo, AnalysisContext analysisContext) {
        System.out.println("*****" + excelDataDemo);
    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
