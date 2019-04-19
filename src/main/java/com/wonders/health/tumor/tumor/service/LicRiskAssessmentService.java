/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LicRiskAssessmentDao;
import com.wonders.health.tumor.tumor.entity.LicRiskAssessment;
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
 * 肝癌危险度评估表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class LicRiskAssessmentService implements BaseService{

    @Autowired
    private LicRiskAssessmentDao licRiskAssessmentDao;

	public DataGrid<LicRiskAssessment> findPage(DataGridSearch search) {
        Integer count = licRiskAssessmentDao.pageCount(search);
        List<LicRiskAssessment> list = null;
        if (count > 0) {
        	list = licRiskAssessmentDao.pageList(search);
        }
        return new DataGrid<LicRiskAssessment>(count,list);
    }

    public LicRiskAssessment findById(String id) {
        return licRiskAssessmentDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LicRiskAssessment vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LicRiskAssessment po = licRiskAssessmentDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                licRiskAssessmentDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            licRiskAssessmentDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        licRiskAssessmentDao.delete(id);
    }

}