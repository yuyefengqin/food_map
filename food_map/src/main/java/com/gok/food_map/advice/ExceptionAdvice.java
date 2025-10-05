package com.gok.food_map.advice;


import com.gok.food_map.exception.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ServiceException.class)
    public ServiceMsg exceptionHandler(ServiceException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return e.getServiceMsg();
    }

}
