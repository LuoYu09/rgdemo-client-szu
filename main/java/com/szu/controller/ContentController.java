package com.szu.controller;

import com.szu.dto.ListContentsDTO;
import com.szu.entity.Tag;
import com.szu.result.PageResult;
import com.szu.vo.ContentVO;
import com.szu.result.Result;
import com.szu.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public Result<ContentVO> getContentById(@PathVariable Integer id){
        ContentVO result = contentService.selectContentById(id);

        return Result.success(result);
    }

    @PostMapping("/page")
    public Result<PageResult> listContents(@RequestBody ListContentsDTO dto){
        PageResult result = contentService.listContents(dto);

        return Result.success(result);
    }

    @GetMapping("/tags")
    public Result<List<Tag>> listTags(){
        List<Tag> tags = contentService.listTags();

        return Result.success(tags);
    }
}
