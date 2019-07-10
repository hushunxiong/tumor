/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucAppLdctXhDao;
import com.wonders.health.tumor.tumor.dao.LucAppLdctXhDao;
import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
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
 * 徐汇肺癌LDCT预约记录表Service
 * @author wangyixian
 */
@Service
@Transactional(readOnly = true)
public class LucAppLdctXhService {

    @Autowired
    private LucAppLdctXhDao LucAppLdctXhDao;

	public DataGrid<LucAppLdctXh> findPage(DataGridSearch search) {
        Integer count = LucAppLdctXhDao.pageCount(search);
        List<LucAppLdctXh> list = null;
        if (count > 0) {
        	list = LucAppLdctXhDao.pageList(search);
        }
        return new DataGrid<LucAppLdctXh>(count,list);
    }

    public LucAppLdctXh findById(String id) {
        return LucAppLdctXhDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucAppLdctXh vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucAppLdctXh po = LucAppLdctXhDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                LucAppLdctXhDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            LucAppLdctXhDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        LucAppLdctXhDao.delete(id);
    }

}