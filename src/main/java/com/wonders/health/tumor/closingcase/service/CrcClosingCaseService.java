/**
 * 
 */
package com.wonders.health.tumor.closingcase.service;

import com.wonders.health.tumor.closingcase.dao.CrcClosingCaseDao;
import com.wonders.health.tumor.closingcase.entity.CrcClosingCase;
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
 * 大肠癌结案Service
 * @author zhaomeng
 */
@Service
@Transactional(readOnly = true)
public class CrcClosingCaseService {

    @Autowired
    private CrcClosingCaseDao crcClosingCaseDao;

	public DataGrid<CrcClosingCase> findPage(DataGridSearch search) {
        Integer count = crcClosingCaseDao.pageCount(search);
        List<CrcClosingCase> list = null;
        if (count > 0) {
        	list = crcClosingCaseDao.pageList(search);
        }
        return new DataGrid<CrcClosingCase>(count,list);
    }

    public CrcClosingCase findById(String id) {
        return crcClosingCaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CrcClosingCase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CrcClosingCase po = crcClosingCaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                crcClosingCaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            crcClosingCaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        crcClosingCaseDao.delete(id);
    }

}