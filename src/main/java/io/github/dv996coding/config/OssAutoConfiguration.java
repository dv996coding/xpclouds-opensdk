package io.github.dv996coding.config;

import com.aliyun.oss.OSSClient;
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
    private final OssProperties ossProperties;
    public OssAutoConfiguration(final OssProperties ossProperties){
        this.ossProperties=ossProperties;
    }

    public OssProperties getOssProperties() {
        return ossProperties;
    }

    @Bean
    public OssClientFactory ossClientFactoryBean(){
        final OssClientFactory factory=new OssClientFactory();
        factory.setEndpoint(ossProperties.getEndpoint());
        factory.setAccessKeyId(ossProperties.getAccessKeyId());
        factory.setAccessKeySecret(ossProperties.getAccessKeySecret());
        return factory;
    }
}