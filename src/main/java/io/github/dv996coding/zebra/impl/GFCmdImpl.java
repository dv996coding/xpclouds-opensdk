package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.util.ZPLConveter;
import io.github.dv996coding.zebra.IZebraComand;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ^GF 命令用于直接将图形字段数据下载到打印机的位图存储区。此命令遵循适
 * 用于任何其他字段的约定 （包括字段方向）。可以将图形字段数据放在位图空间内的
 * 任何位置。
 * <p>
 * ^GFa,b,c,d,data
 * @author 984199774@qq.com
 */
public class GFCmdImpl implements IZebraComand {

    public GFCmdImpl(BufferedImage orderImge) {
        this.bufferedImage = orderImge;
    }

    private BufferedImage bufferedImage;



    @Override
    public String getCommand() {
        ZPLConveter zp = new ZPLConveter();
        zp.setCompressHex(true);
        zp.setBlacknessLimitPercentage(50);
        try {
            return zp.convertfromImg(this.bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
