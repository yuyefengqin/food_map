package com.gok.food_map.controller;

import com.gok.food_map.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PageController {

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }

    @GetMapping("/foodMap")
    public String foodMap() {
        return "app.html";
    }

    @GetMapping("/userDetail")
    public String userDetail(HttpServletRequest request) {
        Map<String, String> tokenSafe = TokenUtil.getIdByTokenUnsafe(request);
        if (tokenSafe != null ) {
            return "userDetail.html";
        }else {
            return "app.html";
        }
    }

    @GetMapping("/userAdmin")
    public String userAdmin(HttpServletRequest request) {
        Map<String, String> tokenSafe = TokenUtil.getIdByTokenUnsafe(request);
        if (tokenSafe != null ) {
            return "user.html";
        }else {
            return "admin.html";
        }
    }
    @GetMapping("/merchantAdmin")
    public String merchantAdmin(HttpServletRequest request) {
        Map<String, String> tokenSafe = TokenUtil.getIdByTokenUnsafe(request);
        if (tokenSafe != null ) {
            return "merchant.html";
        }else {
            return "admin.html";
        }
    }
    @GetMapping("/productAdmin")
    public String productAdmin(HttpServletRequest request) {
        Map<String, String> tokenSafe = TokenUtil.getIdByTokenUnsafe(request);
        if (tokenSafe != null ) {
            return "product.html";
        }else {
            return "admin.html";
        }
    }
    @GetMapping("/orderAdmin")
    public String orderAdmin(HttpServletRequest request) {
        Map<String, String> tokenSafe = TokenUtil.getIdByTokenUnsafe(request);
        if (tokenSafe != null ) {
            return "order.html";
        }else {
            return "admin.html";
        }
    }
}
