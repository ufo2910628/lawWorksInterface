package com.dtxx.service.impl;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawOutcomingRetMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.model.LawOutcomingRetKey;
import com.dtxx.service.IncomingService;
import com.dtxx.service.OutcomingRetService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomingRetServiceImpl  implements OutcomingRetService{

	@Autowired
    private LawOutcomingRetMapper lawOutcomingRetMapper;

  
    /**
     * 增加
     *
     * @param 
     */
    @Override
    public int insert(LawOutcomingRet lawOutcomingRet) {
    	return lawOutcomingRetMapper.insert(lawOutcomingRet);
    }

    /**
     * 更新
     *
     * @param 
     */
    @Override
    public int updateByPrimaryKey(LawOutcomingRet lawOutcomingRet) {
    	return lawOutcomingRetMapper.updateByPrimaryKey(lawOutcomingRet);
    }
    
    /**
     * 获取子系统的列表
     *
     * @param id
     */
    @Override
    
    public List<LawOutcomingRet> selectReading(LawOutcomingRetKey id) {
        return lawOutcomingRetMapper.selectReading(id);
    }

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public LawOutcomingRet selectById(LawOutcomingRetKey id) {
    	return lawOutcomingRetMapper.selectByPrimaryKey(id);
    }
}
