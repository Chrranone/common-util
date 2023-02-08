package com.anserlt.common.java.knowledge.proxyPattern.dynamicProxy;

/**
 * 检测方法执行时间的工具类，在任何方法执行前先调用start方法，执行后调用finsh方法，
 * 就可以计算出该方法的运行时间，这也是一个最简单的方法执行时间检测工具。
 */
public class MonitorUtil {

    private static ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void start() {
        tl.set(System.currentTimeMillis());
    }

    //结束时打印耗时
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "方法耗时" + (finishTime - tl.get()) + "ms");
    }
}
