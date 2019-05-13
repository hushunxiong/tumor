/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.FamilyCancerHistoryDao;
import com.wonders.health.tumor.tumor.entity.FamilyCancerHistory;
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
 * 危险度评估-一级亲属癌症史表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class FamilyCancerHistoryService implements BaseService{

    @Autowired
    private FamilyCancerHistoryDao familyCancerHistoryDao;

	public DataGrid<FamilyCancerHistory> findPage(DataGridSearch search) {
        Integer count = familyCancerHistoryDao.pageCount(search);
        List<FamilyCancerHistory> list = null;
        if (count > 0) {
        	list = familyCancerHistoryDao.pageList(search);
        }
        return new DataGrid<FamilyCancerHistory>(count,list);
    }

    public FamilyCancerHistory findById(String id) {
        return familyCancerHistoryDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(FamilyCancerHistory vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            FamilyCancerHistory po = familyCancerHistoryDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                familyCancerHistoryDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            familyCancerHistoryDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        familyCancerHistoryDao.delete(id);
    }
    @Transactional(readOnly = false)
    public void isChange(String checkId) {
	    familyCancerHistoryDao.changeIschange(checkId);
    }


}