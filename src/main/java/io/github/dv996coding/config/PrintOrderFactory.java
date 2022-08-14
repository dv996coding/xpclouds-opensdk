package io.github.dv996coding.config;

import io.github.dv996coding.service.PrintService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author 984199774@qq.com
 */
public class PrintOrderFactory implements FactoryBean<PrintService>, InitializingBean {
    private PrintService printService;
    private String user;
    private String userKey;

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    private Boolean debug;

    public void setUser(String user) {
        this.user = user;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public PrintService getObject() throws Exception {
        return this.printService;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return PrintService.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.user, "'user' must be not null");
        Assert.notNull(this.userKey, "'userKey' must be not null");
        printService = new PrintService(this.user, this.userKey, this.debug);
    }
}
