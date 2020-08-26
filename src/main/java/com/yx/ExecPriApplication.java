package com.yx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/21
 */
@SpringBootApplication
@EnableScheduling
public class ExecPriApplication {
    public static void main(String[] args){
      SpringApplication.run(ExecPriApplication.class, args);
    }
}
