package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/25
 */
// 记得交给Spring容器管理
@Component
public class PaymentFallBackService implements FeignPaymentService {
    @Override
    public String ok(String id) {
        return "ok,承包feign接口的降级兜底方法，" + id;
    }

    @Override
    public String timeOut(String id) {
        return "timeOut,承包feign接口的降级兜底方法，" + id;
    }
}
