package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Shen Peihong on 2021/4/14
 * Description: 返回给前端的业务VO类
 *
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;
    private String desc;
    private Object data;

    public Response(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
        this.data = null;
    }

    public static ResponseEntity<Response> ok() {
        return ResponseEntity.ok(new Response(RespEnum.OK.getCode(), RespEnum.OK.getDesc()));
    }

    public static ResponseEntity<Response> ok(Object data) {
        return ResponseEntity.ok(new Response(RespEnum.OK.getCode(), RespEnum.OK.getDesc(), data));
    }

    public static ResponseEntity<Response> error() {
        return ResponseEntity.ok(new Response(RespEnum.ERROR.getCode(), RespEnum.ERROR.getDesc()));
    }

    public static ResponseEntity<Response> error(Response response) {
        return ResponseEntity.ok(response);
    }

}
