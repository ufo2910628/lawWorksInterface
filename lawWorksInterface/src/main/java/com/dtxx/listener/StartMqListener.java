package com.dtxx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.dtxx.mq.Thread.GetDeptDataThread;
import com.dtxx.mq.Thread.GetOrgDataThread;
import com.dtxx.mq.Thread.GetSupplierInfoThread;
import com.dtxx.mq.Thread.GetUserDataThread;

/**
 * spring boot 容器加载完成后执行,加载招标系统MQ信息
 * @author anpeng
 * @date 2019/3/12 下午12:04
*/
public class StartMqListener implements ApplicationListener<ContextRefreshedEvent> {
	// 部门
	private GetDeptDataThread deptThread;
	// 机构
	private GetOrgDataThread orgThread;
	// 供应商
	private GetSupplierInfoThread supplierThread;
	// 用户
	private GetUserDataThread userThread;



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		String str = null;
		if (str == null && deptThread == null) {
			deptThread = new GetDeptDataThread();
			deptThread.start();
		}
		if (str == null && orgThread == null) {
			orgThread = new GetOrgDataThread();
			orgThread.start();
		}
		if (str == null && supplierThread == null) {
			supplierThread = new GetSupplierInfoThread();
			supplierThread.start();
		}
		if (str == null && userThread == null) {
			userThread = new GetUserDataThread();
			userThread.start();
		}
	}
}
