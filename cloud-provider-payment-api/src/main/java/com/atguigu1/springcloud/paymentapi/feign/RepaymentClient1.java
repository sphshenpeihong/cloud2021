package com.atguigu1.springcloud.paymentapi.feign;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/29
 */
@FeignClient(name = "cloud-payment-service", path = "/cloud-payment/repayment")
public interface RepaymentClient1 {

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment);

}
