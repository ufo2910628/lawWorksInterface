package com.dtxx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合操作
 * @author Hoperun
 *
 */
public class SetOperation {

	/**
	 * 从所有id集合中祛除现有id集合并返回
	 * @param hasIds 现有id集合
	 * @param allIds 所有id集合
	 * @return
	 */
	public String[] questionIdSetOperation01(String[] hasIds, List<String> allIds){
		String[] arr;
		for(String s : hasIds){
			allIds.remove(s);
		}
		if(null != allIds){
			arr = new String[allIds.size()];
			for(int i=0; i<allIds.size(); i++){
				arr[i] = allIds.get(i);
			}
			return arr;
		}else{
			return null;
		}
	}
	
	/**
	 * 从所有id集合中祛除现有id集合并返回
	 * @param hasIds 现有id集合
	 * @param allIds 所有id集合
	 * @return
	 */
	public String[] questionIdSetOperation02(String[] hasIds, List<Map> allIds){
		String[] arr;
		Map map = new HashMap();
		for(String s : hasIds){
			map.put("QUESTION_ID", s);
			allIds.remove(map);
		}
		if(null != allIds){
			arr = new String[allIds.size()];
			for(int i=0; i<allIds.size(); i++){
				arr[i] = allIds.get(i).get("QUESTION_ID") + "";
			}
			return arr;
		}else{
			return null;
		}
	}
	
	/**
	 * 从所有id集合中祛除现有id集合并返回
	 * @param hasIds 现有id集合
	 * @param allIds 所有id集合
	 * @return
	 */
	public Map<String, Object> questionIdSetOperation1(String[] hasIds, List<String> allIds){
		Map<String, Object> mapResult = new HashMap();
		String[] arrNew;
		String strDel = "";
		boolean flag;
		for(String s : hasIds){
			flag = allIds.remove(s);
			if(!flag){
				strDel = strDel + s + ",";
			}
		}
		if(null != allIds){
			arrNew = new String[allIds.size()];
			for(int i=0; i<allIds.size(); i++){
				arrNew[i] = allIds.get(i);
			}
			mapResult.put("arrNew", arrNew);
		}
		mapResult.put("strDel", strDel);
		return mapResult;
	}
	
	/**
	 * 从所有id集合中祛除现有id集合并返回
	 * @param hasIds 现有id集合
	 * @param allIds 所有id集合
	 * @return
	 */
	public Map<String, Object> questionIdSetOperation2(String[] hasIds, List<Map> allIds){
		Map<String, Object> mapResult = new HashMap();
		String[] arrNew;
		String strDel = "";
		boolean flag;
		Map map = new HashMap();
		for(String s : hasIds){
			map.put("QUESTION_ID", s);
			flag = allIds.remove(map);
			if(!flag){
				strDel = strDel + s + ",";
			}
		}
		if(null != allIds){
			arrNew = new String[allIds.size()];
			for(int i=0; i<allIds.size(); i++){
				arrNew[i] = allIds.get(i).get("QUESTION_ID") + "";
			}
			mapResult.put("arrNew", arrNew);
		}
		mapResult.put("strDel", strDel);
		return mapResult;
	}

}
