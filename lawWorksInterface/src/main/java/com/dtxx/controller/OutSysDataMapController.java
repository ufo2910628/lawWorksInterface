package com.dtxx.controller;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutSysDataMap;
import com.dtxx.model.dto.Return1Info;
import com.dtxx.model.dto.ReturnInfoJson;
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.IncomingService;
import com.dtxx.service.LawOutSysDataMapService;
import com.dtxx.util.JsonUtils;
import com.dtxx.util.LogUtils;
import com.dtxx.util.ReturnUtil;
import com.dtxx.util.StringUtil;

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
@RequestMapping("/LawInterceptor/OutSysDataMap")
public class OutSysDataMapController {

	@Autowired
	private LawOutSysDataMapService lawOutSysDataMapService;
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();

	/*
	 * 查询，外部系统传入需要查询的json
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/select/{sourceSysCode}")
	@ResponseBody
	public ReturnInfoJson selectRet(@PathVariable("sourceSysCode") String sourceSysCode, HttpServletRequest request) {
		// 获取解密后的json数据
		String json = request.getAttribute("jsonDecode").toString();
		try {

			JSONObject jsonD = JSONObject.fromObject(json);
			// 组织替换数据查询
			String org = jsonD.getString("org");
			// 部门替换数据查询
			String dept = jsonD.getString("dept");
			// 用户替换数据查询
			String user = jsonD.getString("user");
			// 组织查询返回对象
			LawOutSysDataMap lawOutSysDataMapOrg;
			// 部门查询返回对象
			LawOutSysDataMap lawOutSysDataMapDept;
			// 用户查询返回对象
			LawOutSysDataMap lawOutSysDataMapUser;
			LawOutSysDataMap lawOutSysDataMap = new LawOutSysDataMap();
			lawOutSysDataMap.setSyscode(sourceSysCode);
			// 返回集合
			List<LawOutSysDataMap> listR = new ArrayList<LawOutSysDataMap>();
			if (!StringUtil.isEmpty(org)) {
				lawOutSysDataMap.setType("org");
				lawOutSysDataMap.setMapValue(org);
				lawOutSysDataMapOrg = lawOutSysDataMapService.selectById(lawOutSysDataMap);
				if (null == lawOutSysDataMapOrg) {
					return new ReturnInfoJson(70001);
				} else {
					listR.add(lawOutSysDataMapOrg);
				}
			}
			if (!StringUtil.isEmpty(dept)) {
				lawOutSysDataMap.setType("dept");
				lawOutSysDataMap.setMapValue(dept);
				lawOutSysDataMapDept = lawOutSysDataMapService.selectById(lawOutSysDataMap);
				if (null == lawOutSysDataMapDept) {
					return new ReturnInfoJson(70002);
				} else {
					listR.add(lawOutSysDataMapDept);
				}
			}
			if (!StringUtil.isEmpty(user)) {
				lawOutSysDataMap.setType("user");
				lawOutSysDataMap.setMapValue(user);
				lawOutSysDataMapUser = lawOutSysDataMapService.selectById(lawOutSysDataMap);
				if (null == lawOutSysDataMapUser) {
					return new ReturnInfoJson(70003);
				} else {
					listR.add(lawOutSysDataMapUser);
				}
			}

			return new ReturnInfoJson(0, ReturnUtil.returnEncodeData(request, JsonUtils.serialize(listR)));

		} catch (Exception e) {
			DBLogger.error(e.getMessage());
			DBLogger.error(String.format("OutSysDataMapController|select|error|%s|%s", json, sourceSysCode));
			return new ReturnInfoJson(99999);
		}
	}

}
