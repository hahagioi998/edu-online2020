package com.qiyu.servicebase.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
@ToString
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
