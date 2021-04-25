package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.HystrixCurcuitBreakerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/25
 */
@Service("hystrixCurcuitBreakerService")
public class HystrixCurcuitBreakerServiceImpl implements HystrixCurcuitBreakerService {

    //this.circuitBreakerEnabled = getProperty(propertyPrefix, key, "circuitBreaker.enabled", builder.getCircuitBreakerEnabled(), default_circuitBreakerEnabled);
    //this.circuitBreakerRequestVolumeThreshold = getProperty(propertyPrefix, key, "circuitBreaker.requestVolumeThreshold", builder.getCircuitBreakerRequestVolumeThreshold(), default_circuitBreakerRequestVolumeThreshold);
    //this.circuitBreakerSleepWindowInMilliseconds = getProperty(propertyPrefix, key, "circuitBreaker.sleepWindowInMilliseconds", builder.getCircuitBreakerSleepWindowInMilliseconds(), default_circuitBreakerSleepWindowInMilliseconds);
    //this.circuitBreakerErrorThresholdPercentage = getProperty(propertyPrefix, key, "circuitBreaker.errorThresholdPercentage", builder.getCircuitBreakerErrorThresholdPercentage(), default_circuitBreakerErrorThresholdPercentage);
    @Override
    // 服务熔断，十次请求内，如果访问异常，先调用服务降级的方法，超过阈值的话，即使你不异常了，也同样会调用降级方法
    // 慢慢地就会恢复调用链路了
    // 服务降级 -> 服务熔断 -> 恢复调用链路
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 十次请求
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 阈值：百分之六十
    })
    public String curcuitBreakerOK(Integer id) {
        if (id <= 0){
            throw new RuntimeException("RuntimeException1111");
        }
        return "啦啦啦啦啦啦啦啦啦啦，" + id;
    }

    String fallbackMethod(Integer id){
        return "不太行" + id;
    }
}
