package com.dtxx.service.impl;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawIncomingRet;
import com.dtxx.service.IncomingService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class IncomingServiceImpl  implements IncomingService{
	
	
	
	    

	@Autowired
    private LawIncomingMapper lawIncomingMapper;

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(LawIncomingKey id) {
    	lawIncomingMapper.deleteByPrimaryKey(id);
    }

    /**
     * 增加
     *
     * @param lawIncoming
     */
    @Override
    public int insert(LawIncoming lawIncoming) {
    	return lawIncomingMapper.insert(lawIncoming);
    }

    /**
     * 更新
     *
     * @param lawIncoming
     */
    @Override
    public int update(LawIncoming lawIncoming) {
        return lawIncomingMapper.updateByPrimaryKey(lawIncoming);
    }
     

    /**
     * 更新
     *
     * @param lawIncoming
     */
    @Override
    public int updateWithBlobs(LawIncoming lawIncoming) {
        return lawIncomingMapper.updateByPrimaryKeyWithBLOBs(lawIncoming);
    }

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public LawIncoming selectById(LawIncomingKey id) {
        return lawIncomingMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部列表,并做分页
     *
     * @param pageNum 开始页数
     * @param pageSize 每页显示的数据条数
     */
    @Override
    public List<LawIncoming> selectAll(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum,pageSize);
        return lawIncomingMapper.selectAll();
    }
    
    /**
     * 查询子系统全部列表。
     *
     * @param id  
     */
    @Override
    public List<LawIncoming>  selectReading(LawIncomingKey id) {
    	 return lawIncomingMapper.selectReading(id);
    }

}
