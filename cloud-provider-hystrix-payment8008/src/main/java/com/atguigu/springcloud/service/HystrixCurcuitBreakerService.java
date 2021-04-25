package com.atguigu.springcloud.service;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/25
 */
public interface HystrixCurcuitBreakerService {

    // 测试服务熔断的方法
    String curcuitBreakerOK(Integer id);

}
