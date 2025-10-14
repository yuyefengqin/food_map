package com.gok.food_map.exception;



import com.gok.food_map.advice.ServiceMsg;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private ServiceMsg serviceMsg;
    public ServiceException(ResponseEnum responseEnum) {
        this.serviceMsg = new ServiceMsg(responseEnum);
    }
    public static void build(ResponseEnum responseEnum) {
        throw new ServiceException(responseEnum);
    }

    public ServiceMsg getServiceMsg() {
        return serviceMsg;
    }

    public void setServiceMsg(ServiceMsg serviceMsg) {
        this.serviceMsg = serviceMsg;
    }
}
