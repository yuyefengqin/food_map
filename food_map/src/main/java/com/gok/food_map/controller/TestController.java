package com.gok.food_map.controller;



import com.gok.food_map.annotation.Auth;
import com.gok.food_map.util.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Auth
    @PostMapping("/hello")
    public Resp<?> hello() {
        return Resp.ok("登录成功");
    }

    @PostMapping("/hi")
    public Resp<?> hi() {
        return Resp.ok("登录成功");
    }
}