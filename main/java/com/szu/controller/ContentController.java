package com.szu.controller;

import com.szu.entity.ContentVO;
import com.szu.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public ContentVO getContentById(@PathVariable Integer id){
        return contentService.selectContentById(id);
    }
}
