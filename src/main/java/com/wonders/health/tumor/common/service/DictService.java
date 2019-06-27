package com.wonders.health.tumor.common.service;

import com.wonders.health.tumor.common.dao.CancerDicDao;
import com.wonders.health.tumor.common.entity.CancerDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunyang on 2019/4/16.
 */
@Service
public class DictService {
    @Autowired
    private CancerDicDao cancerDicDao;

    public List<CancerDic> getDictsById(String id) {
        return cancerDicDao.getDictsById(id);
    }
}
