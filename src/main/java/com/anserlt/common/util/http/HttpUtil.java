package com.anserlt.common.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author Anserlt
 */
public class HttpUtil {

    /**
     * 构造需要用户名、密码Digest认证的HTTP请求客户端
     * @param host ip地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return http客户端
     */
    public HttpClient createDigestHttpClient(String host, Integer port, String username, String password){
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(ObjectUtils.isEmpty(host) ? AuthScope.ANY_HOST : host, port == null ? AuthScope.ANY_PORT : port),
                new UsernamePasswordCredentials(username,password));
        return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    /**
     * 发送HTTP请求，并返回HTTP响应；设置请求参数、设置请求body，设置请求类型暂时忽略。
     * @param host ip地址
     * @param port 端口
     * @param url 请求的路径
     * @param requestType 请求类型
     * @return 响应
     */
    public HttpResponse sendHttpRequest(String host, Integer port, String url, String requestType) {
        String requestUrl = host + ":" + port + "/" + url;
        HttpClient httpClient = createDigestHttpClient(host, port, "admin", "hik12345+");

        HttpPost httpPost = new HttpPost(requestUrl);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
