package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Response;
import com.atguigu.springcloud.pojo.vo.AcceptVO;
import com.atguigu.springcloud.pojo.vo.ParamVO;
import com.atguigu.springcloud.service.TestClient;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Shen Peihong
 * @version 1.0
 * @since 1.0.0.1
 */
@RestController
@RequestMapping("/ordertest")
public class TestCtl {

    @Resource
    private TestClient testClient;

    @GetMapping(value = "/test1")
    public CommonResult test1() {
        ResponseEntity<Response> entity = testClient.test1();
        if (entity.getStatusCode().is2xxSuccessful()) {

        } else {

        }
        return null;
    }

    // 利用HttpClient向payment模块发起POST请求
    @GetMapping("/httpClient1")
    public CommonResult httpClient1() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:9527/cloud-payment/payment/getPaymentById/32");

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
        } finally {
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


    @RequestMapping("/varConvert")
    public CommonResult varConvert(@RequestBody ParamVO vo) {
        AcceptVO acceptVO = new AcceptVO();
        BeanUtils.copyProperties(vo, acceptVO);
        testClient.varConvert(acceptVO);
        return null;
    }

}
