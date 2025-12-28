package com.szu.mapper;

import com.github.pagehelper.Page;
import com.szu.dto.AddRatingDTO;
import com.szu.entity.AvgRating;
import com.szu.entity.Content;
import com.szu.entity.PageContents;
import com.szu.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    Content selectContentById(Integer id);

    String selectAuthorNameByContentId(Integer id);

    Page<Integer> listContents(PageContents pageDto);

    void addRating(AddRatingDTO dto);

    void addRatingCount(AddRatingDTO dto);

    AvgRating getRatingCountByContentId(Integer id);

    Integer getRating(Integer contentId, Long userId);

    void addContent(Content content);

    void likeContent(Content content);

    void addAvgRating(Integer id);

    int update(Content content);
}
