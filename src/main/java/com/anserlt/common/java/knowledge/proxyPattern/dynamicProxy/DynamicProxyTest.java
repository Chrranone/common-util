package com.anserlt.common.java.knowledge.proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 客户端使用操作与分析
 *
 * 代理的是接口(Interfaces)，不是类(Class)，也不是抽象类
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("zhangsan");

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler<Person>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        /*
            参数：
                loader: 用哪个类加载器对生成的代理类进行加载
                interfaces:一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，如果我们提供了这样一个接口对象数组，那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
                h:表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，并最终由其调用。

            newProxyInstance源码中的重点内容：
                final Class<?>[] intfs = interfaces.clone();
                Class<?> cl = getProxyClass0(loader, intfs);  这里产生了代理类，代理类的class文件放在内存中
                final Constructor<?> cons = cl.getConstructor(constructorParams);  产生了构造器
                return cons.newInstance(new Object[]{h});   产生了实例

            产生的代理类：
                public final class $Proxy0 extends Proxy implements Person {
                    ...
                    private static Method m3;
                    ...
                }
                这个类继承了Proxy，所以也就决定了java动态代理只能对接口进行代理，Java的继承机制注定了这些动态代理类们无法实现对class的动态代理
                这个类文件放在内存中的，我们在创建代理对象时，就是通过反射获得这个类的构造方法，然后创建的代理实例
                代理类持有invocationhandler对象，invocationhandler对象持有被代理对象。可以把InvocationHandler看做一个中介类，中介类持有一个被代理对象，在invoke方法中调用了被代理对象的相应方法。通过聚合方式持有被代理对象的引用，把外部对invoke的调用最终都转为对被代理对象的调用。

                代理类的构造方法：
                    public $Proxy0(InvocationHandler paramInvocationHandler)
                        throws
                      {
                        super(paramInvocationHandler);
                      }

                代理类执行操作的方法：
                    public final void giveMoney() {
                    ...;
                    这里调用代理对象的giveMoney方法，直接就调用了InvocationHandler中的invoke方法，并把m3传了进去，m3是通过反射获取到的givemoney方法
                    this.h.invoke(this, m3, null);
                    ...;
                    代理类调用自己的givemoney方法时，通过自身持有的中介类对象来调用中介类对象的invoke方法，从而达到代理执行被代理对象的方法。也就是说，动态代理通过中介类实现了具体的代理功能。
                }
         */
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney();
    }
}
