/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.*;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private CrcRegcaseDao crcRegcaseDao; //

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
    private  CancerHistoryDao cancerHistoryDao;


    public DataGrid<CancerPersonInfo> findPage(DataGridSearch search) {
        Integer count = cancerPersonInfoDao.pageCount(search);
        List<CancerPersonInfo> list = null;
        if (count > 0) {
        	list = cancerPersonInfoDao.pageList(search);
        }
        return new DataGrid<CancerPersonInfo>(count,list);
    }

    public CancerPersonInfo findById(String id) {
        return cancerPersonInfoDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CancerPersonInfo vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CancerPersonInfo po = cancerPersonInfoDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                cancerPersonInfoDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            cancerPersonInfoDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        cancerPersonInfoDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void deleteByRegcaseId(CancerPersonInfo info) {

	    if(StringUtils.isNotBlank(info.getCrcCheckId())){
	      /*  crcRegcaseDao.delete(info.getCrcCheckId());
	        crcFobtDao.deleteByCheckId(info.getCrcCheckId());
	        crcRiskAssessmentDao.deleteByCheckId(info.getCrcCheckId());
	        familyCancerHistoryDao.deleteByCheckId(info.getCrcCheckId());*/
        }
	    if(StringUtils.isNotBlank(info.getLicCheckId())){
	       /* licRegcaseDao.delete(info.getLicCheckId());
	        licAssistCheckDao.deleteByCheckId(info.getLicCheckId());
	        licRiskAssessmentDao.delete(info.getLicCheckId());
	        familyCancerHistoryDao.deleteByCheckId(info.getLicCheckId());*/
        }
        if(StringUtils.isNotBlank(info.getLucCheckId())){
           /* lucRegcaseDao.delete(info.getLucCheckId());
            lucRiskAssessmentDao.deleteByCheckId(info.getLucCheckId());
            familyCancerHistoryDao.deleteByCheckId(info.getLucCheckId());*/

        }
        if(StringUtils.isNotBlank(info.getScCheckId())){
          /*  scRegcaseDao.delete(info.getScCheckId());
            scRiskAssessmentDao.deleteByCheckId(info.getScCheckId());
            familyCancerHistoryDao.deleteByCheckId(info.getScCheckId());*/
        }

        if(info.getHistoryDelflag()<=0){
            System.out.println("确定删除个数："+info.getHistoryDelflag());
            /*cancerHistoryDao.deleteByManageIdAndYear(info.getId(),info.getCsnf());*/
        }

    }

}