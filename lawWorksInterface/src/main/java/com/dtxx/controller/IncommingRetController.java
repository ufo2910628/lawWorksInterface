package com.dtxx.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.model.dto.ReturnInfoJson;
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.OutcomingRetService;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;

@RestController
@RequestMapping("/LawInterceptor/IncomingRet")
public class IncommingRetController {
	@Autowired
    private IncomingRetService incomingRetService;
	
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();
	
	/**
	 * lawIncomingRet写入接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/insert")
	@ResponseBody
	public ReturnInfoJson insert(LawIncomingRet lawIncomingRet,HttpServletRequest request){
		//替换加密参数为解密后的数据
		lawIncomingRet.setErrorMsg(request.getAttribute("errorMsg")+"");
		try {
		    lawIncomingRet.setCreationDate(new Date());
			incomingRetService.insert(lawIncomingRet); 
			return new ReturnInfoJson(0);
		}catch (Exception e) {
			// TODO: handle exception
			DBLogger.error(String.format("OutcomingController|insert|error|%s|%s|%s", lawIncomingRet.getInId(),lawIncomingRet.getErrorMsg(),lawIncomingRet.getIntfCode()));
 
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST,value = "/select/{id}/{sourceSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("id") int id,@PathVariable("sourceSysCode") String sourceSysCode,HttpServletRequest request){
		try { 
				LawIncomingRet lawIncomingRet=incomingRetService.selectById(new LawIncomingRetKey(id,sourceSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawIncomingRet)));
 
		}catch (Exception e) {
			DBLogger.error(String.format("IncomingController|selectRet|error|%s|%s", id,sourceSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}


	@RequestMapping(method = RequestMethod.POST,value = "/select/{sourceSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("sourceSysCode") String sourceSysCode,HttpServletRequest request){
		try {
			    List<LawIncomingRet> lawIncomingRet=incomingRetService.selectReading(new LawIncomingRetKey(sourceSysCode)); 
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawIncomingRet)));

		}catch (Exception e) {
			DBLogger.error(String.format("IncomingController|selectRet|error|%s|%s", sourceSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}


	
	
	
	
	
	
	
	
	/**
	 * lawIncomingRet更新状态
	 */
//	@RequestMapping(method = RequestMethod.POST,value = "/update")
//	public ReturnInfoJson update(LawIncomingRet lawIncomingRet,HttpServletRequest request){
//		lawIncomingRet.setErrorMsg(request.getAttribute("jsonDecode")+""); 
//		try { 
//			incomingRetService.updateByPrimaryKeySelective(lawIncomingRet); 
//			 
//			return new ReturnInfoJson(0);
//		}catch (Exception e) {
//			// TODO: handle exception
//			DBLogger.error(String.format("IncomingController|insert|error|%s|%s|%s", lawIncomingRet.getInId(),lawIncomingRet.getIntfCode(),lawIncomingRet.getErrorMsg())); 
//			return new ReturnInfoJson(99999);
//		}
//	}
	
}
