package com.szu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListContentsDTO {
    /**
     * 分页参数（页码），默认为1
     */
    private Integer page;
    /**
     * 分页参数（每页文章数），默认为10
     */
    private Integer pageSize;
    /**
     * 排序方式：0按发布时间排序，1按浏览量排序，默认为0
     */
    private Integer sortBy;
    /**
     * 0表示降序，1表示升序，默认为0
     */
    private Integer sortOrder;
}
