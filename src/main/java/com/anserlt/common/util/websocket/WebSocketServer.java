package com.anserlt.common.util.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.使用websocket，需要在spring的启动类中，添加websocket支持，如下：
 *          @Bean
 *          public ServerEndpointExporter serverEndpointExporter(){
 *              return new ServerEndpointExporter();
 *          }
 *
 *  2.当需要在websocket中注入service时，有时会出现service为null，无法注入的情况
 *          原因：项目启动时初始化，会初始化 websocket （非用户连接的），spring 同时会为其注入 service，
 *              该对象的 service 不是 null，被成功注入。
 *              但是，由于 spring 默认管理的是单例，所以只会注入一次 service。
 *              当客户端与服务器端进行连接时，服务器端又会创建一个新的 websocket 对象，
 *              这时问题出现了：spring 管理的都是单例，不会给第二个 websocket 对象注入 service，
 *              所以导致只要是用户连接创建的 websocket 对象，都不能再注入了。
 *          解决方法：
 *              1.通过ApplicationContext中的getBean方法来获取
 *              2.将service在类中写成static，通过给类的属性赋值的形式注入
 *
 * @author Anserlt
 */
@Slf4j
@ServerEndpoint("/api")
@Component
public class WebSocketServer {
    private static List<Session> sessions = new ArrayList<>();

    /**
     * 解决方法1
     */
    private Object someService = (Object) ApplicationHelper.getBean("someService");

    /**
     * 解决方法2
     */
    private static Object someService2;
    @Resource
    public void setSomeService2(Object someService2) {
        WebSocketServer.someService2 = someService2;
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        try {
            sendMessage("连接成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info(session.getId() + "：" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        onClose(session);
    }

    public void sendMessage(Object data) throws IOException {
        String message = JSONObject.toJSONString(data);
        sessions.forEach(session -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                onClose(session);
            }
        });
    }
}
