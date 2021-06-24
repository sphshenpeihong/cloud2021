package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.utils.http.HttpClientUtil;
import com.atguigu.springcloud.utils.http.OkHttpUtil;
import com.google.common.collect.Maps;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description: 消费模块远程调用提供者模块
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/order")
public class OrderCtl {

    // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/cloud-payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/create")
    public CommonResult create(HttpServletRequest request, Payment payment){
        // 使用的postForObject方法，发起请求时，使用的请求头类型是application/json的
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult create(@PathVariable(name = "id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    // 利用HttpClient向payment模块发起POST请求
    @GetMapping("/httpClient")
    public CommonResult httpClient(){

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8001/payment/getPaymentById/32");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * HttpClient Post请求
     *
     * @return
     */
    @GetMapping("httpClient2")
    public CommonResult httpClient2() {

        String url = "http://localhost:8001/cloud-payment/payment/create";
        Payment payment = new Payment();
        payment.setSerial("好样1");
        String jsonString = JSON.toJSONString(payment);
        String respJsonString = HttpClientUtil.executePost(url, jsonString);
        CommonResult commonResult = JSON.parseObject(respJsonString, CommonResult.class);
        return commonResult;
    }

    /**
     * HttpClient Get请求
     *
     * @return
     */
    @GetMapping("httpClient3")
    public CommonResult httpClient3() {
        String url = "http://localhost:8001/cloud-payment-service/payment/discovery1";
        HashMap<String, String> paramMap = Maps.newHashMap();
        String username = "123";
        String password = "&";
        paramMap.put("username", username);
        paramMap.put("password", password);
        String respJsonString = HttpClientUtil.executeGet(url, paramMap);
        CommonResult commonResult = JSON.parseObject(respJsonString, CommonResult.class);
        return commonResult;
    }

    /**
     * OkHttp Get请求
     *
     * @return
     */
    @GetMapping("okHttp1")
    public CommonResult okHttp1() {
        String url = "http://localhost:8001/cloud-payment-service/payment/discovery1";
        HashMap<String, String> paramMap = Maps.newHashMap();
        String username = "123";
        String password = "&";
        paramMap.put("username", username);
        paramMap.put("password", password);
        String resp = OkHttpUtil.builder()
                                .url(url)
                                .addParams(paramMap)
                                .get()
                                .sync();
        CommonResult commonResult = JSON.parseObject(resp, CommonResult.class);
        return commonResult;
    }

    /**
     * OkHttp Post请求
     *
     * @return
     */
    @GetMapping("okHttp2")
    public CommonResult okHttp2() throws IllegalAccessException {
        String url = "http://localhost:8001/cloud-payment-service/payment/create";
        Payment payment = new Payment();
        payment.setSerial("好样1");
        String jsonString = JSON.toJSONString(payment);
        HashMap<String, String> paramMap = Maps.newHashMap();
        paramMap.put("serial", "好样1");
        String resp = OkHttpUtil.builder()
                                .url(url)
                                .addParams(payment)
                                .post(true)
                                .sync();
        CommonResult commonResult = JSON.parseObject(resp, CommonResult.class);
        return commonResult;
    }


}
