package io.github.dv996coding.vo;

import com.alibaba.fastjson.JSON;

/**
 * 订单状态类 私有参数
 * @author 984199774@qq.com
 */
public class PrintOrderStatusRequest extends RestRequest{
    public PrintOrderStatusRequest(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单编号，由“打印订单”接口返回
     */
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
