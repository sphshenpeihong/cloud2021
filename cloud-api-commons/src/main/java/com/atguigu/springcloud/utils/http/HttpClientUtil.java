package com.atguigu.springcloud.utils.http;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装HttpClient繁琐的操作
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/16
 * @since V1.0.0.2
 */
@Slf4j
@UtilityClass
public class HttpClientUtil {

    /**
     * Get请求
     *
     * @param url      请求URL
     * @param paramMap 入参
     * @return 返参json字符串
     */
    public String executeGet(@NonNull String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        URI uri = null;
        try {
            List<NameValuePair> params = new ArrayList<>();
            // 将参数放入键值对类NameValuePair中,再放入集合中
            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }

            uri = new URIBuilder(url)
                    .setParameters(params).build();
        } catch (URISyntaxException e) {
            log.error("HttpClient发送HTTP请求出错，e = [{}]", e.getMessage());
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        String respJsonString = null;
        try {

            // 获取配置信息
            RequestConfig requestConfig = getRequestConfig();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            respJsonString = EntityUtils.toString(responseEntity);
        } catch (ParseException | IOException e) {
            log.error("HttpClient发送HTTP请求出错，e = [{}]", e.getMessage());
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("释放资源出错，e = [{}]", e.getMessage());
            }
        }
        return respJsonString;
    }

    /**
     * 访问Http请求的配置信息
     *
     * @return 配置信息
     */
    private RequestConfig getRequestConfig() {
        // 配置信息
        return RequestConfig.custom()
                            // 设置连接超时时间(单位毫秒)
                            .setConnectTimeout(5000)
                            // 设置请求超时时间(单位毫秒)
                            .setConnectionRequestTimeout(5000)
                            // socket读写超时时间(单位毫秒)
                            .setSocketTimeout(5000)
                            // 设置是否允许重定向(默认为true)
                            .setRedirectsEnabled(true).build();
    }

    /**
     * POST请求工具方法
     *
     * @param url        请求URL
     * @param jsonString 入参（json字符串）
     * @return 返参（json字符串）
     */
    public String executePost(String url, String jsonString) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        HttpEntity responseEntity = null;
        String respJsonString = null;
        try {
            // 发送POST请求
            response = httpClient.execute(httpPost);

            // 获取状态code和结果数据
            StatusLine statusLine = response.getStatusLine();
            responseEntity = response.getEntity();

            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            respJsonString = EntityUtils.toString(responseEntity);
        } catch (ParseException | IOException e) {
            log.error("HttpClient发送HTTP请求出错，e = [{}]", e.getMessage());
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("释放资源出错，e = [{}]", e.getMessage());
            }
        }
        return respJsonString;
    }

}
