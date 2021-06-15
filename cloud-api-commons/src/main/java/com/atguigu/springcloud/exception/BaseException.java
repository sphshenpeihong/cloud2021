package com.atguigu.springcloud.exception;

import lombok.Data;

/**
 * 自定义异常处理类
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/5/10
 */
@Data
public class BaseException extends Exception {
    private Integer errorCode;
    private String errorMessage;

    public BaseException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

