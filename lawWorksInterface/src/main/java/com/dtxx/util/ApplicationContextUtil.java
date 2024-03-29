package com.dtxx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	public final static Object getBean(String beanName){
		return context.getBean(beanName);
	}
 
	public final static Object getBean(String beanName, Class<?> requiredType) {
		return context.getBean(beanName, requiredType);
	}
}
