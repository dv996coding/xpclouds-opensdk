package io.github.dv996coding.vo;

import com.alibaba.fastjson.JSON;

/**
 * 打印订单类 私有参数
 * @author 984199774@qq.com
 */
public class PrintOrderRequest extends RestRequest {

    public PrintOrderRequest(String sn,String printOrderOssUrl) {
        this.sn = sn;
        this.copies=1;
        this.mode=1;
        this.content=printOrderOssUrl;
    }

    /**
     * 打印机编号
     */
    private String sn;
    /**
     * 打印内容,不能超过4096字节，这里为图片url地址
     */
    private String content;
    /**
     * 打印份数，默认为1
     */
    private Integer copies=1;
    /**
     * 纸张宽度
     */
    private Integer paperWidth;
    /**
     * 订单幂等性，时效性默认为5分钟
     */
    private String idempotent;
    /**
     * 是否切刀
     */
    private Boolean cutter = false;
    /**
     * 是否透传，
     * true 透传方式不经过OSS，同时打印内容必须为打印机可识别的ZPL和TSPL指令。
     * false 为了兼容现有对接的开发者不受影响，走OSS，此时打印内容可以是Url链接也可以是XML方式
     */
    private Boolean direct = false;

    public void setDirect(Boolean direct) {
        this.direct = direct;
    }

    public Boolean getDirect() {
        return direct;
    }

    public void setIdempotent(String idempotent) {
        this.idempotent = idempotent;
    }

    public String getIdempotent() {
        return idempotent;
    }

    /**
     * 打印模式：
     * 值为0或不指定则会检查打印机是否在线，如果不在线则不生成打印订单，直接返回设备不在线状态码；如果在线则生成打印订单，并返回打印订单号。
     * 值为1不检查打印机是否在线，直接生成打印订单，并返回打印订单号。如果打印机不在线，订单将缓存在打印队列中，打印机正常在线时会自动打印。
     */
    private Integer mode;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setPaperWidth(Integer paperWidth) {
        this.paperWidth = paperWidth;
    }

    public Integer getPaperWidth() {
        return paperWidth;
    }

    public void setCutter(Boolean cutter) {
        this.cutter = cutter;
    }

    public Boolean getCutter() {
        return cutter;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
