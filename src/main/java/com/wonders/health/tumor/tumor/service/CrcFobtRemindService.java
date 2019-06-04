/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CrcFobtRemindDao;
import com.wonders.health.tumor.tumor.entity.CrcFobtRemind;
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
 * 大肠癌便隐血检查提醒表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class CrcFobtRemindService {

    @Autowired
    private CrcFobtRemindDao crcFobtRemindDao;

	public DataGrid<CrcFobtRemind> findPage(DataGridSearch search) {
        Integer count = crcFobtRemindDao.pageCount(search);
        List<CrcFobtRemind> list = null;
        if (count > 0) {
        	list = crcFobtRemindDao.pageList(search);
        }
        return new DataGrid<CrcFobtRemind>(count,list);
    }

    public CrcFobtRemind findById(String id) {
        return crcFobtRemindDao.get(id);
    }

    public CrcFobtRemind findByCheckId(String checkid) {
        return crcFobtRemindDao.getByCheckId(checkid);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CrcFobtRemind vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CrcFobtRemind po = crcFobtRemindDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                crcFobtRemindDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            crcFobtRemindDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        crcFobtRemindDao.delete(id);
    }

}