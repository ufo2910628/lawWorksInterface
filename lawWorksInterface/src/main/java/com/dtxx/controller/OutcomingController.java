package com.dtxx.controller;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.model.SymContractBase;
import com.dtxx.model.dto.Return1Info;
import com.dtxx.model.dto.ReturnInfoJson;
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.IncomingService;
import com.dtxx.service.OutcomingRetService;
import com.dtxx.service.OutcomingService;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/LawInterceptor/Outcoming")
public class OutcomingController {
	@Autowired
    private OutcomingService outcomingService;
	@Autowired
    private OutcomingRetService outcomingRetService;

	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();

	/**
	 * LawOutcoming写入接口。
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/insert")
	@ResponseBody
	public ReturnInfoJson insert(LawOutcoming lawOutcoming,HttpServletRequest request){
		//替换加密参数为解密后的数据
		lawOutcoming.setJson(request.getAttribute("jsonDecode")+""); 
		try { 
			Integer attachCount=lawOutcoming.getAttachCount();
            if (attachCount!=null && attachCount !=0) { //判断附件个数不为0或者不为空的情况下，把整个合同的文件夹附件转移
            	 String josn= lawOutcoming.getJson(); 
				 JSONObject json = JSONObject.fromObject(josn);   
		         ArrayList<SymContractBase> bases = new ArrayList<SymContractBase>();  
		         if (bases.get(0).getSymAttachments().size()==attachCount) {//判断附件个数字段与实际josn里面的附件个数是否一致。
		          String oldPath="/data/sftp/sftp_incoming_yc/"+lawOutcoming.getOutId();
				  String newPath="/data/intf/sftp_incoming_yc/"+lawOutcoming.getOutId();
				  IncomingController.copyFolderWithSelf(oldPath, newPath); 
				} else { 
					return new ReturnInfoJson(88888);
				} 
			}
            lawOutcoming.setStatus("ready");	
            lawOutcoming.setCreationDate(new Date()); 
			outcomingService.insert(lawOutcoming); 
			return new ReturnInfoJson(0);
		}catch (Exception e) {
			// TODO: handle exception
			DBLogger.error(String.format("OutcomingController|insert|error|%s|%s|%s", lawOutcoming.getTargetSysCode(),lawOutcoming.getJson(),lawOutcoming.getIntfCode()));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,value = "/selectAll/{pageNum}/{pageSize}")
	public ReturnInfoJson selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,HttpServletRequest request){
		System.out.println(pageNum);
		try {
			List<LawOutcoming> list=outcomingService.selectAll(pageNum,pageSize,request.getParameter("sourceSysCode").toString());
			return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(list)));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(0)));
		}catch (Exception e) {
			DBLogger.error(String.format("OutcomingController|selectAll|error|%s|%s", pageNum,pageSize));
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
				LawOutcoming lawOutcoming=outcomingService.selectById(new LawOutcomingKey(id,targetSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawOutcoming)));
		}catch (Exception e) {
			DBLogger.error(String.format("OutcomingController|selectRet|error|%s|%s",id,targetSysCode));
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
			 List<LawOutcoming> lawOutcoming=outcomingService.selectReading(new LawOutcomingKey(targetSysCode));
				return new ReturnInfoJson(0,ReturnUtil.returnEncodeData(request, JsonUtils.serialize(lawOutcoming))); 
		}catch (Exception e) {
			DBLogger.error(String.format("OutcomingController|selectRet|error|%s|%s",targetSysCode));
//			return ReturnUtil.returnEncodeData(request, JsonUtils.serialize(new ReturnInfoJson(99999)));
			return new ReturnInfoJson(99999);
		}
	}
	
	
	
 
	
	



}
