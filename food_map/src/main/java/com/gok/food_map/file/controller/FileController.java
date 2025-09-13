package com.gok.food_map.file.controller;

import com.gok.food_map.file.dto.FileUploadDTO;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.file.vo.FileUploadVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class FileController {

    private final FileService fileService;

    //上传
    @PostMapping("/upload")
    public FileUploadVO upload(FileUploadDTO dto) {

        return fileService.upload(dto);
    }

    //下载
    @GetMapping("/download")
    public void download(Long id, HttpServletResponse response) {

        fileService.download(id, response);
    }
}
