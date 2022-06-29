package org.pg7.scsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class ScspApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScspApplication.class, args);
    }


}
