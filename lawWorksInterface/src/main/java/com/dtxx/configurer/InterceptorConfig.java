package com.dtxx.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dtxx.interceptors.LawInterceptor;
import com.dtxx.service.LawSysSecretKeyService;
//拦截器配置
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	//注入系统参数配置文件
	@Autowired
	private SystemYmlConfig systemYmlConfig;
	@Autowired
	private LawSysSecretKeyService lawSysSecretKeyService;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/test为前缀的 url路径
        registry.addInterceptor(new LawInterceptor(systemYmlConfig,lawSysSecretKeyService)).addPathPatterns("/LawInterceptor/**");
    }
}
