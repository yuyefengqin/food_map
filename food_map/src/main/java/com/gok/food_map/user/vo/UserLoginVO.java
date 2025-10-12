package com.gok.food_map.user.vo;

public class UserLoginVO {
    private String id;
    private String code;
    private String name;
    private String gender;
    private String avatar;
    private String city;
    private Integer levelId;
    private String createTime;
    private String cityName;
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public UserLoginVO(String id, String code, String name, String gender, String avatar, String city, Integer levelId, String createTime, String cityName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.avatar = avatar;
        this.city = city;
        this.levelId = levelId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public UserLoginVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
