package com.sp.trainmember;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sp.trainmember.mapper")
public class TrainMemerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainMemerApplication.class, args);
    }

}
