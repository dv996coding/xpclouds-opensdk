package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

/**
 * 设定纸张宽度
 * 单位 dot
 * @author 98419
 */
public class PwCmdImpl implements IZebraComand {
    /**
     * 纸张宽度像素值 dot
     */
    private Integer paperWidth;

    public PwCmdImpl(Integer paperWidth) {
        this.paperWidth = paperWidth;
    }

    /**
     * 设置纸张宽度像素值
     *
     * @param paperWidth 纸张宽度
     * @return 纸张宽度对象
     */
    public PwCmdImpl setPaperWidth(Integer paperWidth) {
        this.paperWidth = paperWidth;
        return this;
    }

    /**
     * 打印机可识别的打印指令
     *
     * @return 打印指令
     */
    @Override
    public String getCommand() {
        return "^PW" + this.paperWidth + "\n";
    }
}
