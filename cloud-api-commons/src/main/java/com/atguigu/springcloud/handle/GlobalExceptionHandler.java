package com.atguigu.springcloud.handle;


import com.atguigu.springcloud.entities.Response;
import com.atguigu.springcloud.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/5/10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获BaseException异常
     *
     * @param request 请求对象
     * @param e       捕获异常对象
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Response> baseExceptionHandler(HttpServletRequest request, BaseException e) {
        printException(e);

        return Response.error(new Response(e.getErrorCode(), e.getErrorMessage()));
    }

    /**
     * 捕获未知异常
     *
     * @param e 捕获异常对象
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception e) {
        // 临时打印全部
        e.printStackTrace();
        printException(e);
        return Response.error();
    }

    private void printException(Exception e) {
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append("异常信息--").append(e.getMessage());
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        errorInfo.append("--报错类--").append(stackTraceElement.getClassName());
        errorInfo.append("--报错方法--").append(stackTraceElement.getMethodName());
        errorInfo.append("--报错行数--").append(stackTraceElement.getLineNumber());
        log.error(errorInfo.toString());
    }

}
