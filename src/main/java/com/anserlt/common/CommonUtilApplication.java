package com.anserlt.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Anserlt
 */
@SpringBootApplication
public class CommonUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonUtilApplication.class, args);
    }

    //添加websocket支持
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
