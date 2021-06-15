package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Response;
import com.atguigu.springcloud.pojo.vo.AcceptVO;
import com.atguigu.springcloud.pojo.vo.ParamVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shen Peihong
 * @version 1.0
 * @since 1.0.0.1
 */
@FeignClient(value = "cloud-payment-service", path = "cloud-payment-service", contextId = "testClient")
public interface TestClient {
    @PostMapping("/paymenttest/test1")
    ResponseEntity<Response> test1();

    @RequestMapping("/paymenttest/varConvert")
    ResponseEntity<Response> varConvert(@RequestBody AcceptVO vo);

}
