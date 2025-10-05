package com.gok.food_map.exception;



import com.gok.food_map.advice.ServiceMsg;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private ServiceMsg serviceMsg;
    public ServiceException(String msg) {
        this.serviceMsg = new ServiceMsg(msg);
    }
    public static void build(String msg) {
        throw new ServiceException(msg);
    }

    public ServiceMsg getServiceMsg() {
        return serviceMsg;
    }

    public void setServiceMsg(ServiceMsg serviceMsg) {
        this.serviceMsg = serviceMsg;
    }
}
