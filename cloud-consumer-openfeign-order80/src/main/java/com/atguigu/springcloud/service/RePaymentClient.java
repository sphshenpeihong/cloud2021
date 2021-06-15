package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.pojo.vo.ParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/29
 */
@FeignClient(name = "cloud-payment-service", path = "/cloud-payment-service/repayment", contextId = "rePaymentClient")
public interface RePaymentClient {

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment);

    @RequestMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id);

    @GetMapping("/discovery")
    public Object discovery() throws InterruptedException;

    @GetMapping("/discovery1")
    public Object discovery1(@SpringQueryMap ParamVO paramVO) throws InterruptedException;

    /**
     * 测试Get请求传参，额外多加参数
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/discovery3")
    public Object discovery3(@SpringQueryMap ParamVO paramVO,
                             @RequestParam("eventId") String eventId) throws InterruptedException;

}
