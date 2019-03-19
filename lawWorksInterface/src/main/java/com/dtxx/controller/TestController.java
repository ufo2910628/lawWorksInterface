package com.dtxx.controller;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutSysDataBase;
import com.dtxx.model.dto.Return1Info;
import com.dtxx.model.dto.ReturnInfoJson; 
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.IncomingService;
import com.dtxx.service.LawOutSysDataBaseService;
import com.dtxx.util.ApplicationContextUtil;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/LawInterceptor/test")
public class TestController {
//	@Autowired
//	private LawOutSysDataBaseService lawOutSysDataBaseService;
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();

	/**
	 * lawIncoming写入接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/insert")
	@ResponseBody
	public void insert(LawOutSysDataBase LawOutSysDataBase,String operation,HttpServletRequest request){
		LawOutSysDataBaseService lawOutSysDataBaseService=ApplicationContextUtil.getContext().getBean(LawOutSysDataBaseService.class);
		LawOutSysDataBase lawOutSysDataBaseses=LawOutSysDataBase;
		LawOutSysDataBase.setSyscode("BID_SYS");
		LawOutSysDataBase lawOutSysDataBasese = lawOutSysDataBaseService.selectById(LawOutSysDataBase);
		if (lawOutSysDataBasese == null) {
			LawOutSysDataBase.setSyscode("BID_SYS");
			LawOutSysDataBase.setStatus("1");
			lawOutSysDataBaseService.insert(LawOutSysDataBase);
			// lawOutSysDataBasese =lawOutSysDataBaseService.selectById(lawOutSysDataBase);
		} else {
			if (operation.equals("update")) {
				LawOutSysDataBase =lawOutSysDataBaseService.selectById(LawOutSysDataBase);
				LawOutSysDataBase.setName(lawOutSysDataBaseses.getName());
				LawOutSysDataBase.setParent(lawOutSysDataBaseses.getParent());
				lawOutSysDataBaseService.update(LawOutSysDataBase);
			}
		}
	}
	
	

}
