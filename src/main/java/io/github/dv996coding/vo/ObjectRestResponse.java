package io.github.dv996coding.vo;

import com.alibaba.fastjson.JSON;

/**
 * 返回公共参数
 *
 * @author 984199774@qq.com
 * @param <T> 泛型参数
 */
public class ObjectRestResponse<T> {
    /**
     * 返回码，正确返回0，【注意：结果正确与否的判断请用此返回参数】，错误返回非零。
     */
    private Integer code;
    /**
     * 结果提示信息，正确返回”ok”，如果有错误，返回错误信息标识。
     */
    private String msg;
    /**
     * 数据类型和内容详看私有返回参数data，如果有错误，返回null。
     */
    private T data;
    /**
     * 服务器程序执行时间，单位：毫秒。
     */
    private Integer serverExecutedTime;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getServerExecutedTime() {
        return serverExecutedTime;
    }

    public void setServerExecutedTime(Integer serverExecutedTime) {
        this.serverExecutedTime = serverExecutedTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
