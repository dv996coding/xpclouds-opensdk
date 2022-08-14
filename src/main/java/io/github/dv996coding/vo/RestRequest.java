package io.github.dv996coding.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

/**
 * 请求公共参数
 * 所有请求都必须传递的参数。
 * @author 984199774@qq.com
 */
public class RestRequest implements Serializable {
    /**
     * 芯烨云电子面单打印机平台用户名
     */
    private String user;
    /**
     * 芯烨云电子面单打印机开发者密钥
     */
    @JSONField(serialize = false)
    private String userKey;

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * 当前UNIX时间戳，10位，精确到秒
     */
    private String timestamp;

    /**
     * 当前UNIX时间戳，10位，精确到秒
     *
     * @return 当前UNIX时间戳，10位，精确到秒
     */
    public String getTimestamp() {
        timestamp = System.currentTimeMillis() / 1000 + "";
        return timestamp;
    }

    /**
     * 对参数 user + UserKEY + timestamp 拼接后（+号表示连接符）进行SHA1加密得到签名，值为40位小写字符串，其中UserKEY为用户开发者密钥
     * sign参数说明：例如：user=acc、UserKEY=abc、timestamp=acbc，那么先拼成字符串accabcacbc，再将此字符串进行SHA1加密，得到sign。
     */
    private String sign;

    /**
     * 获取计算后的签名
     *
     * @return 签名
     */
    public String getSign() {
        String signSrc = user + userKey + getTimestamp();
        sign = DigestUtils.sha1Hex(signSrc);
        return sign;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
