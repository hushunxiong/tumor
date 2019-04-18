/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.google.common.collect.Lists;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.*;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.PinYinUtil;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.dao.DicHospitalInfoDao;
import com.wonders.health.tumor.tumor.entity.DicHospitalInfo;
import com.wonders.health.tumor.common.utils.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 医疗机构表Service
 * @author zhaomeng
 */
@Service
@Transactional(readOnly = true)
public class DicHospitalInfoService {

    @Autowired
    private DicHospitalInfoDao dicHospitalInfoDao;

    public List<DataOption> findHospitalsByAreaCode(String areaCode, User user) {
        List<DataOption> offices = Lists.newArrayList();
        if("bsq".equals(AuthUtils.judgeRole(user.getOrgCode()))){
            DicHospitalInfo dicHospitalInfo=dicHospitalInfoDao.getHospitalById(user.getOrgCode());
            if(dicHospitalInfo!=null){
                offices.add(new DataOption(dicHospitalInfo.getHospitalId(), dicHospitalInfo.getHospitalName(), PinYinUtil.getFirstSpell(dicHospitalInfo.getHospitalName())));
            }
        }else{
            List<DicHospitalInfo> hospitals = dicHospitalInfoDao.findHospitalsByAreaCode(areaCode);
            if (hospitals != null && hospitals.size() > 0) {
                for (DicHospitalInfo u : hospitals) {
                    if (StringUtils.isNotBlank(u.getHospitalName())) {
                        offices.add(new DataOption(u.getHospitalId(), u.getHospitalName(), PinYinUtil.getFirstSpell(u.getHospitalName())));
                    }
                }
            }
        }

        return  offices;
    }




}