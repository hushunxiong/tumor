package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.model.BaseDao;

public interface BaseService {
    default Object getByManageidAndYear(BaseDao baseDao,String manageid, String checkyear){
        return baseDao.getByManageidAndYear(manageid, checkyear);
    }
}
