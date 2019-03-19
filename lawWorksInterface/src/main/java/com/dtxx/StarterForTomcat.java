package com.dtxx;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.dtxx.listener.StartMqListener;

public class StarterForTomcat extends SpringBootServletInitializer {
	// 覆盖父类方法,将入口指向springboot的main
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 参数Builder就是容器创建整个spring容器的起始对象，也就是main方法的类
		builder.application().addListeners(new StartMqListener());
		return builder.sources(LawWorksInterfaceApplication.class);
	}
}