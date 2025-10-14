package com.gok.food_map.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


//Token生成工具类 用于生成和验证JWT令牌
@Slf4j
public class TokenUtil {

//    固定密钥
    private final static String ENCRYPT_KEY = "czh0418";

//    JWT令牌的过期时间 单位为分钟
    private final static int EXPIRE_TIME = 60;


//    JWT令牌的发行者
    private static final String ISSUER = "ChenZhiHao";

//    通过设置json格式的形参传入 内容为用户id
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

    public static Map<String,String> getIdByTokenSafe(HttpServletRequest request) {
        if(request == null){
            ServiceException.build(ResponseEnum.NO_TOKEN);
        }
        Object o = request.getSession().getAttribute("token");
        if(o == null){
            ServiceException.build(ResponseEnum.NO_TOKEN);
        }
        String token = o.toString();
        if (token.isEmpty() || token.isBlank()) {
            ServiceException.build(ResponseEnum.TOKEN_VERIFY_ERROR);
        }
        if (TokenUtil.checkToken(token)) {
            ServiceException.build(ResponseEnum.TOKEN_EX);
        }
        return TokenToMap(token);

    }
    public static Map<String,String> getIdByTokenUnsafe(HttpServletRequest request) {
        if(request == null){
            return null;
        }
        Object o = request.getSession().getAttribute("token");
        if(o == null){
            return null;
        }
        String token = o.toString();
        if (token.isEmpty() || token.isBlank()) {
            return null;
        }
        if (TokenUtil.checkToken(token)) {
            return null;
        }
        return TokenToMap(token);

    }

    private static Map<String, String> TokenToMap(String token) {
        List<String> stream = Arrays.stream(JWT.decode(token)
                .getClaim("sub")
                .toString()
                .replace("\"", "")
                .replace(" ", "")
                .replace("-", "")
                .replace("{", "")
                .replace("}", "")
                .replace("\\", "")
                .split(":")).toList();
        Map<String, String> map = new HashMap<String, String>();
        map.put(stream.getFirst(), stream.get(1));
        return map;
    }
}

