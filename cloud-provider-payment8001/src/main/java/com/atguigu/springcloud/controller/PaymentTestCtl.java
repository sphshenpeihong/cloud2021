package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Response;
import com.atguigu.springcloud.pojo.AcceptVO;
import com.atguigu.springcloud.pojo.ParamVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shen Peihong
 * @version 1.0
 * @since 1.0.0.1
 */
@RestController
@RequestMapping("/paymenttest")
public class PaymentTestCtl {

    @PostMapping("/test1")
    ResponseEntity<Response> test1() {
        if (true) {
            throw new NullPointerException();
        }
        return Response.ok();
    }

    @RequestMapping("/varConvert")
    public ResponseEntity<Response> varConvert(@RequestBody AcceptVO vo) {
        return null;
    }


}
