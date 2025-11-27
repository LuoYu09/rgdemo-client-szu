package com.szu.service.impl;

import com.szu.entity.Content;
import com.szu.entity.ContentVO;
import com.szu.mapper.ContentMapper;
import com.szu.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

        ContentVO contentVO = new ContentVO();
        BeanUtils.copyProperties(content, contentVO);
        contentVO.setTags(tags);
        contentVO.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(content.getCreatedAt()));

        return contentVO;
    }
}
