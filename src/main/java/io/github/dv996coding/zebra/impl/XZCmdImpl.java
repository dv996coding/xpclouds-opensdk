package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

/**
 * ^XZ 命令为结束 （关闭）括号。它表示标签格式的结束。收到此命令后，即会 打印标签。
 * 此命令还可作为单个 ASCII 控制字符 ETX （Control-C、十六进制 03）发出。
 * @author 984199774@qq.com
 */
public class XZCmdImpl implements IZebraComand {
    @Override
    public String getCommand() {
        return "^XZ";
    }
}
