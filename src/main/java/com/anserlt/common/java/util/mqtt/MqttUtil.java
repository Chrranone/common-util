package com.anserlt.common.java.util.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Anserlt
 */
@Slf4j
public class MqttUtil {
    private MqttClient client;

    public boolean connect(String brokerAddress, String clientId, String username, String password){
        boolean connectStatus = false;
        if (client == null || !client.isConnected()) {
            if (client != null && !client.isConnected()) {
                try {
                    client.close();
                } catch (MqttException e) {

                }
                client = null;
            }

            MemoryPersistence persistence = new MemoryPersistence();

            try {
                client = new MqttClient(brokerAddress, clientId, persistence);

                // MQTT 连接选项
                MqttConnectOptions connOpts = new MqttConnectOptions();
                if (username != null && password != null) {
                    connOpts.setUserName(username);
                    connOpts.setPassword(password.toCharArray());
                }
                connOpts.setCleanSession(true);

                // 设置回调，建立连接
                client.setCallback(new MqttCallbackUtil());
                client.connect(connOpts);

                connectStatus = true;
            } catch (MqttException me) {
                log.error("reason " + me.getReasonCode() + "\n" + "msg " + me.getMessage() + "\n" + me.getLocalizedMessage() + "\n" + me.getCause());
                me.printStackTrace();
            }
        }

        return connectStatus;
    }

    public boolean publish(String pubTopic, Integer qos, String content){
        try {
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(pubTopic, message);
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean subscribe(String subTopic, Integer qos) {
        try {
            client.subscribe(subTopic, qos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close(){
        boolean closeStatus = false;
        if (client != null && client.isConnected()) {
            try {
                client.disconnect();
                client.close();
                client = null;
                closeStatus = true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return closeStatus;
    }

}
