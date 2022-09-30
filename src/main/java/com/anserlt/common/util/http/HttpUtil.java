package com.anserlt.common.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anserlt
 */
public class HttpUtil {

    /**
     * 构造httpclient实例
     */
    public HttpClient createHttpClient(){
        return HttpClientBuilder.create().build();
    }

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
     * 发送请求的时候，设置header属性，并解析返回的json对象
     */
    public void sendRequestWithSetHeadersAndParseResponse() {
        HttpClient httpClient = createHttpClient();

        String url = "";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Authorization", "123");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            String bodyString = null;
            try {
                bodyString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!ObjectUtils.isEmpty(bodyString)) {
                JSONObject bodyJson = JSON.parseObject(bodyString);
                // bodyJson 获取需要的数据，如message、data
            }
        }
    }

    /**
     * 发送HTTP请求，并返回HTTP响应；设置请求参数、设置请求body，设置请求类型暂时忽略。
     * @param host ip地址
     * @param port 端口
     * @param url 请求的路径
     * @return 响应
     */
    public HttpResponse sendHttpGetRequestByHttpClient(String host, Integer port, String url) {
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

    /**
     * 构造需要用户名、密码Digest认证的HTTP请求客户端
     * @param host ip地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return RestTemplate客户端
     */
    public RestTemplate createDigestRestTemplate(String host, Integer port, String username, String password){
        HttpClient httpClient = createDigestHttpClient(host, port, username, password);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        //支持中文编码
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }

    /**
     *
     * @param host ip
     * @param port 端口
     * @param url 请求地址
     * @return ResponseEntity<T>
     */
    public ResponseEntity<String> sendHttpGetRequestByRestTemplate(String host, Integer port, String url) {
        RestTemplate restTemplate = createDigestRestTemplate(host, port, "admin", "hik12345+");

        Map<String, String> params = new HashMap<>();
        params.put("host", host);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);

        return responseEntity;
    }

    /**
     *
     * @param host ip
     * @param port 端口
     * @param url 请求地址
     * @return ResponseEntity<T>
     */
    public ResponseEntity<String> sendHttpPostRequestByRestTemplate(String host, Integer port, String url) {
        RestTemplate restTemplate = createDigestRestTemplate(host, port, "admin", "hik12345+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host", host);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, jsonObject, String.class);

        return responseEntity;
    }

}
