package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.entity.CancerDic;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.dao.LucPushXhDao;
import com.wonders.health.tumor.tumor.entity.LucPushXh;
import com.wonders.health.tumor.tumor.vo.BigDataSearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String[] IGNORES = {"riskAccount"};
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60054");
        List<LucPushXh> list=null;
        Integer count=lucPushXhDao.pageCount(search);
        if(count>0){
            list=lucPushXhDao.pageList(search);
        }
        if(list!=null&&list.size()>0){
            for(LucPushXh xh:list){
                LucPushXh person = lucPushXhDao.getByPersoncard(xh.getPersoncard());
                BeanUtils.copyProperties(person, xh, IGNORES);

                //居住地址拼接
                xh.setAddressDetail(areaService.getFullAddress(
                        xh.getProvince(), xh.getCity(), xh.getCounty(),
                        xh.getTown(), xh.getCommittee(), xh.getDetail()));
                //手机优先，然后是固定电话
                xh.setMobile(StringUtils.isNotBlank(xh.getMobile())
                        ? xh.getMobile() : xh.getTelephone());

                String riskAccount = xh.getRiskAccount();
                if (StringUtils.isNotBlank(riskAccount)) {
                    List<String> riskList = Arrays.asList(riskAccount.split(","));
                    List<String> riskNewList =riskList.stream().map(n ->generalForMap.get(n).getName()).collect(Collectors.toList());
                    xh.setRiskAccount(riskNewList.stream().collect(Collectors.joining(",")));
                }
            }
        }
        return new DataGrid<LucPushXh>(count,list);
    }
}
