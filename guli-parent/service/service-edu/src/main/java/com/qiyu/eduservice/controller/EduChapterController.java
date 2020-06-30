package com.qiyu.eduservice.controller;


import com.qiyu.commonutils.R;
import com.qiyu.eduservice.entity.EduChapter;
import com.qiyu.eduservice.entity.chapter.ChapterVo;
import com.qiyu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;
    //获取章节及小节数据
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoList(@PathVariable("courseId") String courseId){
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoList(courseId);
        return R.ok().data("items", chapterVoList);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }

    //查询章节
    @GetMapping("getChapter/{chapterId}")
    public R getChapter(@PathVariable("chapterId")String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除章节
    @DeleteMapping("deleteChapter/{chapterId}")
    public R updateChapter(@PathVariable("chapterId")String chapterId){
        Boolean deleteFlag = chapterService.deleteChapter(chapterId);
        if(deleteFlag){
            return R.ok();
        }else {
            return R.error();
        }

    }

}

