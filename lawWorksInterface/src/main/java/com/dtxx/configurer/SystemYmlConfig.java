package com.dtxx.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载yaml配置文件的方法
 * Created by sun on 2017-1-15.
 * spring-boot更新到1.5.2版本后locations属性无法使用
 * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 * 故现在把数据放到application.yml文件中,spring-boot启动时会加载
 */
@Component
@ConfigurationProperties(prefix = "system")
public class SystemYmlConfig {
	//系统接口发送后超时时间（单位秒）
	private Integer sendDate;

	public Integer getSendDate() {
		return sendDate;
	}

	public void setSendDate(Integer sendDate) {
		this.sendDate = sendDate;
	}


	
}
