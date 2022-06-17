package com.anserlt.common.util.websocket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHelper.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}

