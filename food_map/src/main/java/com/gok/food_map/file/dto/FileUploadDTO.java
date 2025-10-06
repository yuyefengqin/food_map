package com.gok.food_map.file.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDTO {
    private MultipartFile file;

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
