package com.gok.food_map.order.vo;

import lombok.*;

import java.util.List;
@Builder
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

}

