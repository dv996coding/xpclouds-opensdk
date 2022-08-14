package io.github.dv996coding.config;

import io.github.dv996coding.service.PrintService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author 984199774@qq.com
 */
@Configuration
@ConditionalOnClass({PrintService.class})
@EnableConfigurationProperties(DevelopInfoProperties.class)
public class XpDevInfoAutoConfiguration {
    private final DevelopInfoProperties properties;

    public XpDevInfoAutoConfiguration(final DevelopInfoProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public PrintOrderFactory printOrderFactory() {
        final PrintOrderFactory factory = new PrintOrderFactory();
        factory.setUser(properties.getUser());
        factory.setUserKey(properties.getUserKey());
        factory.setDebug(properties.isDebug());
        return factory;
    }
}
