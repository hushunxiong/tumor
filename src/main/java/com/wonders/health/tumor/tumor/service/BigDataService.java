package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.dao.LucPushXhDao;
import com.wonders.health.tumor.tumor.entity.LucPushXh;
import com.wonders.health.tumor.tumor.vo.BigDataSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 大数据筛查Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class BigDataService {
    @Autowired
    private AreaService areaService;
    @Autowired
    private LucPushXhDao lucPushXhDao;

    public DataGrid<LucPushXh> findPage(BigDataSearchVo search) {
        List<LucPushXh> list=null;
        Integer count=lucPushXhDao.pageCount(search);
        if(count>0){
            list=lucPushXhDao.pageList(search);
        }
        if(list!=null&&list.size()>0){
            for(LucPushXh xh:list){
                //居住地址拼接
                xh.setAddressDetail(areaService.getFullAddress(
                        xh.getProvince(), xh.getCity(), xh.getCounty(),
                        xh.getTown(), xh.getCommittee(), xh.getDetail()));
                //手机优先，然后是固定电话
                xh.setMobile(StringUtils.isNotBlank(xh.getMobile())
                        ? xh.getMobile() : xh.getTelephone());
            }
        }
        return new DataGrid<LucPushXh>(count,list);
    }
}
