package com.anserlt.common.java.knowledge.proxyPattern.staticProxy;

/**
 * 代理类与被代理类的公共接口
 *
 * 假如一个班的同学要向老师交班费，但是都是通过班长把自己的钱转交给老师。
 * 这里，班长代理学生上交班费，班长就是学生的代理
 */
public interface Person {
    void giveMoney();
}
