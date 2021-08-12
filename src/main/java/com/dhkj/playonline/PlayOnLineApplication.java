package com.dhkj.playonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dhkj.playonline.dao")
public class PlayOnLineApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayOnLineApplication.class, args);
    }

}
