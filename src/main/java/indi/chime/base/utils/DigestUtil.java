package indi.chime.base.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * 签名工具
 */
public class DigestUtil {
    // MD5签名工具实体
    private static final MessageDigest MD5_DIGEST = getMessageDigest("MD5");

    // 获取MD5签名字符串
    public static String getMD5String(String sourceString) {
        return getMD5String(Objects.requireNonNull(sourceString.getBytes(StandardCharsets.UTF_8)));
    }

    // 获取MD5签名字符串
    public static String getMD5String(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] digest = MD5_DIGEST.digest(bytes);
        for (byte b : digest) {
            int v = b & 0XFF;
            if(v < 16) {
                stringBuilder.append(0);
            }
            stringBuilder.append(Integer.toHexString(v));
        }
        return stringBuilder.toString();
    }

    // 获取指定类型签名工具
    public static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(Objects.requireNonNull(algorithm));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
