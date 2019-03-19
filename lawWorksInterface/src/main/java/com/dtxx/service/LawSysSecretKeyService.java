package com.dtxx.service;

import com.dtxx.model.LawIncoming;
import com.dtxx.model.LawIncomingKey;
import com.dtxx.model.LawSysSecretKey;

import java.util.List;

public interface LawSysSecretKeyService {
  
    /** 查询单个*/
    public LawSysSecretKey selectById(String SYS_CODE);
}
