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

    public String getFullAddress(String province, String city, String county, String town, String committee, String detail) {
        CancerDicArea provinceArea = cancerDicAreaDao.get(province);
        CancerDicArea cityArea = cancerDicAreaDao.get(city);
        CancerDicArea countyArea = cancerDicAreaDao.get(county);
        CancerDicArea townArea = cancerDicAreaDao.get(town);
        CancerDicArea committeeArea = cancerDicAreaDao.get(committee);
        StringBuffer sb = new StringBuffer();

        if (provinceArea != null) {
            sb.append(provinceArea.getCname());
        }
        if (cityArea != null) {
            sb.append(cityArea.getCname());
        }
        if (countyArea != null) {
            sb.append(countyArea.getCname());
        }
        if (townArea != null) {
            sb.append(townArea.getCname());
        }
        if (committeeArea != null) {
            sb.append(committeeArea.getCname());
        }
        sb.append(detail);
        return sb.toString();
    }


}
