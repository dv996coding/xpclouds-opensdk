package io.github.dv996coding.vo;

/**
 * 清空打印队列
 *
 * @author star
 */
public class ClearPrintOrderRequest extends PrinterVoRequest {
    public ClearPrintOrderRequest(String sn) {
        super(sn);
    }

    /**
     * 需要取消打印的订单编号，多个订单号采用英文逗号分隔“,”，每次最多支持10个订单号，超出10个订单，请分批调用提交
     */
    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
