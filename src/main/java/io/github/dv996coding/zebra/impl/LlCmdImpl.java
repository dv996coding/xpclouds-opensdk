package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

public class LlCmdImpl implements IZebraComand {
    public LlCmdImpl(Integer length) {
        this.length = length;
    }

    private Integer length;

    public LlCmdImpl setLength(Integer length) {
        this.length = length;
        return this;
    }

    /**
     * 打印机可识别的打印指令
     *
     * @return 打印指令
     */
    @Override
    public String getCommand() {
        return "^LL" + this.length + "\n";
    }
}
