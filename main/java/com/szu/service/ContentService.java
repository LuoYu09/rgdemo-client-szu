package com.szu.service;

import com.szu.dto.AddRatingDTO;
import com.szu.dto.ContentAddDTO;
import com.szu.dto.ListContentsDTO;
import com.szu.entity.Tag;
import com.szu.result.PageResult;
import com.szu.vo.ContentVO;

import java.util.List;

public interface ContentService{
    ContentVO selectContentById(Integer id);

    PageResult listContents(ListContentsDTO dto);

    void addRating(AddRatingDTO dto);

    void addContent(ContentAddDTO dto);

    void likeContent(Integer contentId);

}
