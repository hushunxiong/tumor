package com.wonders.health.tumor.common.service;

import com.wonders.health.tumor.tumor.dao.CancerDicHospitalInfoDao;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class HospitalDicService {

    @Autowired
    private CancerDicHospitalInfoDao cancerDicHospitalInfoDao;

    public List<CancerDicHospitalInfo> queryName(String name) {
        return cancerDicHospitalInfoDao.queryName(name);
    }
}
