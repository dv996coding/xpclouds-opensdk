package io.github.dv996coding.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 984199774@qq.com
 */
public final class ToolsUtil {
    private static Pattern ZEROS = Pattern.compile("0+$"), ONES = Pattern.compile("1+$"), MULTI_W = Pattern.compile("([0-9A-Z])\\1{2,}");
    /**
     * 压缩图片数据
     *
     * @param data 图片指令数据
     * @return 压缩后的数据
     */
    public static String compress(String[] data) {
        StringBuffer sb = new StringBuffer();
        String pre = null;
        for (String d : data) {
            String a = d;
            Matcher m = ZEROS.matcher(a);
            if (m.find()) {
                a = m.replaceFirst(",");
            }

            m = ONES.matcher(a);
            if (m.find()) {
                a = m.replaceFirst("!");
            }

            a = minimizeSameWord(a);

            if (pre != null && a.equals(pre)) {
                a = ":";
            } else {
                pre = a;
            }
            sb.append(a);
            //sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * 字节数组转为十六进制
     *
     * @param b
     * @param rowSize
     * @return
     */
    public static String[] byte2HexStr(byte[] b, int rowSize) {
        int len = b.length / rowSize;
        String[] arr = new String[len];
        for (int n = 0; n < len; n++) {
            StringBuffer hs = new StringBuffer();
            for (int j = 0; j < rowSize; j++) {
                String stmp = Integer.toHexString(b[n * rowSize + j] & 0XFF);
                if (stmp.length() == 1) {
                    hs.append("0");
                }
                hs.append(stmp);
            }
            arr[n] = hs.toString().toUpperCase();
        }
        return arr;
    }
    /**
     * 十六进制串中相同字母压缩
     *
     * @param str
     * @return
     */
    public static String minimizeSameWord(String str) {
        Matcher matcher = MULTI_W.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            int len = group.length();
            String c = "";
            if (len > 20) {
                c = Character.toString((char) ('f' + len / 20));
            }
            if (len % 20 > 0) {
                c = c + (char) ('F' + len % 20);
            }

            str = str.replaceFirst(group, c + group.charAt(0));
        }
        return str;
    }
}
