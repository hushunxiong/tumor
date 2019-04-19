package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.model.BaseDao;

import java.util.List;

public interface BaseService {

    //4个初筛信息登记表根据manageid和checkyear查询记录
    default Object getByManageidAndYear(BaseDao baseDao,String manageid, String checkyear){
        return baseDao.getByManageidAndYear(manageid, checkyear);
    }

    //4个业务危险度评估表+大肠癌便隐血检查表+肝癌辅助检查表
    // 根据外键checkid查询记录
    default Object getByCheckid(BaseDao baseDao,String checkid){
        return baseDao.getByCheckid(checkid);
    }

    //根据manageid和checkyear查询危险度评估-癌症史表
    default List getListByManageidAndYear(BaseDao baseDao, String manageid, String checkyear){
        return baseDao.getListByManageidAndYear(manageid, checkyear);
    }

    //4个业务危险度评估表新增时查询idnumber是否已占用
    default Object checkIdnumber(BaseDao baseDao,String manageid,String idnumber){
        return baseDao.checkIdnumber(manageid,idnumber);
    }

}
