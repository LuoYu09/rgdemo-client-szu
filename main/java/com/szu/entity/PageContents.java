package com.szu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageContents {
    private Integer page;

    private Integer pageSize;

    private String sortBy;

    private String sortOrder;

    private List<Integer> tagIds;
}
