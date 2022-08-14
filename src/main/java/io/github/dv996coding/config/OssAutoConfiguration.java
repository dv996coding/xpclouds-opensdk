package io.github.dv996coding.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import io.github.dv996coding.properties.OssProperties;
import io.github.dv996coding.service.AliyunCloudStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 984199774@qq.com
 */
@Configuration
@ConditionalOnClass({OSSClient.class})
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {
    private OSSClient ossClient;
    private OssProperties properties;

    public OssAutoConfiguration(OssProperties properties) {
        this.ossClient = new OSSClient(properties.getEndpoint(), new DefaultCredentialProvider(properties.getAccessKeyId(), properties.getAccessKeySecret()), null);
        this.properties = properties;
    }

    @Bean
    public AliyunCloudStorageService storageService() {
        return new AliyunCloudStorageService(properties, ossClient);
    }
}