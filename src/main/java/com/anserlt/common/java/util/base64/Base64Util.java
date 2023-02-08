package com.anserlt.common.java.util.base64;

import java.util.Base64;

/**
 * @author Anserlt
 */
public class Base64Util {

    /**
     * base64转为byte[]
     * @param base64 base64字符串
     * @return byte[]，可以写入文件等应用
     */
    public byte[] base64ToByteArray(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return bytes;
    }

    /**
     * byte[]转为Base64
     * @param bytes 文件或其他类型转化的base[]
     * @return base64字符串
     */
    public String byteArrayToBase64(byte[] bytes) {
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }

}
