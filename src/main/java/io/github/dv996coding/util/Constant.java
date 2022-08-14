package io.github.dv996coding.util;

/**
 * sdk常量
 *
 * @author 98419
 */
public interface Constant {
    /**
     * 设备编号最大长度
     */
    Integer SN_MAX_LENGTH = 15;
    /**
     * 最大重试次数
     */
    Integer MAX_RETRY_TIME = 3;
    /**
     * http 请求状态码
     */
    Integer STATUS = 300;
    /**
     * 请求响应成功状态码
     */
    Integer SUCCESS = 200;
    /**
     * 流控状态码
     */
    Integer LIMIT_REQUEST = 429;
    /**
     * 参数请求错误
     */
    Integer PARAM_ERROR=400;
}
