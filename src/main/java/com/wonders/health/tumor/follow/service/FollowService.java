package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.dao.FollowDao;
import com.wonders.health.tumor.tumor.dao.*;
import com.wonders.health.tumor.tumor.entity.*;
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
    private CancerPersonInfoDao cancerPersonInfoDao;
    @Autowired
    private CrcRegcaseDao crcRegcaseDao;
    @Autowired
    private LicRegcaseDao licRegcaseDao;
    @Autowired
    private LucRegcaseDao lucRegcaseDao;
    @Autowired
    private ScRegcaseDao scRegcaseDao;


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

    /**
     * 根据个人编号获取初筛对象信息
     * @return
     */
    public CancerPersonInfo getPersonInfo(String manageId) {
        return cancerPersonInfoDao.get(manageId);
    }

    /**
     * 根据年份和个人编号获取大肠癌初筛信息
     * @return
     */
    public String getCrcResult(String manageId, String checkYear) {
        CrcRegcase crcRegcase = crcRegcaseDao.getByManageidAndYear(manageId, checkYear);

        if (crcRegcase != null) {
            return crcRegcase.getCheckResult();
        }
        return "";
    }

    /**
     * 根据年份和个人编号获取肝癌初筛信息
     * @return
     */
    public String getLicResult(String manageId, String checkYear) {
        LicRegcase licRegcase = licRegcaseDao.getByManageidAndYear(manageId, checkYear);

        if (licRegcase != null) {
            return licRegcase.getCheckResult();
        }
        return "";
    }

    /**
     * 根据年份和个人编号获取肺癌初筛信息
     * @return
     */
    public String getLucResult(String manageId, String checkYear) {
        LucRegcase lucRegcase = lucRegcaseDao.getByManageidAndYear(manageId, checkYear);

        if (lucRegcase != null) {
            return lucRegcase.getCheckResult();
        }
        return "";
    }

    /**
     * 根据年份和个人编号获取胃癌初筛信息
     * @return
     */
    public String getScResult(String manageId, String checkYear) {
        ScRegcase scRegcase = scRegcaseDao.getByManageidAndYear(manageId, checkYear);

        if (scRegcase != null) {
            return scRegcase.getCheckResult();
        }
        return "";
    }
}
