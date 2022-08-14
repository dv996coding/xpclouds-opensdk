package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.enums.CutMode;
import io.github.dv996coding.enums.PSelectMode;
import io.github.dv996coding.zebra.IZebraComand;

/**
 * 切刀指令
 *
 * @author JavaLyl
 */
public class MMCmdImpl implements IZebraComand {
    private CutMode cutMode;

    public void setCutMode(CutMode cutMode) {
        this.cutMode = cutMode;
    }

    public CutMode getCutMode() {
        return cutMode;
    }

    private PSelectMode selectMode;

    public void setSelectMode(PSelectMode selectMode) {
        this.selectMode = selectMode;
    }

    public PSelectMode getSelectMode() {
        return selectMode;
    }

    public MMCmdImpl(CutMode cutMode, PSelectMode selectMode) {
        this.cutMode = cutMode;
        this.selectMode = selectMode;
    }

    /**
     * 打印机可识别的打印指令
     *
     * @return 打印指令
     */
    @Override
    public String getCommand() {
        return String.format("^MM%s,%s", getCutMode().getValue(), getSelectMode().getValue());
    }
}
