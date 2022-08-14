package io.github.dv996coding.config;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author 984199774@qq.com
 */
@ConditionalOnClass({OSSClient.class})
@ConfigurationProperties(prefix = "oss")
@RefreshScope
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * Oss绑定的域名
     */
    private String bindDomain;
    /**
     * Oss路径前缀
     */
    private String prefix;

    public String getSubPrefix() {
        return subPrefix;
    }

    public void setSubPrefix(String subPrefix) {
        this.subPrefix = subPrefix;
    }

    /**
     * OSS 子路径前缀
     */
    private String subPrefix;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Oss BucketName
     */
    private String bucketName;


    public String getBindDomain() {
        return bindDomain;
    }

    public void setBindDomain(String bindDomain) {
        this.bindDomain = bindDomain;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
