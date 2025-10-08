package com.gok.food_map.order.vo;

import lombok.Data;

import java.util.List;
@Data
public class UserOrderInfoVO {
//    userOrderInfo:[{
//                orderId:'436543564563413641',
//                tradeNo:'3678434443651367',
//                createTime:'2017-05-11',
//                payTime:'2017-05-11',
//                shipTime:'2017-05-11',
//                receiveTime:'2017-05-15',
//                completeTime:'2017-05-15',
//                merchantName:'水产店',
//                orderAmount:'332.00',
//                deliveryFee:'10.00',
//                totalQuantity:'2',
//                deliveryMethod:'顺丰速递',
//                receiver:'张三',
//                telephone:'12345678987',
//                detailAddress:'awgdagdkagdagd',
//                logisticsNo:'64653453643644434',
//                orderStatus:'已完成',
//                buyProducts:[{
//                      mainImage:'1973384655986180097',
//                      spuName:'三文鱼',
//                      specs:["十三香","中","一区"],
//                      unitPrice:'166.00',
//                      quantity:'2'
//        }]
//    }],
    private String orderId;
    private String tradeNo;
    private String merchantName;
    private String orderAmount;
    private String deliveryFee;
    private Integer totalQuantity;
    private String deliveryMethod;
    private String logisticsNo;
    private String receiver;
    private String telephone;
    private String detailAddress;
    private String orderStatus;
    private String createTime;
    private String payTime;
    private String shipTime;
    private String receiveTime;
    private String completeTime;
    private List<BuyProduct> buyProducts;

    public UserOrderInfoVO() {
    }

    public UserOrderInfoVO(String orderId, String tradeNo, String merchantName, String orderAmount, String deliveryFee, Integer totalQuantity, String deliveryMethod, String logisticsNo, String receiver, String telephone, String detailAddress, String orderStatus, String createTime, String payTime, String shipTime, String receiveTime, String completeTime, List<BuyProduct> buyProducts) {
        this.orderId = orderId;
        this.tradeNo = tradeNo;
        this.merchantName = merchantName;
        this.orderAmount = orderAmount;
        this.deliveryFee = deliveryFee;
        this.totalQuantity = totalQuantity;
        this.deliveryMethod = deliveryMethod;
        this.logisticsNo = logisticsNo;
        this.receiver = receiver;
        this.telephone = telephone;
        this.detailAddress = detailAddress;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.payTime = payTime;
        this.shipTime = shipTime;
        this.receiveTime = receiveTime;
        this.completeTime = completeTime;
        this.buyProducts = buyProducts;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getShipTime() {
        return shipTime;
    }

    public void setShipTime(String shipTime) {
        this.shipTime = shipTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public List<BuyProduct> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<BuyProduct> buyProducts) {
        this.buyProducts = buyProducts;
    }
}

