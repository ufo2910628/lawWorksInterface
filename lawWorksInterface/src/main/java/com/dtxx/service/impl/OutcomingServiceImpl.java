package com.dtxx.service.impl;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawOutcomingMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.model.LawIncomingRetKey;
import com.dtxx.model.LawOutcoming;
import com.dtxx.model.LawOutcomingKey;
import com.dtxx.model.LawOutcomingRet;
import com.dtxx.service.IncomingService;
import com.dtxx.service.OutcomingService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomingServiceImpl  implements OutcomingService{

	@Autowired
    private LawOutcomingMapper lawOutcomingMapper;

 
    /**
     * 查询全部列表,并做分页
     *
     * @param pageNum 开始页数
     * @param pageSize 每页显示的数据条数
     */
    @Override
    public List<LawOutcoming> selectAll(int pageNum, int pageSize,String sourceSysCode) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum,pageSize);
        return lawOutcomingMapper.selectAll(sourceSysCode);
    }
    /**
     * 增加
     *
     * @param 
     */
    @Override
    public int insert(LawOutcoming lawOutcoming ) {
    	return lawOutcomingMapper.insert(lawOutcoming);
    }
    
    /**
     * 更新
     *
     * @param 
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(LawOutcoming lawOutcoming ) {
    	return lawOutcomingMapper.updateByPrimaryKeyWithBLOBs(lawOutcoming);
    }
    
    /**
     * 获取子系统的列表
     *
     * @param LawOutcomingKey
     */
    @Override
    public List<LawOutcoming> selectReading(LawOutcomingKey id) {
        return lawOutcomingMapper.selectReading(id);
    }

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public LawOutcoming selectById(LawOutcomingKey id) {
    	return lawOutcomingMapper.selectByPrimaryKey(id);
    }
}
