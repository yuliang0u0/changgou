package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yuliang0u0
 * @create 2021-03-23 16:43
 */
@SpringBootApplication
@EnableEurekaClient // 开启eureka客户端
@MapperScan("com.changgou.goods.dao") // 开启通用Mapper的包扫描
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

}
