package com.example.tongji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tongji.mapper")
public class TongjiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TongjiApplication.class, args);
	}
}
