package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description:
 *
 * @since 1.0.0
 */
// 可以选择直接在启动类或配置类去主动扫描DAO，也可以直接配@Mapper注解声明
@Mapper
public interface PaymentDao {

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
    public Payment getPaymentById(@Param("id") Long id);


}
