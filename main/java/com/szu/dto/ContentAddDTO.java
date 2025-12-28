package com.szu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentAddDTO {
    private String title;
    private String coverImage;
    private String contentBody;
    private List<String> tags;
    private List<String> images;
}
