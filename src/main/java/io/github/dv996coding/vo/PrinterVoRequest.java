package io.github.dv996coding.vo;

import com.alibaba.fastjson.JSON;

/**
 * 打印机私有参数
 * @author 984199774@qq.com
 */
public class PrinterVoRequest extends RestRequest {
    public PrinterVoRequest(String sn) {
        this.sn = sn;
    }

    /**
     * 打印机编号
     */
    private String sn;
    /**
     * 打印机名称 自己定义
     */
    private String name;

    public String getSn() {
        return sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
