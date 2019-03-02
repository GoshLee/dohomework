package com.helper.homeworkhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.helper"})
@SpringBootApplication
public class HomeworkHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkHelperApplication.class, args);
    }
}
