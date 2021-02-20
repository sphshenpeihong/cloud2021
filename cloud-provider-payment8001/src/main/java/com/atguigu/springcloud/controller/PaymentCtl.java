package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description:
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/payment")
public class PaymentCtl {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/create")
    public CommonResult create(Payment payment){
        int row = paymentService.create(payment);
        if (row > 0){
            return new CommonResult(200, "插入成功", payment.getId());
        }else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200, "查询成功", payment);
        }else {
            return new CommonResult(444, "查询失败", null);
        }
    }



}
