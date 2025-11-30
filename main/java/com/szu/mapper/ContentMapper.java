package com.szu.mapper;

import com.github.pagehelper.Page;
import com.szu.entity.Content;
import com.szu.entity.PageContents;
import com.szu.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    Content selectContentById(Integer id);

    List<String> selectTagsByContentId(Integer id);

    String selectAuthorNameByContentId(Integer id);

    Page<Integer> listContents(PageContents pageDto);

    List<Tag> listTags();
}
