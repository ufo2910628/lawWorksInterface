package com.dtxx.controller;

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
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.model.LawOutcomingRetKey;
import com.dtxx.model.dto.ReturnInfoJson;
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.OutcomingRetService;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;
@RestController
@RequestMapping("/LawInterceptor/OutcomingRet")
public class OutcomingRetController {

	@Autowired
    private OutcomingRetService outcomingRetService;
	
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();
	
	/**
	 * lawOutcomingRet写入接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/insert")
	@ResponseBody
	public ReturnInfoJson insert(LawOutcomingRet lawOutcomingRet,HttpServletRequest request){
		//替换加密参数为解密后的数据
		lawOutcomingRet.setErrorMsg(request.getAttribute("errorMsg")+"");
		try {
			outcomingRetService.insert(lawOutcomingRet);
//			return  ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(0)));
			return new ReturnInfoJson(0);
		}catch (Exception e) {
			// TODO: handle exception
			DBLogger.error(String.format("OutcomingController|insert|error|%s|%s|%s", lawOutcomingRet.getOutId(),lawOutcomingRet.getErrorMsg(),lawOutcomingRet.getIntfCode()));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
	/**
	 * LawOutcoming读取接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/select/{targetSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("targetSysCode") String targetSysCode,HttpServletRequest request){
		try { 
			   List<LawOutcomingRet> lawOutcomingRet=outcomingRetService.selectReading(new LawOutcomingRetKey(targetSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawOutcomingRet))); 
		}catch (Exception e) {
			DBLogger.error(String.format("OutcomingController|selectRet|error|%s|%s",targetSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	/**
	 * LawOutcoming读取接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/select/{id}/{targetSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("id") Short id,@PathVariable("targetSysCode") String targetSysCode,HttpServletRequest request){
		try { 
				LawOutcomingRet lawOutcomingRet=outcomingRetService.selectById(new LawOutcomingRetKey(id,targetSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawOutcomingRet)));
 
			 
		}catch (Exception e) {
			DBLogger.error(String.format("OutcomingController|selectRet|error|%s|%s",id,targetSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
//	/**
//	 * lawOutcomingRet更新状态
//	 */
//	@RequestMapping(method = RequestMethod.POST,value = "/update")
//	public ReturnInfoJson update(LawOutcomingRet lawOutcomingRet,HttpServletRequest request){
//		lawOutcomingRet.setErrorMsg(request.getAttribute("jsonDecode")+""); 
//		try { 
//			outcomingRetService.updateByPrimaryKey(lawOutcomingRet); 
//			 
//			return new ReturnInfoJson(0);
//		}catch (Exception e) {
//			// TODO: handle exception
//			DBLogger.error(String.format("IncomingController|insert|error|%s|%s|%s", lawOutcomingRet.getOutId(),lawOutcomingRet.getErrorMsg(),lawOutcomingRet.getIntfCode())); 
//			return new ReturnInfoJson(99999);
//		}
//	}
	
//	/**
//	 * lawOutcomingRet读取数据
//	 */
//	@RequestMapping(method = RequestMethod.POST,value = "/select/{id}/{sourceSysCode}")
//	@ResponseBody
//	public ReturnInfoJson selectRet(@PathVariable("id")int id, @PathVariable("sourceSysCode") String sourceSysCode,HttpServletRequest request){
//		try {
//			LawIncomingRet lawIncomingRet=incomingRetService.selectById(new LawIncomingRetKey(id, sourceSysCode));
//			return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawIncomingRet)));
////			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(0)));
//		}catch (Exception e) {
//			DBLogger.error(String.format("IncomingController|selectRet|error|%s|%s", id,sourceSysCode));
////			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
//			return new ReturnInfoJson(99999);
//		}
//	}
	
	
	 
}
