package com.gok.food_map.district.dto;

public class AddressGetDto {
    private String id;

    public AddressGetDto(String id) {
        this.id = id;
    }
    public AddressGetDto() {}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
