package com.qiyu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author qiyu
 * @create 2020-06-23
 * @Description:
 */
public class ExcelDataDemoTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
          //写入
//        String fileName = "E:\\onlinedu\\test\\11.xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, ExcelDataDemo.class).sheet("写入方法一").doWrite(data());

        //读取
        // 写法1：
//        String fileName = "E:\\\\onlinedu\\\\test\\\\11.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, ExcelDataDemo.class, new ExcelListener()).sheet().doRead();

//            String cardNo = "01020000";
////        String substring = cardNo.substring(1, 4);
////        System.out.println(substring);
        byte[] decode = Base64.getDecoder().decode("5byg6Ziz");
        String s = new String(decode, "UTF-8");
        System.out.println(s);
    }
    //循环设置要添加的数据，最终封装到list集合中
    private static List<ExcelDataDemo> data() {
        List<ExcelDataDemo> list = new ArrayList<ExcelDataDemo>();
        for (int i = 0; i < 10; i++) {
            ExcelDataDemo data = new ExcelDataDemo();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
