package com.dtxx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtxx.mapper.LawIncomingMapper;
import com.dtxx.mapper.LawSysSecretKeyMapper;
import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawSysSecretKey;
import com.dtxx.service.LawSysSecretKeyService;
@Service
public class LawSysSecretKeyServiceImpl implements LawSysSecretKeyService{
	@Autowired
    private LawSysSecretKeyMapper lawSysSecretKeyMapper;

	@Override
	public LawSysSecretKey selectById(String SYS_CODE) {
		// TODO Auto-generated method stub
		return lawSysSecretKeyMapper.selectByPrimaryKey(SYS_CODE);
	}

}
