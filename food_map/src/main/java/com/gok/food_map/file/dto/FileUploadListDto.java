package com.gok.food_map.file.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUploadListDto {
    private List<MultipartFile> fileList;

    public FileUploadListDto() {
    }

    public FileUploadListDto(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }

    public List<MultipartFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }
}
