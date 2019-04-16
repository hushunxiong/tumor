/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.ScRegcaseDao;
import com.wonders.health.tumor.tumor.entity.ScRegcase;
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
 * 胃癌初筛信息登记Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class ScRegcaseService {

    @Autowired
    private ScRegcaseDao scRegcaseDao;

	public DataGrid<ScRegcase> findPage(DataGridSearch search) {
        Integer count = scRegcaseDao.pageCount(search);
        List<ScRegcase> list = null;
        if (count > 0) {
        	list = scRegcaseDao.pageList(search);
        }
        return new DataGrid<ScRegcase>(count,list);
    }

    public ScRegcase findById(String id) {
        return scRegcaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(ScRegcase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            ScRegcase po = scRegcaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                scRegcaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            scRegcaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        scRegcaseDao.delete(id);
    }

}