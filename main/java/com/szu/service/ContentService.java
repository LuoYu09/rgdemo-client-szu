package com.szu.service;

import com.szu.dto.ListContentsDTO;
import com.szu.result.PageResult;
import com.szu.vo.ContentVO;

public interface ContentService{
    ContentVO selectContentById(Integer id);

    PageResult listContents(ListContentsDTO dto);
}
