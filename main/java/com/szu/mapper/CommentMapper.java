package com.szu.mapper;

import com.szu.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Select("select comments from contents where id = #{id}")
    String getCommentById(String id);


}
