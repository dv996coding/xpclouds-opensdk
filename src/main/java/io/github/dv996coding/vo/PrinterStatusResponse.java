package io.github.dv996coding.vo;

import java.io.Serializable;

/**
 * 打印机状态 由打印机状态查询接口响应得到
 *
 * @author 984199774@qq.com
 */
public class PrinterStatusResponse implements Serializable {
    private Integer status;
    private String version;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
