package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Shen Peihong on 2021/2/20
 * Description:
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

    /**
     * 提供默认方法
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult defaultResult(T data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(0);
        commonResult.setMessage("success");
        commonResult.setData(data);
        return commonResult;
    }

  // 有时候创建对象时，data值为null，则可以用这个构造方法
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
