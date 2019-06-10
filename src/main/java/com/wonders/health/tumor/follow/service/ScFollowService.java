package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.follow.dao.ScFollowDao;
import com.wonders.health.tumor.follow.entity.ScFollow;
import com.wonders.health.tumor.follow.vo.ScFollowVo;
import com.wonders.health.tumor.tumor.dao.CancerPersonInfoDao;
import com.wonders.health.tumor.tumor.dao.ScRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.entity.ScRegcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 胃癌随访Service
 * @author zhangyi
 */
@Service
@Transactional(readOnly = true)
public class ScFollowService {

    @Autowired
    private ScFollowDao scFollowDao;
    @Autowired
    private CancerPersonInfoDao cancerPersonInfoDao;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ScRegcaseDao scRegcaseDao;
    /**
     * 胃癌随访一览页面数据
     */
    public ScFollowVo scFollowList(String manageId,String checkYear){
        ScFollowVo vo=handleData(manageId,checkYear);
        List<ScFollow> list=scFollowDao.queryScFollowListByScRegcaseId(vo.getScRegcaseId());
        if (list.size()>0){
          vo.setZdzt("已诊断");
        }else{
            vo.setZdzt("未诊断");
        }
        return vo;
    }
    /**
     * 根据胃癌初筛信息登记id查询随访数据
     */
    public List<ScFollow> queryScFollowListByCsId(String scRegcaseId){
        return scFollowDao.queryScFollowListByScRegcaseId(scRegcaseId);
    }
    /**
     * 胃癌随访页面数据
     */
    public ScFollowVo scFollowForm(String manageId,String checkYear,String flag,String id){
        ScFollowVo vo=handleData(manageId,checkYear);
        if (flag.equals("1")){
            vo.setScFollow(new ScFollow());
        }else{
            vo.setScFollow(scFollowDao.get(id));
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
    public ScFollowVo handleData(String manageId,String checkYear){
        ScFollowVo vo=new ScFollowVo();
        //初筛对象信息
        CancerPersonInfo cpInfo=cancerPersonInfoDao.getById(manageId,checkYear);
        vo.setManageId(manageId);
        vo.setCheckYear(checkYear);
        vo.setPersoncardType(cpInfo.getPersoncardType());
        vo.setPersoncard(cpInfo.getPersoncard());
        vo.setName(cpInfo.getName());
        vo.setGender(cpInfo.getGender());
        vo.setBirth(cpInfo.getBirth());
        vo.setTelephone(cpInfo.getTelephone());
        vo.setMobile(cpInfo.getMobile());
        //居住地址整合
        vo.setAddress(areaService.getFullAddress(cpInfo.getAddressProvince(), cpInfo.getAddressCity(), cpInfo.getAddressCounty(), cpInfo.getAddressTown(), cpInfo.getAddressCommittee(), cpInfo.getAddressDetail()));
        //胃癌初筛信息
        ScRegcase sr=scRegcaseDao.getByManageidAndYear(manageId,checkYear);
        vo.setRegdate(sr.getRegdate());
        vo.setCheckResult(sr.getCheckResult());
        vo.setScRegcaseId(sr.getId());
        return vo;
    }
    /**
     * 保存胃癌随访
     */
    public Boolean saveScFollow(ScFollowVo vo){
        ScFollow sc=vo.getScFollow();
        int flag;
        if (null!=sc.getId()&&!sc.getId().equals("")){
            sc.setSuifangjigouId(vo.getUser().getOrgCode());
            sc.setSuifangDate(new Date());
            sc.setSuifangjigouName(AuthUtils.getHospitalByCode(vo.getUser().getOrgCode()).getName());
            sc.setSuifangyishengId(vo.getUser().getId());
            flag=scFollowDao.update(sc);
        }else{
            //uuid生成主键
            sc.setId(IdGen.uuid());
            //随访编号
            sc.setPrimaryMark(Math.random()+"");
            sc.setDelFlag("0");
            sc.setCreateBy(vo.getUser().getName());
            sc.setCreateDate(new Date());
            sc.setSuifangjigouId(vo.getUser().getOrgCode());
            sc.setSuifangjigouName(AuthUtils.getHospitalByCode(vo.getUser().getOrgCode()).getName());
            sc.setSuifangyishengId(vo.getUser().getId());
            sc.setSuifangDate(new Date());
            sc.setDengjiDate(new Date());
            sc.setDengjiId(vo.getUser().getId());
            sc.setDengjijigouId(vo.getUser().getOrgCode());
            flag=scFollowDao.insert(vo.getScFollow());
        }
        if (flag>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 删除胃癌随访
     */
    public Boolean delScFollow(String id){
        int flag=scFollowDao.delete(id);
        if (flag>0){
            return true;
        }else{
            return false;
        }
    }
}