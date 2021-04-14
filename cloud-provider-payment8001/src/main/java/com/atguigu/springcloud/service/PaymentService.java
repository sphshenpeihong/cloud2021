package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description:
 *
 * @since 1.0.0
 */
public interface PaymentService {

    /**
     * 插入语句 （简单测试）
     *
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 通过ID查询语句 （简单测试）
     *
     * @param id
     * @return
     */
    public Payment getPaymentById(Long id);

}
