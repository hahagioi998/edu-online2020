package com.qiyu.eduservice.service;

import com.qiyu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyu.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoList(String courseId);

    Boolean deleteChapter(String chapterId);

    Boolean removeByCourseId(String courseId);
}
