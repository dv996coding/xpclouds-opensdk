package io.github.dv996coding.util;

import io.github.dv996coding.contants.ZplContants;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Zpl指令类
 *
 * @author 98419
 * &#064;create  2022-08-16 7:53
 */
public class ZplCommandUtil {
//    private static final Logger LOG = LoggerFactory.getLogger(ZplCommandUtil.class);

    public String getImage2ZplByZlib(BufferedImage resizedImage) {
        // 对图片进行处理
        DataBufferByte dataBufferByte = (DataBufferByte) resizedImage.getRaster().getDataBuffer();
        byte[] encodedBytesImage = Base64.getMimeEncoder().encode(ZlibUtil.compress(dataBufferByte.getData()));
        int rowBytes = getWidthBytes(resizedImage.getWidth());
        String crcString = ImageUtil.getCRCHexString(encodedBytesImage);
        int bytes = rowBytes * resizedImage.getHeight();

        return String.format(ZplContants.GFA_ZPL, bytes, bytes, rowBytes,
                new String(encodedBytesImage, StandardCharsets.US_ASCII), crcString, StringUtils.LF);
    }

    public String getImage2Zpl(BufferedImage resizedImage) {
        // 对图片进行处理
        DataBufferByte dataBufferByte = (DataBufferByte) resizedImage.getRaster().getDataBuffer();
        String result = StringUtils.EMPTY;
        byte[] compress = ZlibUtil.compressByOutputStream(dataBufferByte.getData());
        if (compress != null && compress.length > 0) {
            byte[] encodedBytesImage = Base64.getMimeEncoder().encode(compress);
            int rowBytes = getWidthBytes(resizedImage.getWidth());
            String crcString = ImageUtil.getCRCHexString(encodedBytesImage);
            int bytes = rowBytes * resizedImage.getHeight();

            result = String.format(ZplContants.GFA_ZPL, bytes, bytes, rowBytes,
                    new String(encodedBytesImage, StandardCharsets.US_ASCII), crcString, StringUtils.LF);
        }
        return result;
    }

    public String getImageToAsciiByZpl(BufferedImage image) {
        String ascii = getImageToAscii(image);
        int rowBytes = getWidthBytes(image.getWidth());
        String crcString = ImageUtil.getCRCHexString(ascii.getBytes());
        int bytes = rowBytes * image.getHeight();
        return String.format(ZplContants.GFA_ZPL, bytes, bytes, rowBytes, ascii, crcString, StringUtils.LF);
    }

    /**
     * 获取图片的打印内容
     *
     * @param image 图片
     * @return 打印内容
     */
    public String getImageToAscii(BufferedImage image) {
        StringBuilder sb = new StringBuilder();
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        int height = image.getHeight();
        int width = image.getWidth();
        int rgb, red, green, blue, index = 0;
        char[] auxBinaryChar = {'0', '0', '0', '0', '0', '0', '0', '0'};
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                rgb = image.getRGB(w, h);
                red = (rgb >> 16) & 0x000000FF;
                green = (rgb >> 8) & 0x000000FF;
                blue = (rgb) & 0x000000FF;
                char auxChar = '1';
                int totalColor = red + green + blue;
                if (totalColor > 768 * 0.5) {
                    auxChar = '0';
                }
                auxBinaryChar[index] = auxChar;
                index++;
                if (index == 8 || w == (width - 1)) {
                    sb.append(fourByteBinary(new String(auxBinaryChar)));
                    auxBinaryChar = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
                    index = 0;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String fourByteBinary(String binaryStr) {
        int decimal = Integer.parseInt(binaryStr, 2);
        if (decimal > 15) {
            return Integer.toString(decimal, 16).toUpperCase();
        } else {
            return "0" + Integer.toString(decimal, 16).toUpperCase();
        }
    }

    /**
     * @param width ширина картинки в px
     * @return размер пикселей в байтах
     */
    public int getWidthBytes(int width) {
        return width % 8 > 0 ? width / 8 + 1 : width / 8;
    }
}
