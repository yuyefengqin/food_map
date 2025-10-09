package com.gok.food_map.district.vo;

public class AddressGetVO {
    private String addressId;
    private String receiver;
    private String telephone;
    private String detailAddress;
    private String zipCode;
    private Boolean isDefault;

    public AddressGetVO(String addressId, String receiver, String telephone, String detailAddress, String zipCode, Boolean isDefault) {
        this.addressId = addressId;
        this.receiver = receiver;
        this.telephone = telephone;
        this.detailAddress = detailAddress;
        this.zipCode = zipCode;
        this.isDefault = isDefault;
    }

    public AddressGetVO() {
    }
    public String getAddressId() {
        return addressId;
    }
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}