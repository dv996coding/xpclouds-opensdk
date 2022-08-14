package io.github.dv996coding.enums;
/**
 * 剥离选择模式
 *
 * @author JavaLyl
 */
public enum PSelectMode {
    N("N", "否"),
    Y("Y", "是");
    String value;
    String desc;

    PSelectMode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}

