package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawOutSysDataBase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LawOutSysDataBaseService {
    /** 删除 */
    public void delete(Long id);
    /** 增加*/
    public int insert(LawOutSysDataBase lawOutSysDataBase);
    /** 更新*/
    public int update(LawOutSysDataBase lawOutSysDataBase);
    /** 更新*/
    public int updateWithBlobs(LawOutSysDataBase lawOutSysDataBase);
    /** 查询单个*/
    public LawOutSysDataBase selectById(LawOutSysDataBase lawOutSysDataBase);
    /** 查询全部列表*/
    public List<LawOutSysDataBase> selectAll(int pageNum, int pageSize);
     
    
    
}
