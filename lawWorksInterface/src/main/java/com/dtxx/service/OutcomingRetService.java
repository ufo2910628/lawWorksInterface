package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.model.LawOutcomingRetKey;

import java.util.List;

public interface OutcomingRetService {
  
    /** 增加*/
    public int insert(LawOutcomingRet lawOutcomingRet);
    
    /** 更新*/
    public int updateByPrimaryKey(LawOutcomingRet lawOutcomingRet);
    
    /** 查询子系统全部列表*/
    public List<LawOutcomingRet> selectReading(LawOutcomingRetKey id) ;
    
    /** 查询单个*/
    public LawOutcomingRet selectById(LawOutcomingRetKey id);
}
