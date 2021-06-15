package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description:
 *
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
public enum RespEnum {
    OK(0, "操作成功"),
    ERROR(-1, "系统出现异常，请稍后再试！");


    private Integer code;
    private String desc;


}
