package io.github.dv996coding.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author 984199774@qq.com
 */
@ConfigurationProperties(prefix = "xp.dev")
@RefreshScope
public class DevelopInfoProperties {
    /**
     * 开发者ID，登录芯烨云条码打印机开放平台获取
     */
    private String user;
    /**
     * 开发者密钥，登录芯烨云条码打印机开放平台获取
     */
    private String userKey;
    /**
     * 是否打印调试信息，默认false不打印
     */
    private Boolean debug=false;
    /**
     * 芯烨云条码打印机开放平台接口地址，具体参见开放平台文档说明
     */
    private String domain;

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
