//package io.github.dv996coding.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.Base64Utils;
//
//import java.awt.image.BufferedImage;
//import java.io.UnsupportedEncodingException;
//
///**
// * Zpl指令类
// * @author 98419
// * @create 2022-08-16 7:53
// */
//public class ZplCommandUtil {
//    private static final Logger log = LoggerFactory.getLogger(ZplCommandUtil.class);
//
//    public static byte[] bitmap(BufferedImage image) {
////        PrintBitmapUtil.BitmapRes res = PrintBitmapUtil.toBmpData(bitmap);
//        byte[] datas = res.getData();
//        log.info("compressBase64", "图片的字节数据长度:\t" + datas.length);
//        byte[] compressData = ZlibUtil.compress(datas);
//        log.info("compressBase64", "图片压缩后字节数据长度:\t" + compressData.length);
//        byte[] base64Data = Base64Util.convert(compressData);
//        log.info("compressBase64", "图片Base64的字节数据长度:\t" + base64Data.length);
//        String sf = "^GFA,%d,%d,%d,:Z64:%s\n";
//        int b = datas.length;
//        int c = datas.length;
//        int d = (int) Math.ceil(res.getWidth() / 8d);
//        String str = String.format(sf, b, c, d, Base64Util.convert2String(compressData));
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] llBymm(int a) {
//        String str = String.format("^LL%d\n", a * ConvertUtil.getRatio());
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] pwBymm(int a) {
//        String str = String.format("^PW%d\n", a * ConvertUtil.getRatio());
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//
//    public static byte[] pm(boolean b) {
//        String str = b ? "^PMY\n" : "^PMN\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] fo(int a, int b) {
//        String str = String.format("^FO%d,%d\n", a, b);
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] md(int a) {
//        String str = String.format("^MD%d\n", a);
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] sd(int a) {
//        String str = String.format("~SD%d\n", a);
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] pq(int q) {
//        String str = String.format("^PQ%d,0,0,Y\n", q);
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] xz() {
//        String str = "^XZ\r\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] xa() {
//        String str = "^XA\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] sizeBymm(double m, double n) {
//        String str = "\nSIZE " + m + " mm," + n + " mm\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//    public static byte[] gapBymm(double m, double n) {
//        String str = "GAP " + m + " mm," + n + " mm\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//
//    public static byte[] blineBymm(double m, double n) {
//        String str = "BLINE " + m + " mm," + n + " mm\n";
//        byte[] data = strTobytes(str);
//        return data;
//    }
//
//
//    public static byte[] strTobytes(String str) {
//        log.info("Zpl command: {}", str);
//        String charsetName = "gbk";
//        byte[] data = null;
//        try {
//            byte[] b = str.getBytes("UTF-8");
//            data = (new String(b, "UTF-8")).getBytes(charsetName);
//        } catch (UnsupportedEncodingException ex) {
//            log.info("Zpl command encoding exception: {}", ex);
//        }
//
//        return data;
//    }
//}
