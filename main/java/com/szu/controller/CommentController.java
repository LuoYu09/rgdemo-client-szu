package com.szu.controller;

import com.szu.result.Result;
import com.szu.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public Result<String> getCommentById(@PathVariable String id) {
        String comment = commentService.getCommentById(id);

        return Result.success(comment);
    }
}
