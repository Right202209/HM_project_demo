package org.qqhru.hmpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 每一个springboot工程都有一个启动类 相当于我们曾经启动tomcat
 * 启动类必须在根包下
 * 1.类头上必须有注解
 * 2.必须有main函数
 */
@SpringBootApplication  //表明身份 此注解也是springboot的核心注解
@MapperScan("org.qqhru.hmpt.mapper")
public class HmptApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmptApplication.class, args);
    }

}
