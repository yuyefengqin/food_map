package com.gok.food_map.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordEncryptionUtil {
    // 安全参数配置
    private static final int ITERATIONS = 100000;  // 迭代次数
    private static final int KEY_LENGTH = 256;     // 密钥长度
    private static final int SALT_LENGTH = 16;     // 盐值长度
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    // 生成随机盐值
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // 加密密码 PBKDF2算法
    public static String encryptPassword(String password, String salt) {
        try {
            // 创建密钥规范
            KeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    Base64.getDecoder().decode(salt),
                    ITERATIONS,
                    KEY_LENGTH
            );

            // 获取密钥工厂并生成密钥
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // 返回Base64编码的哈希值
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException("密码加密失败", e);
        }
    }

    // 验证密码
    public static boolean verifyPassword(
            String inputPassword,
            String storedHash,
            String storedSalt
    ) {
        // 计算输入密码的哈希值
        String inputHash = encryptPassword(inputPassword, storedSalt);

        // 安全地比较两个哈希值
        return slowEquals(inputHash, storedHash);
    }

    // 比较方法 使用固定时间比较算法
    private static boolean slowEquals(String a, String b) {
        byte[] aBytes = a.getBytes();
        byte[] bBytes = b.getBytes();

        int diff = aBytes.length ^ bBytes.length;
        for (int i = 0; i < aBytes.length && i < bBytes.length; i++) {
            diff |= aBytes[i] ^ bBytes[i];
        }
        return diff == 0;
    }

}
