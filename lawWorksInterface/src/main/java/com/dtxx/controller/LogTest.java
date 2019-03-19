package com.dtxx.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtxx.util.LogUtils;

@RestController
@RequestMapping("LogTest")
public class LogTest {
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();
	@RequestMapping("/test")
    public String LogTest() throws Exception{
		
    	//userService.queryUser();
		ExceptionLogger.error("getExceptionLogger===日志测试");
    	BussinessLogger.info("getBussinessLogger===日志测试");
    	DBLogger.info("getDBLogger===日志测试");
        return "helloworld";
    }

}
