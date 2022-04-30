package com.anserlt.common.util.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用websocket，需要在spring的启动类中，添加websocket支持，如下：
 *          @Bean
 *          public ServerEndpointExporter serverEndpointExporter(){
 *              return new ServerEndpointExporter();
 *          }
 *
 * @author Anserlt
 */
@Slf4j
@ServerEndpoint("/api")
@Component
public class WebSocketServer {
    private static List<Session> sessions = new ArrayList<>();

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
