package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.dao.FollowDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 随访一览Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class FollowService {

    @Autowired
    private FollowDao followDao;
    @Autowired
    private AreaService areaService;


    public DataGrid<CancerPersonInfo> findPage(DataGridSearch search) {
        List<CancerPersonInfo> list=null;
        Integer count=followDao.pageCount(search);
        if(count>0){
            list=followDao.pageList(search);
        }

        if(list!=null&&list.size()>0){
            for(CancerPersonInfo info : list){
                if(StringUtils.isNotBlank(info.getCrcCheckYear())){
                    info.setCsnf(info.getCrcCheckYear());
                }
                if(StringUtils.isNotBlank(info.getLucCheckYear())){
                    info.setCsnf(info.getLucCheckYear());
                }
                if(StringUtils.isNotBlank(info.getLicCheckYear())){
                    info.setCsnf(info.getLicCheckYear());
                }
                if(StringUtils.isNotBlank(info.getScCheckYear())){
                    info.setCsnf(info.getScCheckYear());
                }
                //居住地址拼接
                info.setAddressDetail(areaService.getFullAddress(
                        info.getAddressProvince(), info.getAddressCity(), info.getAddressCounty(),
                        info.getAddressTown(), info.getAddressCommittee(), info.getAddressDetail()));
                //手机优先，然后是固定电话
                info.setMobile(StringUtils.isNotBlank(info.getMobile())
                        ? info.getMobile() : info.getTelephone());
            }
        }

        return new DataGrid<CancerPersonInfo>(count,list);
    }

}
