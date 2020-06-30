package com.qiyu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiyu.eduservice.entity.EduChapter;
import com.qiyu.eduservice.entity.EduVideo;
import com.qiyu.eduservice.entity.chapter.ChapterVo;
import com.qiyu.eduservice.entity.chapter.VideoVo;
import com.qiyu.eduservice.mapper.EduChapterMapper;
import com.qiyu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiyu.eduservice.service.EduVideoService;
import com.qiyu.servicebase.handler.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author qiyu
 * @since 2020-06-28
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoList(String courseId) {
        //最终要的到的数据列表
        ArrayList<ChapterVo> finalChapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);

        //获取课时信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> videos = videoService.list(queryWrapper2);


        chapters.forEach(chapter ->{
            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            //填充课时vo数据
            ArrayList<VideoVo> finalVideoVoArrayList = new ArrayList<>();

            videos.forEach(video ->{
                if(chapter.getId().equals(video.getChapterId())){
                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    finalVideoVoArrayList.add(videoVo);
                }
            });

            chapterVo.setChildren(finalVideoVoArrayList);
            finalChapterVoArrayList.add(chapterVo);
        });


        return finalChapterVoArrayList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();

        videoQueryWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(videoQueryWrapper);
        if(count >0){
            throw new GuliException(20001,"删除章节失败");
        }else {
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            //成功  1>0   0>0
            return result>0;
        }

    }
}
