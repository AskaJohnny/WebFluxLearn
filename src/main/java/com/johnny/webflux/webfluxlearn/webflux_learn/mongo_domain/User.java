package com.johnny.webflux.webfluxlearn.webflux_learn.mongo_domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * User mongo实体
 *
 * @author johnny
 * @create 2020-02-29 下午9:26
 **/
@Data
@Document(collection = "user") //对应mongodb的表名
public class User {

    /**
     * 定义ID mongodb中id 一般都是字符串类型
     */
    @Id
    private String id;

    @NotBlank
    private String name;

    private int age;
}

