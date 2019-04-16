/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CrcFobtDao;
import com.wonders.health.tumor.tumor.entity.CrcFobt;
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
 * 大肠癌便隐血检查表Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class CrcFobtService {

    @Autowired
    private CrcFobtDao crcFobtDao;

	public DataGrid<CrcFobt> findPage(DataGridSearch search) {
        Integer count = crcFobtDao.pageCount(search);
        List<CrcFobt> list = null;
        if (count > 0) {
        	list = crcFobtDao.pageList(search);
        }
        return new DataGrid<CrcFobt>(count,list);
    }

    public CrcFobt findById(String id) {
        return crcFobtDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CrcFobt vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CrcFobt po = crcFobtDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                crcFobtDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            crcFobtDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        crcFobtDao.delete(id);
    }

}