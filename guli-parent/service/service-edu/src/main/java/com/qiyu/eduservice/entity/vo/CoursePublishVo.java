package com.qiyu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author qiyu
 * @create 2020-06-30
 * @Description:
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
