/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucDiagCheckRemindDao;
import com.wonders.health.tumor.tumor.entity.LucDiagCheckRemind;
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
public class LucDiagCheckRemindService {

    @Autowired
    private LucDiagCheckRemindDao lucDiagCheckRemindDao;

	public DataGrid<LucDiagCheckRemind> findPage(DataGridSearch search) {
        Integer count = lucDiagCheckRemindDao.pageCount(search);
        List<LucDiagCheckRemind> list = null;
        if (count > 0) {
        	list = lucDiagCheckRemindDao.pageList(search);
        }
        return new DataGrid<LucDiagCheckRemind>(count,list);
    }

    public LucDiagCheckRemind findById(String id) {
        return lucDiagCheckRemindDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucDiagCheckRemind vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucDiagCheckRemind po = lucDiagCheckRemindDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucDiagCheckRemindDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucDiagCheckRemindDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucDiagCheckRemindDao.delete(id);
    }

}