package com.szu.service.impl;

import com.szu.mapper.CommentMapper;
import com.szu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public String getCommentById(String id) {
        return commentMapper.getCommentById(id);
    }
}
