package com.wonders.health.tumor.common.service;

import com.wonders.health.tumor.common.dao.CancerDicAreaDao;
import com.wonders.health.tumor.common.entity.CancerDicArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */
@Service
public class AreaService {

    @Autowired
    private CancerDicAreaDao cancerDicAreaDao;

    public List<CancerDicArea> findAreas(String level, String pcode) {
        return cancerDicAreaDao.findAreas(level, pcode);
    }


}
