package io.github.dv996coding.zebra.impl;


import io.github.dv996coding.zebra.IZebraComand;

/**
 * ^FO 命令用于设置字段起始位置，相当于标签起始 (^LH) 位置而言。
 * 通过沿 x 轴和 y 轴定义点 （与旋转角度无关）， ^FO 可设置字段区的左上角。
 * <p>
 * ^FOx,y,z
 * @author 984199774@qq.com
 */
public class FOCmdImpl implements IZebraComand {
    public FOCmdImpl(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getCommand() {
        return String.format("^FO%d,%d", x, y);
    }

    /**
     * 沿 x 轴定义点 （与旋转角度无关）
     */
    private Integer x;
    /**
     * 沿 y 轴定义点 （与旋转角度无关）
     */
    private Integer y;

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getX() {
        return x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getY() {
        return y;
    }
}
