package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IncomingRetService {
    /** 查询单个*/
    public LawIncomingRet selectById(LawIncomingRetKey id);

    /** 增加*/
    public int insert(LawIncomingRet lawIncomingRet);
    
    /** 更新*/
    public int updateByPrimaryKeySelective(LawIncomingRet lawIncomingRet);
    
    /** 查询子系统全部列表*/
    public List<LawIncomingRet> selectReading(LawIncomingRetKey id) ;
     
}
