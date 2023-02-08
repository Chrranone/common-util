package com.anserlt.common.java.knowledge.proxyPattern.staticProxy;

/**
 * 客户端使用操作与分析
 *
 * 代理模式最主要的就是有一个公共接口（Person），一个具体的类（Student），一个代理类（StudentsProxy），
 * 代理类持有具体类的实例，代为执行具体类实例方法
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        //被代理的学生张三
        Person zhangsan = new Student("zhangsan");

        //生成代理对象，并将张三传给代理对象
        Person studentProxy = new StudentsProxy(zhangsan);

        //班长代理上交班费
        studentProxy.giveMoney();
    }
}
