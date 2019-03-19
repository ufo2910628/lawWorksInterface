package com.dtxx.service.impl;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawIncomingRetMapper;
import com.dtxx.model.LawIncoming; 
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey; 
import com.dtxx.service.IncomingRetService;
import com.dtxx.service.IncomingService;
import com.dtxx.util.LogUtils;
import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingRetServiceImpl  implements IncomingRetService{

	@Autowired
    private LawIncomingRetMapper lawIncomingRetMapper; 
	
	Logger ExceptionLogger = LogUtils.getExceptionLogger();
	Logger BussinessLogger = LogUtils.getBussinessLogger();
	Logger DBLogger = LogUtils.getDBLogger();
	
	  /**
     * 增加
     *
     * @param 
     */
    @Override
    public int insert(LawIncomingRet lawIncomingRet) {
    	return lawIncomingRetMapper.insert(lawIncomingRet);
    }
	
	@Override
	public LawIncomingRet selectById(LawIncomingRetKey id) {
		// TODO Auto-generated method stub
		return lawIncomingRetMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 更新
     *
     * @param lawIncomingRet
     */
    @Override
    public int updateByPrimaryKeySelective(LawIncomingRet lawIncomingRet) {
        return lawIncomingRetMapper.updateByPrimaryKeySelective(lawIncomingRet);
    }
    
    /**
     * 获取子系统的列表
     *
     * @param lawIncomingRet
     */
    @Override
    public List<LawIncomingRet>  selectReading(LawIncomingRetKey id) {
        return lawIncomingRetMapper.selectReading(id);
    }
}
