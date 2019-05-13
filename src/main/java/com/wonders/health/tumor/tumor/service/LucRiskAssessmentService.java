/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucRiskAssessmentDao;
import com.wonders.health.tumor.tumor.entity.LucRiskAssessment;
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
 * 肺癌危险度评估表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class LucRiskAssessmentService implements BaseService{

    @Autowired
    private LucRiskAssessmentDao lucRiskAssessmentDao;

	public DataGrid<LucRiskAssessment> findPage(DataGridSearch search) {
        Integer count = lucRiskAssessmentDao.pageCount(search);
        List<LucRiskAssessment> list = null;
        if (count > 0) {
        	list = lucRiskAssessmentDao.pageList(search);
        }
        return new DataGrid<LucRiskAssessment>(count,list);
    }

    public LucRiskAssessment findById(String id) {
        return lucRiskAssessmentDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucRiskAssessment vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucRiskAssessment po = lucRiskAssessmentDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucRiskAssessmentDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucRiskAssessmentDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucRiskAssessmentDao.delete(id);
    }

}