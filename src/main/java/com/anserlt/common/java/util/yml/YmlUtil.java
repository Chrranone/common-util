package com.anserlt.common.java.util.yml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Anserlt
 */
@Component
public class YmlUtil {

    /**
     * 从yml值获取自定义配置，并自动注入到对应的属性中
     *
     * 对于非静态的属性，只需要添加@Value注释，以及@Component
     *
     * 若想要使用当前自动注入的值，则当前类需要使用 @Resource 或者 @Autowire 引入
     */
    @Value("${some.value}")
    private String someValue;

    /**
     * 从yml值获取自定义配置，并自动注入到对应的属性中
     *
     * 对于静态属性，需要实现对应属性的set方法，需要@Value和@Component
     *
     * 若想要使用当前自动注入的值，则当前类需要使用 @Resource 或者 @Autowire 引入 ？？？ // fixme 这个地方不是很确定
     */
    private static String anotherValue;
    @Value("${some.value}")
    public void setAnotherValue(String anotherValue) {
        YmlUtil.anotherValue = anotherValue;
    }

}
