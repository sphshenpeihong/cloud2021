package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
@Service
public class HystrixServiceImpl implements HystrixService {
    @Override
    public String ok(String id) {
        return "哈哈OK：id = " + id;
    }

    // 服务降级，当该方法执行的时间超过1.5秒，则直接调用降级的方法
    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1580")
    })
    @Override
    public String timeOut(String id) {
        long i = 1;
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "哈哈timeOut： id = " + id;
    }

    public String fallBack(String id){
        return "呜呜呜呜，不太行， id = " + id;
    }
}
