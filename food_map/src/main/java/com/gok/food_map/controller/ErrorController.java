package com.gok.food_map.controller;


import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.util.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @PostMapping("/token")
    public Resp<?> token() {
        return Resp.error(ResponseEnum.NO_TOKEN);
    }
    @PostMapping("/accountError")
    public Resp<?> AccountError() {
        return Resp.error(ResponseEnum.USERNAME_PASSWORD_ERROR);
    }

    @PostMapping("/tokenError")
    public Resp<?> tokenError() {
        return Resp.error(ResponseEnum.TOKEN_VERIFY_ERROR);
    }
}
