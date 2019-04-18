/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucFamilyCancerHistoryXHDao;
import com.wonders.health.tumor.tumor.entity.LucFamilyCancerHistoryXH;
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
public class LucFamilyCancerHistoryXHService {

    @Autowired
    private LucFamilyCancerHistoryXHDao lucFamilyCancerHistoryXHDao;

	public DataGrid<LucFamilyCancerHistoryXH> findPage(DataGridSearch search) {
        Integer count = lucFamilyCancerHistoryXHDao.pageCount(search);
        List<LucFamilyCancerHistoryXH> list = null;
        if (count > 0) {
        	list = lucFamilyCancerHistoryXHDao.pageList(search);
        }
        return new DataGrid<LucFamilyCancerHistoryXH>(count,list);
    }

    public LucFamilyCancerHistoryXH findById(String id) {
        return lucFamilyCancerHistoryXHDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucFamilyCancerHistoryXH vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucFamilyCancerHistoryXH po = lucFamilyCancerHistoryXHDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucFamilyCancerHistoryXHDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucFamilyCancerHistoryXHDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucFamilyCancerHistoryXHDao.delete(id);
    }

}