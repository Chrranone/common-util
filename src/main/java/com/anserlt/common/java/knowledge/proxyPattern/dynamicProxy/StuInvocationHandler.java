package com.anserlt.common.java.knowledge.proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 创建StuInvocationHandler类，实现InvocationHandler接口，
 * 这个类中持有一个被代理对象的实例target。InvocationHandler中有一个invoke方法，所有执行代理对象的方法都会被替换成执行invoke方法。
 * 在invoke方法中执行被代理对象target的相应方法。
 * 在代理过程中，我们在真正执行被代理对象的方法前加入自己其他处理。这也是Spring中的AOP实现的主要原理，
 * 这里还涉及到一个很重要的关于java反射方面的基础知识。
 */
public class StuInvocationHandler<T> implements InvocationHandler {

    //invocationHandler持有的被代理对象
    T target;

    public StuInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * proxy:代表动态代理对象，真实代理对象com.sun.proxy.$Proxy0
     * method：我们所要调用某个对象真实的方法的Method对象
     * args：代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" +method.getName() + "方法");
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
