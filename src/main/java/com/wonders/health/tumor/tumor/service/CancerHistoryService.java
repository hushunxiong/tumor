/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CancerHistoryDao;
import com.wonders.health.tumor.tumor.entity.CancerHistory;
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
 * 历史Service
 * @author xuguobing
 */
@Service
@Transactional(readOnly = true)
public class CancerHistoryService implements BaseService{

    @Autowired
    private CancerHistoryDao cancerHistoryDao;

	public DataGrid<CancerHistory> findPage(DataGridSearch search) {
        Integer count = cancerHistoryDao.pageCount(search);
        List<CancerHistory> list = null;
        if (count > 0) {
        	list = cancerHistoryDao.pageList(search);
        }
        return new DataGrid<CancerHistory>(count,list);
    }

    public CancerHistory findById(String id) {
        return cancerHistoryDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CancerHistory vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CancerHistory po = cancerHistoryDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                cancerHistoryDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            cancerHistoryDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        cancerHistoryDao.delete(id);
    }

}