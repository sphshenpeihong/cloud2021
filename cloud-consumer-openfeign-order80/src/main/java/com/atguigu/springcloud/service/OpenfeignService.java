package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/19
 */
@FeignClient(value = "cloud-payment-service")
public interface OpenfeignService {

    @GetMapping(value = "/cloud-payment/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id);

    @GetMapping("/cloud-payment/payment/discovery")
    public Object discovery();

}
