/**
 * 
 */
package com.wonders.health.tumor.tumor.service;


import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.Constants;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.dao.*;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
import com.wonders.health.tumor.tumor.vo.XhPatientResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;

/**
 * 初筛对象信息Service
 * @author zhaomeng
 */
@Service
@Transactional(readOnly = true)
public class CancerPersonInfoService {

    @Autowired
    private CancerPersonInfoDao cancerPersonInfoDao;

    @Autowired
    private CrcRegcaseDao crcRegcaseDao;

    @Autowired
    private CrcRiskAssessmentDao crcRiskAssessmentDao;

    @Autowired
    private CrcFobtDao crcFobtDao;

    @Autowired
    private LicRegcaseDao licRegcaseDao;

    @Autowired
    private LicRiskAssessmentDao licRiskAssessmentDao;

    @Autowired
    private LicAssistCheckDao licAssistCheckDao;

    @Autowired
    private ScRegcaseDao scRegcaseDao;

    @Autowired
    private ScRiskAssessmentDao scRiskAssessmentDao;

    @Autowired
    private LucRegcaseDao lucRegcaseDao;

    @Autowired
    private LucRiskAssessmentDao lucRiskAssessmentDao;

    @Autowired
    private FamilyCancerHistoryDao familyCancerHistoryDao;

    @Autowired
    private CancerHistoryDao cancerHistoryDao;

    @Autowired
    private LucAppLdctXhDao lucAppLdctXhDao;

    @Value("${area_code}")
    private String areaCode;

    @Autowired
    private XkyyRegisterService xkyyRegisterService;

    public DataGrid<CancerPersonInfo> findPage(DataGridSearch search) {
        List<CancerPersonInfo> list=null;
        Integer count=cancerPersonInfoDao.pageCount(search);
        if(count>0){
            list=cancerPersonInfoDao.pageList(search);
        }
        if(list!=null&&list.size()>0){
            for(CancerPersonInfo info:list){
                    Integer delRecordsFlag=0;
                    if(StringUtils.isNotBlank(info.getCrcCheckYear())){
                        info.setCsnf(info.getCrcCheckYear());
                        delRecordsFlag++;
                    }
                    if(StringUtils.isNotBlank(info.getLucCheckYear())){
                        info.setCsnf(info.getLucCheckYear());
                        delRecordsFlag++;
                    }
                    if(StringUtils.isNotBlank(info.getLicCheckYear())){
                        info.setCsnf(info.getLicCheckYear());
                        delRecordsFlag++;
                    }
                    if(StringUtils.isNotBlank(info.getScCheckYear())){
                        info.setCsnf(info.getScCheckYear());
                        delRecordsFlag++;
                    }
                    info.setDelRecordsFlag(delRecordsFlag);
            }
        }
        return new DataGrid<CancerPersonInfo>(count,list);
    }



    public CancerPersonInfo findById(String id) {
        return cancerPersonInfoDao.get(id);
    }

    public CancerPersonInfo findByInfoId(String id,String csnf) {
        return cancerPersonInfoDao.getById(id,csnf);
    }


    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CancerPersonInfo vo, String userId)  {
        boolean flag = true;

        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CancerPersonInfo po = cancerPersonInfoDao.get(vo.getId());
            if (po != null) {
                vo.setPatid(po.getPatid());
                vo.setBlh(po.getBlh());
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);

                //调用接口
                XhPatientResultVo resultVo = null;
                try {
                    //阳性的并且是身份证的人调用接口
                    if (StringUtils.equals("01", vo.getPersoncardType())
                            && StringUtils.equals("2", vo.getLucResult())
                            && StringUtils.isBlank(vo.getPatid())) {
                        resultVo = xkyyRegisterService.callApiByProxy(vo);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    flag = false;
                }
                if (resultVo != null) {
                    po.setPatid(resultVo.getPatid());
                    po.setBlh(resultVo.getBlh());
                }
                cancerPersonInfoDao.update(po);

                if (!flag) {
                    return new AjaxReturn<Map<String, String>>(true, "修改成功, 首诊患者建档注册不成功");
                }
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            //徐汇区
            if (StringUtils.equals("310104000000", areaCode)) {
                //调用接口
                XhPatientResultVo resultVo = null;
                try {
                    //阳性的并且是身份证的人调用接口
                    if (StringUtils.equals("01", vo.getPersoncardType()) && StringUtils.equals("2", vo.getLucResult())) {
                        resultVo = xkyyRegisterService.callApiByProxy(vo);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    flag = false;
                }
                if (resultVo != null) {
                    vo.setPatid(resultVo.getPatid());
                    vo.setBlh(resultVo.getBlh());
                }
                cancerPersonInfoDao.insert(vo);
            } else {
                cancerPersonInfoDao.insert(vo);
            }
            if (!flag) {
                return new AjaxReturn<Map<String, String>>(true, "保存成功, 首诊患者建档注册不成功");
            }
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }

    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        cancerPersonInfoDao.delete(id);
    }

    /**
     * 初筛信息删除
     * @param info
     */
    @Transactional(readOnly = false)
    public void deleteByRegcaseId(CancerPersonInfo info) {

	    if(StringUtils.isNotBlank(info.getCrcCheckId())){
	        crcRegcaseDao.delete(info.getCrcCheckId());
	        crcFobtDao.deleteByCheckId(info.getCrcCheckId());
	        crcRiskAssessmentDao.deleteByCheckId(info.getCrcCheckId());
	        familyCancerHistoryDao.deleteByCheckId(info.getCrcCheckId());
        }
	    if(StringUtils.isNotBlank(info.getLicCheckId())){
	        licRegcaseDao.delete(info.getLicCheckId());
	        licAssistCheckDao.deleteByCheckId(info.getLicCheckId());
	        licRiskAssessmentDao.delete(info.getLicCheckId());
	        familyCancerHistoryDao.deleteByCheckId(info.getLicCheckId());
        }
        if(StringUtils.isNotBlank(info.getLucCheckId())){
            lucRegcaseDao.delete(info.getLucCheckId());
            lucRiskAssessmentDao.deleteByCheckId(info.getLucCheckId());
            familyCancerHistoryDao.deleteByCheckId(info.getLucCheckId());

        }
        if(StringUtils.isNotBlank(info.getScCheckId())){
            scRegcaseDao.delete(info.getScCheckId());
            scRiskAssessmentDao.deleteByCheckId(info.getScCheckId());
            familyCancerHistoryDao.deleteByCheckId(info.getScCheckId());
        }

        /**
         * 四种癌症初筛信息都不存在
         * 删除危险度评估-癌症史表信息
         */
        if(info.getHistoryDelflag()<=0){
            cancerHistoryDao.deleteByManageIdAndYear(info.getId(),info.getCsnf());
        }

    }

    public void updateChange(String id){
        cancerPersonInfoDao.updateChange(id);
    }

    @Transactional(readOnly = false)
    public void insertAppCheck(String manageId, String checkCode, String checkDate, String checkTime, User user) {
        LucAppLdctXh ldct = new LucAppLdctXh();
        ldct.setId(IdGen.uuid());
        ldct.init(user.getId());
        ldct.setManageid(manageId);
        ldct.setAppCheckCode(checkCode);
        ldct.setAppCheckDate(DateUtils.parseDate(checkDate));
        ldct.setAppCheckTime(checkTime);
        ldct.setAppointmentDoc(user.getId());
        ldct.setAppointmentOrg(user.getOrgCode());
        ldct.setAppointmentDate(new Date());
        lucAppLdctXhDao.insert(ldct);
    }

    @Transactional(readOnly = false)
    public void deleteAppCheck(String ldctid, User user) {
        LucAppLdctXh ldct = lucAppLdctXhDao.get(ldctid);
        ldct.initByUpdate(user.getId());
        ldct.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        lucAppLdctXhDao.update(ldct);
    }

    public LucAppLdctXh getLdctInfo(String id) {
        List<LucAppLdctXh> list = lucAppLdctXhDao.findByManageId(id);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}