package com.gok.food_map.file.controller;

import com.gok.food_map.file.dto.FileRemoveDTO;
import com.gok.food_map.file.dto.FileUploadDTO;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.file.vo.FileUploadVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/MFile")
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class FileController{
    @Resource
    private  FileService service;

    @PostMapping("/upload")
    public Map<String,String> upload(FileUploadDTO dto) {
        String id = service.upload(dto).getId();
        return Collections.singletonMap("id", id);
    }

    @GetMapping("/download")
    public void download(Long id, HttpServletResponse response) {
        service.download(id, response);
    }
    @PostMapping("/remove")
    public void remove(@RequestBody FileRemoveDTO dto) {
        service.remove(dto.getId());
    }
}

