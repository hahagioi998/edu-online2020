package com.qiyu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiyu
 * @create 2020-06-24
 * @Description:
 */
@Data
public class SubjectOneVo {
    private String id;
    private String title;
    private List<SubjectTwoVo> children = new ArrayList<>();
}
