package com.lemon.api.runner.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.lemon.api.runner.api","com.lemon.api.runner.service.**","com.lemon.api.runner.shiro"})//组建扫描
@MapperScan(basePackages={"com.lemon.api.runner.dao"})
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
