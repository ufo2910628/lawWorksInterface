package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawOutSysDataBase;
import com.dtxx.model.LawOutSysDataMap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LawOutSysDataMapService {
    /** 删除 */
    public void delete(Long id);
    /** 增加*/
    public int insert(LawOutSysDataMap lawOutSysDataMap);
    /** 更新*/
    public int update(LawOutSysDataMap lawOutSysDataMap);
    /** 更新*/
    public int updateWithBlobs(LawOutSysDataMap lawOutSysDataMap);
    /** 查询单个*/
    public LawOutSysDataMap selectById(LawOutSysDataMap lawOutSysDataMap);
    /** 查询全部列表*/
    public List<LawOutSysDataMap> selectAll(int pageNum, int pageSize);
     
    
    
}
