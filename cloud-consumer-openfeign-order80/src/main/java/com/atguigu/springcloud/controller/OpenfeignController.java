package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.pojo.vo.ParamVO;
import com.atguigu.springcloud.service.OpenfeignService;
import com.atguigu.springcloud.service.RePaymentClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.*;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/19
 */
@RestController
@RequestMapping("/openfeign")
public class OpenfeignController {

    @Resource
    private OpenfeignService openfeignService;

    @Resource
    private RePaymentClient rePaymentClient;


    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        return rePaymentClient.create(payment);
    }

    @RequestMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(HttpServletRequest request, @PathVariable(name = "id") Long id){
        return rePaymentClient.getPaymentById(id);
    }

    @GetMapping("/discovery")
    public Object discovery() throws InterruptedException {
        return rePaymentClient.discovery();
    }

    @GetMapping("/feign")
    public CommonResult feign() {
        CommonResult commonResult = openfeignService.getPaymentById(32L);
        return commonResult;
    }

    @GetMapping("/discovery1")
    public Object discovery1(){
        return openfeignService.discovery();
    }

    /**
     * 测试Get请求传参
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/discovery2")
    public Object discovery2() throws InterruptedException {
        ParamVO paramVO = new ParamVO();
        paramVO.setId(1);
        paramVO.setUsername("123");
        return rePaymentClient.discovery1(paramVO);
    }

    /**
     * 测试Get请求传参，额外多加参数
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/discovery3")
    public Object discovery3() throws InterruptedException {
        ParamVO paramVO = new ParamVO();
        paramVO.setId(1);
        paramVO.setUsername("123");
        return rePaymentClient.discovery3(paramVO, "123123");
    }
}
