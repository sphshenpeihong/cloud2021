package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/29
 */
@RestController
@RequestMapping("/repayment")
public class RePaymentCtl {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    // 由于该接口需要给客户端调用，客户端的请求头Content-Type：application/json，故这里不将json类型转java类型的话，是接收不到参数的。
    // 但这里有一个缺点就是，加了@RequestBody之后，以后凡是调这个接口的请求头格式一定需要是json了，不然会报400
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        int row = paymentService.create(payment);
        if (row > 0){
            return new CommonResult(200, "插入成功，当前服务的端口serverPort：" + serverPort, payment.getId());
        }else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @RequestMapping(value = "/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable(name = "id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200, "查询成功，当前服务的端口serverPort：" + serverPort, payment);
        }else {
            return new CommonResult(444, "查询失败", null);
        }
    }

    // discoveryClient对象存储了注册中心中所注册的服务清单详细信息，获取后提供接口对外暴露
    @GetMapping("/discovery")
    public Object discovery() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        // 获取注册中心中所注册的服务名称列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("服务名称：" + service);
            /*
                服务名称：cloud-order-service
                服务名称：cloud-payment-service
             */
        }

        // 获取指定某个服务的详细信息
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "，" + instance.getScheme() + "，" + instance.getHost() + "，"
                                       + instance.getPort() + "，" + instance.getUri());
            /*
                CLOUD-PAYMENT-SERVICE，null，192.168.43.247，8002，http://192.168.43.247:8002
                CLOUD-PAYMENT-SERVICE，null，192.168.43.247，8001，http://192.168.43.247:8001
             */
        }
        return this.discoveryClient;
    }


}
