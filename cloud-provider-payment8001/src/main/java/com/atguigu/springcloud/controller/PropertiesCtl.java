package com.atguigu.springcloud.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/5/12
 */
@RequestMapping("/common")
@RestController
public class PropertiesCtl {

    @Value("${testUrl}")
    private String testUrl;

    @RequestMapping("/getProperties")
    public Map<String, Object> getProperties() throws IOException {
        Properties properties = new Properties();
        // 获取指向classes目录
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("common.properties");
        properties.load(is);//指向输入流地址
        // yml文件的话，有点坑，不是server.port，而是port
        String commonUrl = properties.getProperty("common-url");
        Map<String, Object> map = Maps.newHashMap();
        map.put("common-url", commonUrl);
        return map;

    }

    @RequestMapping("/getYmlConfig")
    public Map<String, Object> getYmlConfig() throws IOException {
        System.out.println(testUrl);
        Map<String, Object> map = Maps.newHashMap();
        map.put("testUrl", testUrl);
        return map;
    }


}
