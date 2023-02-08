package com.anserlt.common.java.knowledge.proxyPattern.staticProxy;

/**
 * 代理类实现接口，完成委托类预处理消息、过滤消息、把消息转发给委托类，
 * 以及事后处理消息等。
 */
public class StudentsProxy implements Person{

    //被代理的学生
    Student stu;

    public StudentsProxy(Person stu) {
        // 只代理学生对象
        if(stu.getClass() == Student.class) {
            this.stu = (Student)stu;
        }
    }

    @Override
    public void giveMoney() {
        stu.giveMoney();
    }
}
