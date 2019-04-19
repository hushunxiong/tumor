/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CrcRiskAssessmentDao;
import com.wonders.health.tumor.tumor.entity.CrcRiskAssessment;
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
 * 大肠癌危险度评估Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class CrcRiskAssessmentService implements BaseService{

    @Autowired
    private CrcRiskAssessmentDao crcRiskAssessmentDao;

	public DataGrid<CrcRiskAssessment> findPage(DataGridSearch search) {
        Integer count = crcRiskAssessmentDao.pageCount(search);
        List<CrcRiskAssessment> list = null;
        if (count > 0) {
        	list = crcRiskAssessmentDao.pageList(search);
        }
        return new DataGrid<CrcRiskAssessment>(count,list);
    }

    public CrcRiskAssessment findById(String id) {
        return crcRiskAssessmentDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CrcRiskAssessment vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CrcRiskAssessment po = crcRiskAssessmentDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                crcRiskAssessmentDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            crcRiskAssessmentDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        crcRiskAssessmentDao.delete(id);
    }

}