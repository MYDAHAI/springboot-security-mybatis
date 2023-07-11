package com.yr.springbootsecuritymybatiscrud;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi //启用swagger
@EnableKnife4j //启用离线，导出文档   访问 doc.html
public class SpringbootSecurityMybatisCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityMybatisCrudApplication.class, args);
    }

}
