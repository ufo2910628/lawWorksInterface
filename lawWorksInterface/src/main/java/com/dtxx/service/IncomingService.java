package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IncomingService {
    /** 删除 */
    public void delete(LawIncomingKey id);
    /** 增加*/
    public int insert(LawIncoming lawIncoming);
    /** 更新*/
    public int update(LawIncoming lawIncoming);
    /** 更新*/
    public int updateWithBlobs(LawIncoming lawIncoming);
    /** 查询单个*/
    public LawIncoming selectById(LawIncomingKey id);
    /** 查询全部列表*/
    public List<LawIncoming> selectAll(int pageNum, int pageSize);
    /** 查询子系统全部列表*/
    public List<LawIncoming> selectReading(LawIncomingKey id) ;
	 
     
    
    
}
