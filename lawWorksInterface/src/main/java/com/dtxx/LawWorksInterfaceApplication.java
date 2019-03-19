package com.dtxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dtxx.listener.StartMqListener;

@SpringBootApplication
@MapperScan("com.dtxx.mapper")
public class LawWorksInterfaceApplication {

	public static void main(String[] args) {
//		SpringApplication.run(LawWorksInterfaceApplication.class, args);
//		SpringApplication
		//项目启动调用，添加监听器
		   SpringApplication springApplication = new SpringApplication(LawWorksInterfaceApplication.class);
        springApplication.addListeners(new StartMqListener());
        springApplication.run(args);

	}

}

