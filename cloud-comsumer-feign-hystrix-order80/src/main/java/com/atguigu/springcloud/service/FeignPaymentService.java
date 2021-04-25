package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
// fallback书写承包feign接口fallback的方法的类的反射
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX", fallback = PaymentFallBackService.class)
public interface FeignPaymentService {

    @GetMapping("/hystrix/ok")
    public String ok(@RequestParam(name = "id") String id);

    @GetMapping("/hystrix/timeOut")
    public String timeOut(@RequestParam(name = "id") String id);

}
