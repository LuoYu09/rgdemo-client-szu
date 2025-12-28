package com.szu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.szu.context.BaseContext;
import com.szu.dto.AddRatingDTO;
import com.szu.dto.ContentAddDTO;
import com.szu.dto.ListContentsDTO;
import com.szu.entity.AvgRating;
import com.szu.entity.Content;
import com.szu.entity.PageContents;
import com.szu.entity.Tag;
import com.szu.result.PageResult;
import com.szu.vo.ContentVO;
import com.szu.mapper.ContentMapper;
import com.szu.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public ContentVO selectContentById(Integer id) {
        log.info("根据id查询文章：{}", id);

        Content content = contentMapper.selectContentById(id);
        String name = contentMapper.selectAuthorNameByContentId(id);

        AvgRating avgRating = contentMapper.getRatingCountByContentId(id);
        int totalRatings = avgRating.getFive() + avgRating.getFour() + avgRating.getThree() + avgRating.getTwo() + avgRating.getOne();

        Integer rating = contentMapper.getRating(id, BaseContext.getCurrentId());

        ContentVO contentVO = new ContentVO();
        BeanUtils.copyProperties(content, contentVO);
        contentVO.setAuthorName(name);
        if (rating != null) {
            contentVO.setRating(rating);
        }else {
            contentVO.setRating(0);
        }
        contentVO.setRatingCount(totalRatings);
        if(totalRatings == 0){
            contentVO.setAvgRating(0.0);
        } else {
            double average = (5.0 * avgRating.getFive() + 4.0 * avgRating.getFour() + 3.0 * avgRating.getThree()
                    + 2.0 * avgRating.getTwo() + 1.0 * avgRating.getOne()) / totalRatings;
            contentVO.setAvgRating(Math.round(average * 10.0) / 10.0);
        }
        contentVO.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(content.getCreatedAt()));

        if (content.getLikes() != null) {
            List<Integer> likesList = JSON.parseArray(content.getLikes(), Integer.class);
            contentVO.setLikeCount(likesList.size());
            contentVO.setLike(likesList.contains(BaseContext.getCurrentId().intValue()) ? 1 : 0);
        }else {
            contentVO.setLikeCount(0);
            contentVO.setLike(0);
        }

        return contentVO;
    }

    @Override
    public PageResult listContents(ListContentsDTO dto) {
        log.info("分页查询文章列表：{}", dto);

        if(dto.getPage() == null || dto.getPage() < 1){
            dto.setPage(1);
        }
        if(dto.getPageSize() == null || dto.getPageSize() < 1){
            dto.setPageSize(10);
        }
        if (dto.getSortBy() == null) {
            dto.setSortBy(0);
        }
        if (dto.getSortOrder() == null) {
            dto.setSortOrder(0);
        }

        PageContents pageDto = new PageContents();
        BeanUtils.copyProperties(dto, pageDto);
        if(dto.getSortBy() == 0){
            pageDto.setSortBy("created_at");
        } else {
            pageDto.setSortBy("view_count");
        }
        if(dto.getSortOrder() == 0){
            pageDto.setSortOrder("DESC");
        } else {
            pageDto.setSortOrder("ASC");
        }

        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        Page<Integer> result = contentMapper.listContents(pageDto);

        List<Integer> contentIds = result.getResult();
        List<ContentVO> contents = new ArrayList<>();
        for (Integer contentId : contentIds) {
            ContentVO contentVO = selectContentById(contentId);
            contents.add(contentVO);
        }

        return new PageResult(result.getTotal(), contents);
    }

    @Override
    public void addRating(AddRatingDTO dto) {
        log.info("添加文章评分：{}", dto);

        contentMapper.addRating(dto);
        contentMapper.addRatingCount(dto);
    }

    @Override
    public void addContent(ContentAddDTO dto) {
        log.info("添加文章：{}", dto);

        Content content = new Content();
        BeanUtils.copyProperties(dto, content);
        content.setAuthorId(BaseContext.getCurrentId().intValue());
        content.setViewCount(0);
        content.setCommentCount(0);
        content.setStatus(1);
        content.setIsDeleted(0);
        content.setLikes("[]");
        content.setComments("[]");
        content.setImages(JSONObject.toJSONString(dto.getImages()));
        content.setTags(JSONObject.toJSONString(dto.getTags()));

        contentMapper.addContent(content);
        contentMapper.addAvgRating(content.getId());
    }

    @Override
    public void likeContent(Integer contentId) {
        log.info("点赞文章：{}", contentId);

        Content content = contentMapper.selectContentById(contentId);
        String likes = content.getLikes();
        List<Integer> likesList = JSON.parseArray(likes, Integer.class);
        Integer userId = BaseContext.getCurrentId().intValue();

        if (likesList.contains(userId)) {
            likesList.remove(userId);
        } else {
            likesList.add(userId);
        }

        content.setLikes(JSONObject.toJSONString(likesList));
        contentMapper.likeContent(content);
    }
}
