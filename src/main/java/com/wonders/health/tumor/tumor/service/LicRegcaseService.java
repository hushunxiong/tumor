/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LicRegcaseDao;
import com.wonders.health.tumor.tumor.entity.LicRegcase;
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
 * 肝癌初筛信息登记Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class LicRegcaseService {

    @Autowired
    private LicRegcaseDao licRegcaseDao;

	public DataGrid<LicRegcase> findPage(DataGridSearch search) {
        Integer count = licRegcaseDao.pageCount(search);
        List<LicRegcase> list = null;
        if (count > 0) {
        	list = licRegcaseDao.pageList(search);
        }
        return new DataGrid<LicRegcase>(count,list);
    }

    public LicRegcase findById(String id) {
        return licRegcaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LicRegcase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LicRegcase po = licRegcaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                licRegcaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            licRegcaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        licRegcaseDao.delete(id);
    }

}