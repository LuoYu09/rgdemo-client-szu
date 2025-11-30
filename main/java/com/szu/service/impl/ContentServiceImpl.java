package com.szu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.szu.dto.ListContentsDTO;
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
        List<String> tags = contentMapper.selectTagsByContentId(id);
        String name = contentMapper.selectAuthorNameByContentId(id);

        ContentVO contentVO = new ContentVO();
        BeanUtils.copyProperties(content, contentVO);
        contentVO.setTags(tags);
        contentVO.setAuthorName(name);
        contentVO.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(content.getCreatedAt()));

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
    public List<Tag> listTags() {
        log.info("查询所有标签");

        return contentMapper.listTags();
    }
}
