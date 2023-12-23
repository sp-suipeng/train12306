package com.sp.trainmemer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sp.trainmemer.mapper")
public class TrainMemerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainMemerApplication.class, args);
    }

}
