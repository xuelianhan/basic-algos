package org.ict.util;

import java.nio.charset.StandardCharsets;

/**
 * @author sniper
 * @date 2022/4/27 3:19 PM
 */
public class UTF8EncodeTest {

    public static void main(String[] args) {
        String s = "中山大学";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        String result = bytesToHex(bytes);
        System.out.println(result);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
