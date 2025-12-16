package com.szu.controller;

import com.szu.result.Result;
import com.szu.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/client/common")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);

        //获取原文件名
        String originalFilName = file.getOriginalFilename();
        //截取后缀
        String extension = originalFilName.substring(originalFilName.lastIndexOf("."));


        String uid = UUID.randomUUID().toString();
        String url = null;
        try {
            url = aliOssUtil.upload(file.getBytes(),uid + extension);

            return Result.success(url);
        } catch (IOException e) {
            log.info("文件上传失败：{}",e);

            return Result.error("文件上传失败");
        }
    }
}
