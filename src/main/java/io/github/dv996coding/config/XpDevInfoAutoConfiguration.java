package io.github.dv996coding.config;

import io.github.dv996coding.properties.DevelopInfoProperties;
import io.github.dv996coding.service.PrintService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 984199774@qq.com
 */
@Configuration
@ConditionalOnClass({PrintService.class})
@EnableConfigurationProperties(DevelopInfoProperties.class)
public class XpDevInfoAutoConfiguration {

}
