package com.szu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szu.context.BaseContext;
import com.szu.dto.CommentDTO;
import com.szu.entity.Comment;
import com.szu.entity.Content;
import com.szu.mapper.CommentMapper;
import com.szu.mapper.ContentMapper;
import com.szu.mapper.UserMapper;
import com.szu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getCommentById(String id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public void addComment(CommentDTO dto) {
        Content content = contentMapper.selectContentById(dto.getContentId());
        // 3. 评论内容校验
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new RuntimeException("评论内容不能为空");
        }
        if (dto.getContent().length() > 500) {
            throw new RuntimeException("评论内容不能超过500字");
        }

        Comment comment = new Comment();

        // 4. 设置评论信息
        comment.setId("comment_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().substring(0, 8));
        comment.setCreatedAt(String.valueOf(new Date()));
        comment.setLikeCount(0);
        comment.setStatus(1);
        comment.setUserId(BaseContext.getCurrentId().intValue());
        comment.setUsername(userMapper.selectById(BaseContext.getCurrentId().intValue()).getName());
        comment.setContent(dto.getContent());
        comment.setReplies("[]");

        // 5. 获取现有评论列表并添加新评论
        String commentList = content.getComments();

        if(commentList == null || commentList.trim().isEmpty()){
            commentList = "[]";
        }

        List<Comment> comments = JSONObject.parseArray(commentList, Comment.class);
        if (dto.getCommentId() == null) {
            comments.add(comment);

            content.setComments(JSONObject.toJSONString(comments));
        }else {
            for (Comment comment1 : comments) {
                if(comment1.getId().equals(dto.getCommentId())){
                    if(comment1.getReplies().equals("[]")){
                        comment1.setReplies(JSONObject.toJSONString(List.of(comment)));
                    }else{
                        List<Comment> replies = JSONObject.parseArray(comment1.getReplies(), Comment.class);
                        replies.add(comment);
                        comment1.setReplies(JSONObject.toJSONString(replies));
                    }
                    break;
                }
            }

            content.setComments(JSONObject.toJSONString(comments));
        }

        // 6. 更新评论数量
        content.setCommentCount(content.getCommentCount() + 1);
        content.setUpdatedAt(LocalDateTime.now());

        // 7. 保存到数据库
        int result = contentMapper.update(content);
        if (result != 1) {
            throw new RuntimeException("发表评论失败");
        }
    }
}
