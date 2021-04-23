package com.atguigu.springcloud.service;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/22
 */
public interface HystrixService {

    String ok(String id);

    String timeOut(String id);

}
