package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    // 由于该接口需要给客户端调用，客户端的请求头Content-Type：application/json，故这里不将json类型转java类型的话，是接收不到参数的。
    // 但这里有一个缺点就是，加了@RequestBody之后，以后凡是调这个接口的请求头格式一定需要是json了，不然会报400
    @PostMapping(value = "/create")
    public CommonResult create(HttpServletRequest request, @RequestBody Payment payment){
        int row = paymentService.create(payment);
        if (row > 0){
            return new CommonResult(200, "插入成功", payment.getId());
        }else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(HttpServletRequest request,@PathVariable(name = "id") Long id){
        String header = request.getHeader("Content-Type");
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200, "查询成功", payment);
        }else {
            return new CommonResult(444, "查询失败", null);
        }
    }



}
