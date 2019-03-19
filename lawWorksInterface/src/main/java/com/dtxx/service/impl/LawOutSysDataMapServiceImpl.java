package com.dtxx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawOutSysDataBaseMapper;
import com.dtxx.mapper.LawOutSysDataMapMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawOutSysDataBase;
import com.dtxx.model.LawOutSysDataMap;
import com.dtxx.service.LawOutSysDataBaseService;
import com.dtxx.service.LawOutSysDataMapService;
@Service
public class LawOutSysDataMapServiceImpl implements LawOutSysDataMapService {
	@Autowired
    private LawOutSysDataMapMapper lawOutSysDataMapMapper;

	@Override
	public void delete(Long baseId) {
		// TODO Auto-generated method stub
		lawOutSysDataMapMapper.deleteByPrimaryKey(baseId);
	}

	@Override
	public int insert(LawOutSysDataMap lawOutSysDataMap) {
		// TODO Auto-generated method stub
		return lawOutSysDataMapMapper.insert(lawOutSysDataMap);
	}

	@Override
	public int update(LawOutSysDataMap lawOutSysDataMap) {
		// TODO Auto-generated method stub
		return lawOutSysDataMapMapper.updateByPrimaryKey(lawOutSysDataMap);
	}



	@Override
	public LawOutSysDataMap selectById(LawOutSysDataMap lawOutSysDataMap) {
		// TODO Auto-generated method stub
		return lawOutSysDataMapMapper.selectByPrimaryKey(lawOutSysDataMap);
	}

	@Override
	public List<LawOutSysDataMap> selectAll(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateWithBlobs(LawOutSysDataMap lawOutSysDataMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}
