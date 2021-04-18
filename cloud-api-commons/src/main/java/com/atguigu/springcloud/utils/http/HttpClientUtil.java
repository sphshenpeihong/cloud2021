package com.atguigu.springcloud.utils.http;

import cn.hutool.json.JSON;
import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 封装HttpClient繁琐的操作
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/16
 * @since V1.0.0.2
 */
@Slf4j
public class HttpClientUtil {

    // POST请求，入参JSONString，返参CommonResult
    public static CommonResult test() {

        return null;
    }

    /**
     * POST请求工具方法
     *
     * @param url        请求URL
     * @param jsonString 入参（json字符串）
     * @return 返参（json字符串）
     */
    public static String executePost(String url, String jsonString) {
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
