package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动
 * @author 99200
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.example.demo.*"})
/**
 * 使用MapperScan批量扫描所有的Mapper接口；
 */
@MapperScan("com.example.demo.mapper")
@Slf4j
@Configuration
@ComponentScan
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        log.info("\n"
            + "███████╗██╗   ██╗ ██████╗ ██████╗███████╗███████╗███████╗\n"
            + "██╔════╝██║   ██║██╔════╝██╔════╝██╔════╝██╔════╝██╔════╝\n"
            + "███████╗██║   ██║██║     ██║     █████╗  ███████╗███████╗\n"
            + "╚════██║██║   ██║██║     ██║     ██╔══╝  ╚════██║╚════██║\n"
            + "███████║╚██████╔╝╚██████╗╚██████╗███████╗███████║███████║\n"
            + "╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝╚══════╝╚══════╝╚══════╝\n"
            + "                                                         \n");
    }

}
