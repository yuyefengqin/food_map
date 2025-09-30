package com.gok.food_map.file.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileUploadDTO {
    private MultipartFile file;

    public FileUploadDTO(MultipartFile file) {
        this.file = file;
    }
    public FileUploadDTO(){

    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
