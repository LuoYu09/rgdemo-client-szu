package com.szu.service;

import com.szu.dto.CommentDTO;

public interface CommentService {
    String getCommentById(String id);

    void addComment(CommentDTO dto);
}
