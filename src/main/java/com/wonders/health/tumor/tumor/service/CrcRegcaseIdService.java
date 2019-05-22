package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CrcRegcaseIdDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcaseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CrcRegcaseIdService {
    @Autowired
    private CrcRegcaseIdDao crcRegcaseIdDao;

    public List<CrcRegcaseId> getByAreacode(String areaCode){
        return crcRegcaseIdDao.getByAreacode(areaCode);
    }

}
