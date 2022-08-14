package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

/**
 * ^FS 命令用于表示字段定义已结束。或者，还可将 ^FS 命令作为单个 ASCII 控
 * 制码 SI （Control-O、十六进制 0F）使用。
 * @author 984199774@qq.com
 */
public class FSCmdImpl implements IZebraComand {
    @Override
    public String getCommand() {
        return "^FS";
    }
}
