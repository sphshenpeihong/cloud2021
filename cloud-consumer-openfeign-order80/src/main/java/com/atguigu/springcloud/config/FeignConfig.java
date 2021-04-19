package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/19
 */
@Configuration
public class FeignConfig {

    /**
     * openFeign进行服务间的调用时，可以指定日志级别(看看自己是否需要在服务间调用时需要打详细的日志)
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
