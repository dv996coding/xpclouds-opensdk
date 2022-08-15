package io.github.dv996coding.util;

import io.github.dv996coding.properties.DevelopInfoProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

/**
 * 数据断言
 *
 * @author JavaLyl
 */
public final class AssertUtil {
    /**
     * 开发者信息断言，必须指定开发者信息
     *
     * @param properties 开发者信息
     * @return true 断言成功
     */
    public static Boolean isDevelopInNoneEmpty(DevelopInfoProperties properties) {
        Assert.isTrue(StringUtils.isNotBlank(properties.getUser()), "Invalid developer ID (xp.dev.user)");
        Assert.isTrue(StringUtils.isNotBlank(properties.getUserKey()), "Invalid developer key (xp.dev.userKey)");
        Assert.isTrue(StringUtils.isNoneEmpty(properties.getDomain()), "The domain name address of the open platform interface is invalid (xp.dev.domain)");
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        Assert.isTrue(pattern.matcher(properties.getDomain()).matches(), "The interface request address is invalid (xp.dev.domain)");
        return true;
    }
}
