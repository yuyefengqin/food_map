package com.gok.food_map.file.dto;

public class FileRemoveDTO {
    private Long id;
    public FileRemoveDTO() {}
    public FileRemoveDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
