package com.szu.mapper;

import com.szu.entity.Content;
import com.szu.entity.ContentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    Content selectContentById(Integer id);

    List<String> selectTagsByContentId(Integer id);
}
