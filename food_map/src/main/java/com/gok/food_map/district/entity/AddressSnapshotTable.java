package com.gok.food_map.district.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 地址快照表
 * @TableName address_snapshot_table
 */
@TableName(value ="address_snapshot_table")
public class AddressSnapshotTable {
    /**
     * 地址ID
     */
    @TableId
    private Long addressId;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 快照时间
     */
    private Timestamp snapshotDate;

    public AddressSnapshotTable() {
    }

    public AddressSnapshotTable(Long addressId, Long userId, String receiver, String telephone, String detailAddress, String zipCode, LocalDateTime snapshotDate) {
        this.addressId = addressId;
        this.userId = userId;
        this.receiver = receiver;
        this.telephone = telephone;
        this.detailAddress = detailAddress;
        this.zipCode = zipCode;
        if (snapshotDate != null) {
            this.snapshotDate = Timestamp.valueOf(snapshotDate);
        }
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getSnapshotDate() {
        return snapshotDate.toLocalDateTime();
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        if (snapshotDate != null) {
            this.snapshotDate = Timestamp.valueOf(snapshotDate);

        }
    }
}