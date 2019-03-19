package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;

import java.util.List;

public interface OutcomingService {
    /** 查询全部列表*/
    public List<LawOutcoming> selectAll(int pageNum, int pageSize,String sourceSysCode);
    
    /** 增加*/
    public int insert(LawOutcoming lawOutcoming);
    
    /** 更新*/
    public int updateByPrimaryKeyWithBLOBs(LawOutcoming lawOutcoming);
    
    /** 查询子系统全部列表*/
    public List<LawOutcoming> selectReading(LawOutcomingKey id) ;
    
    /** 查询单个*/
    public LawOutcoming selectById(LawOutcomingKey id);
}
