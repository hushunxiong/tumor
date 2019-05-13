/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucRegcaseDao;
import com.wonders.health.tumor.tumor.entity.LucRegcase;
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
 * 肺癌初筛信息登记Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class LucRegcaseService implements BaseService{

    @Autowired
    private LucRegcaseDao lucRegcaseDao;

	public DataGrid<LucRegcase> findPage(DataGridSearch search) {
        Integer count = lucRegcaseDao.pageCount(search);
        List<LucRegcase> list = null;
        if (count > 0) {
        	list = lucRegcaseDao.pageList(search);
        }
        return new DataGrid<LucRegcase>(count,list);
    }

    public LucRegcase findById(String id) {
        return lucRegcaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucRegcase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucRegcase po = lucRegcaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucRegcaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucRegcaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucRegcaseDao.delete(id);
    }

}