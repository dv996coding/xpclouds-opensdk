package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

/**
 * ^XA 命令用于 ZPL II 代码的开头。它是开括号，表示开始新的标签格式。可用
 * 单个 ASCII 控制字符 STX （control-B、十六进制 02）替换此命令。
 * <p>
 * 有效 ZPL II 格式要求标签格式应以 ^XA 命令开始并以 ^XZ 命令结束。
 * @author 984199774@qq.com
 */
public class XACmdImpl implements IZebraComand {
    @Override
    public String getCommand() {
        return "^XA\n";
    }
}
