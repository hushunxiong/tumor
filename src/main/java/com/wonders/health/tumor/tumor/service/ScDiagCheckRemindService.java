/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.ScDiagCheckRemindDao;
import com.wonders.health.tumor.tumor.entity.ScDiagCheckRemind;
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
 * 胃癌诊断检查提醒表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class ScDiagCheckRemindService {

    @Autowired
    private ScDiagCheckRemindDao scDiagCheckRemindDao;

	public DataGrid<ScDiagCheckRemind> findPage(DataGridSearch search) {
        Integer count = scDiagCheckRemindDao.pageCount(search);
        List<ScDiagCheckRemind> list = null;
        if (count > 0) {
        	list = scDiagCheckRemindDao.pageList(search);
        }
        return new DataGrid<ScDiagCheckRemind>(count,list);
    }

    public ScDiagCheckRemind findById(String id) {
        return scDiagCheckRemindDao.get(id);
    }
    public ScDiagCheckRemind findByCheckId(String checkid) {
        return scDiagCheckRemindDao.getByCheckId(checkid);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(ScDiagCheckRemind vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            ScDiagCheckRemind po = scDiagCheckRemindDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                scDiagCheckRemindDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            scDiagCheckRemindDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        scDiagCheckRemindDao.delete(id);
    }

}