package com.anserlt.common.java.util.mqtt;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author Anserlt
 */
@Slf4j
public class MqttCallbackUtil implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        log.error("连接断开");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // 接收到消息
        log.info("接收消息主题:" + topic);
        log.info("接收消息Qos:" + message.getQos());
        log.info("接收消息内容:" + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("消息发送状态：" + token.isComplete());
    }
}