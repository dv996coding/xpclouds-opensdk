package io.github.dv996coding.util;

import java.util.Base64;

/**
 * base64 加密工具类
 * @author 98419
 * @create 2022-08-16 7:48
 */
public class Base64Util {
    /**
     * 图片数据转换成Base64的数据
     *
     * @param imageData 图片源数据
     * @return base64[]
     */
    public static byte[] convert(byte[] imageData) {
        return Base64.getEncoder().encode(imageData);
    }

    /**
     * 图片数据转换成Base64的数据
     * @param imageData 图片源数据
     * @return base64 String
     */
    public static String convert2String(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
