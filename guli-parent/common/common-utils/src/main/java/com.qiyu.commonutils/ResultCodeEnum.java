package com.qiyu.commonutils;

public enum ResultCodeEnum {
    FILE_UPLOAD_ERROR(20002,"文件上传失败");
    private Integer code;
    private String value;

    ResultCodeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
