package com.atguigu.springcloud.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Shen Peihong
 * @version 1.0
 * @since 1.0.0.1
 */
@Data
public class ParamVO {

    private Integer id;

    @JsonProperty("user_name")
    private String username;

}
