package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description: 前后端通用的返回对象
 *
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    // 有时候创建对象时，data值为null，则可以用这个构造方法
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
