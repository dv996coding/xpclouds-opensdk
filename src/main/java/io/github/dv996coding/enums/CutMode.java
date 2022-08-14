package io.github.dv996coding.enums;

/**
 * 打印机支持的打印模式
 *
 * @author JavaLyl
 */

public enum CutMode {
    T("T", "撕扯"),
    P("P", "剥离"),
    R("R", "回卷"),
    A("A", "贴标机"),
    C("C", "切纸器"),
    D("D", "延迟切纸器"),
    F("F", "RFID"),
    L("L", "预留"),
    U("U", "预留"),
    K("K", "自助终端模式");

    String value;
    String desc;

    CutMode(String value, String desc) {
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
