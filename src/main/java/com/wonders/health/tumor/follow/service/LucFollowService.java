package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.follow.dao.LucFollowDao;
import com.wonders.health.tumor.follow.entity.LucFollow;
import com.wonders.health.tumor.follow.vo.LucFollowVo;
import com.wonders.health.tumor.tumor.dao.CancerPersonInfoDao;
import com.wonders.health.tumor.tumor.dao.LucDiagCheckRemindDao;
import com.wonders.health.tumor.tumor.dao.LucRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.entity.LucDiagCheckRemind;
import com.wonders.health.tumor.tumor.entity.LucRegcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 肺癌随访Service
 * @author zhangyi
 */
@Service
@Transactional(readOnly = true)
public class LucFollowService {

    @Autowired
    private LucFollowDao lucFollowDao;
    @Autowired
    private CancerPersonInfoDao cancerPersonInfoDao;
    @Autowired
    private AreaService areaService;
    @Autowired
    private LucRegcaseDao lucRegcaseDao;
    @Autowired
    private LucDiagCheckRemindDao lucDiagCheckRemindDao;

    /**
     * 肺癌随访一览页面数据
     */
    public LucFollowVo lucFollowList(String manageId, String checkYear){
        LucFollowVo vo=handleData(manageId,checkYear);
        List<LucFollow> list=lucFollowDao.queryLucFollowListByLucRegcaseId(vo.getLucRegcaseId());
        if (list.size()>0){
            vo.setZdzt("已诊断");
        }else{
            vo.setZdzt("未诊断");
        }
        return vo;
    }
    /**
     * 根据肺癌初筛信息登记id查询随访数据
     */
    public List<LucFollow> queryLucFollowListByLucRegcaseId(String lucRegcaseId){
        return lucFollowDao.queryLucFollowListByLucRegcaseId(lucRegcaseId);
    }
    /**
     * 肺癌随访页面数据
     */
    public LucFollowVo lucFollowForm(String manageId,String checkYear,String flag,String id){
        LucFollowVo vo=handleData(manageId,checkYear);
        if (flag.equals("1")){
            vo.setLucFollow(new LucFollow());
        }else{
            vo.setLucFollow(lucFollowDao.get(id));
        }
        vo.setFlag(flag);
        return vo;
    }

    /**
     * 处理数据
     * @param manageId
     * @param checkYear
     * @return
     */
    public LucFollowVo handleData(String manageId,String checkYear){
        LucFollowVo vo=new LucFollowVo();
        //初筛对象信息
        CancerPersonInfo cpInfo=cancerPersonInfoDao.getById(manageId,checkYear);
        vo.setManageId(manageId);
        vo.setCheckYear(checkYear);
        vo.setPersoncard(cpInfo.getPersoncard());
        vo.setPersoncardType(cpInfo.getPersoncardType());
        vo.setMobile(cpInfo.getMobile());
        vo.setName(cpInfo.getName());
        vo.setGender(cpInfo.getGender());
        vo.setBirth(cpInfo.getBirth());
        vo.setTelephone(cpInfo.getTelephone());
        //居住地址整合
        vo.setAddress(areaService.getFullAddress(cpInfo.getAddressProvince(), cpInfo.getAddressCity(), cpInfo.getAddressCounty(), cpInfo.getAddressTown(), cpInfo.getAddressCommittee(), cpInfo.getAddressDetail()));
        //肺癌初筛信息
        LucRegcase lr=lucRegcaseDao.getByManageidAndYear(manageId,checkYear);
        if (null!=lr){
            vo.setRegdate(lr.getRegdate());
            vo.setCheckResult(lr.getCheckResult());
            vo.setLucRegcaseId(lr.getId());
        }
        return vo;
    }
    /**
     * 保存肺癌随访
     */
    public Boolean saveLucFollow(LucFollowVo vo){
        LucFollow luc=vo.getLucFollow();
        int flag;
        if (null!=luc.getId()&&!luc.getId().equals("")){
            luc.setSuifangjigouId(vo.getUser().getOrgCode());
            luc.setSuifangyishengId(vo.getUser().getId());
            luc.setSuifangDate(new Date());
            luc.setSuifangjigouName(AuthUtils.getHospitalByCode(vo.getUser().getOrgCode()).getName());
            luc.setUpdateBy(vo.getUser().getName());
            luc.setUpdateDate(new Date());
            flag=lucFollowDao.update(luc);
        }else{
            //uuid生成主键
            luc.setId(IdGen.uuid());
            //随访编号
            luc.setPrimaryMark(vo.getUser().getOrgCode()+ DateUtils.formatDate(new Date(),"yyyyMMddHHmm"));
            luc.setCreateDate(new Date());
            luc.setSuifangyishengId(vo.getUser().getId());
            luc.setDelFlag("0");
            luc.setCreateBy(vo.getUser().getName());
            luc.setUpdateBy(vo.getUser().getName());
            luc.setUpdateDate(new Date());
            luc.setSuifangjigouId(vo.getUser().getOrgCode());
            luc.setSuifangjigouName(AuthUtils.getHospitalByCode(vo.getUser().getOrgCode()).getName());
            if (null!=luc.getHualiaojigouId()&&!luc.getHualiaojigouId().equals("")){
                luc.setHualiaojigouName(AuthUtils.getHospitalByCode(luc.getHualiaojigouId()).getName());
            }
            if (null!=luc.getFangliaojigouId()&&!luc.getFangliaojigouId().equals("")){
                luc.setFangliaojigouName(AuthUtils.getHospitalByCode(luc.getFangliaojigouId()).getName());
            }
            if (null!=luc.getShoushujigouId()&&!luc.getShoushujigouId().equals("")){
                luc.setShoushujigouName(AuthUtils.getHospitalByCode(luc.getShoushujigouId()).getName());
            }
            luc.setZhenduanjigouName(AuthUtils.getHospitalByCode(luc.getZhenduanjigouId()).getName());
            luc.setSuifangDate(new Date());
            luc.setDengjijigouId(vo.getUser().getOrgCode());
            luc.setDengjiDate(new Date());
            luc.setDengjiId(vo.getUser().getId());
            flag=lucFollowDao.insert(vo.getLucFollow());
        }
        if (flag>0){
            //查询肺癌诊断检查提醒表
            LucDiagCheckRemind ldcr=lucDiagCheckRemindDao.getByCheckId(luc.getLucCheckId());
            if (null!=ldcr&&!ldcr.getRemindStatus().equals("04")){
                ldcr.setRemindStatus("02");
                ldcr.setUpdateBy(vo.getUser().getName());
                ldcr.setUpdateDate(new Date());
                lucDiagCheckRemindDao.update(ldcr);
            }
            return true;
        }else{
            return false;
        }
    }
    /**
     * 删除肺癌随访
     */
    public Boolean delLucFollow(String id){
        int flag=lucFollowDao.delete(id);
        if (flag>0){
            return true;
        }else{
            return false;
        }
    }

}