package com.gok.food_map.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


//Token生成工具类 用于生成和验证JWT令牌
@Slf4j
public class TokenUtil {

//    固定密钥
    private final static String ENCRYPT_KEY = "czh0418";

//    JWT令牌的过期时间 单位为分钟
    private final static int EXPIRE_TIME = 5;


//    JWT令牌的发行者
    private static final String ISSUER = "ChenZhiHao";

//    通过设置json格式的形参传入，内容通常是id等识别参数
//    返回生成的JWT令牌字符串
    public static String createToken(JSONObject json) {
        // 使用JWT创建一个令牌
        return JWT.create()
                // 设置令牌的主题 即json对象转换后的字符串 不要把密码封装进去因为不安全
                .withSubject(json.toString())
                // 设置令牌的发行者
                .withIssuer(ISSUER)
                // 设置令牌的过期时间 以当前时间为基础加上设定的过期时间
                .withExpiresAt(DateUtil.offsetMinute(new Date(), EXPIRE_TIME))
                // 设置自定义的声明
//                .withClaim("test", "123")//可改为其他重要认证信息
//                .withClaim("role", "admin")//role键对应值为身份 如admin和user
                // 使用HMAC256算法对令牌进行签名加密
                .sign(Algorithm.HMAC256(ENCRYPT_KEY));
    }

//    验证JWT令牌的有效性
    public static boolean verifyToken(String token) {
        try {
            // 创建一个 JWT 验证器 使用 HMAC256 算法 密钥为 ENCRYPT_KEY 发行者为 ISSUER
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ENCRYPT_KEY))
                    .withIssuer(ISSUER)
                    .build();

            // 对令牌进行验证
            jwtVerifier.verify(token);
            // 如果验证成功，返回 true
            return true;
        } catch (Exception e) {
            // 验证失败，打印异常信息，并返回false
            log.error(e.getMessage());
            return false;
        }
    }
//    检查token令牌是否过期
    public static boolean checkToken(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }
}

