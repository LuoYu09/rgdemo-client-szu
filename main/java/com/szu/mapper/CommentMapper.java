package com.szu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Select("select comments from contents where id = #{id}")
    String getCommentById(String id);
}
