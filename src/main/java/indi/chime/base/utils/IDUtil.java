package indi.chime.base.utils;

import java.util.Random;
import java.util.UUID;

/**
 * ID生成工具
 */
public class IDUtil {
    // 获取UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 获取指定长度随机数字
    public static String getRandom(int length) {
        final Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
