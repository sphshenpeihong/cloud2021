package com.atguigu.springclou.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置网关有两种方式
 * 1、yml文件
 * 2、@Bean的形式：RouteLocator路由定位器
 * 总结：
 * 作用就像nginx一样，拦截一个请求，然后断言判断，如何的话，进行网络段转发
 *
 * @author Shen Peihong
 * @version: 1.0
 * @date 2021/4/27
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        // 断言只要匹配上（一般/前缀/**），那么就会去转发到指望的网段（请求后缀仍然不变，拼接到转发网段之后）
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("cloud-eureka", r -> r.path("/").or().path("/eureka/**")
                    .uri("http://localhost:7001"));
        return routes.build();
    }

}
