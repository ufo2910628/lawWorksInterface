package com.dtxx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawOutSysDataBaseMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawOutSysDataBase;
import com.dtxx.service.LawOutSysDataBaseService;
@Service
public class LawOutSysDataBaseServiceImpl implements LawOutSysDataBaseService {
	@Autowired
    private LawOutSysDataBaseMapper lawOutSysDataBaseMapper;

	@Override
	public void delete(Long baseId) {
		// TODO Auto-generated method stub
		lawOutSysDataBaseMapper.deleteByPrimaryKey(baseId);
	}

	@Override
	public int insert(LawOutSysDataBase lawOutSysDataBase) {
		// TODO Auto-generated method stub
		return lawOutSysDataBaseMapper.insert(lawOutSysDataBase);
	}

	@Override
	public int update(LawOutSysDataBase lawOutSysDataBase) {
		// TODO Auto-generated method stub
		return lawOutSysDataBaseMapper.updateByPrimaryKey(lawOutSysDataBase);
	}

	@Override
	public int updateWithBlobs(LawOutSysDataBase lawOutSysDataBase) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LawOutSysDataBase selectById(LawOutSysDataBase lawOutSysDataBase) {
		// TODO Auto-generated method stub
		return lawOutSysDataBaseMapper.selectByPrimaryKey(lawOutSysDataBase);
	}

	@Override
	public List<LawOutSysDataBase> selectAll(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
