package com.gok.food_map.log.util;

import java.util.UUID;

public class TokenUtils {
    // 生成一个随机Token（UUID去除横杠）
    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
