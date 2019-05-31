/**
 * 
 */
package com.wonders.health.tumor.closingcase.service;

import com.wonders.health.tumor.closingcase.dao.licClosingCaseDao;
import com.wonders.health.tumor.closingcase.entity.licClosingCase;
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
 * 肝癌结案Service
 * @author zhaomeng
 */
@Service
@Transactional(readOnly = true)
public class licClosingCaseService {

    @Autowired
    private licClosingCaseDao licClosingCaseDao;

	public DataGrid<licClosingCase> findPage(DataGridSearch search) {
        Integer count = licClosingCaseDao.pageCount(search);
        List<licClosingCase> list = null;
        if (count > 0) {
        	list = licClosingCaseDao.pageList(search);
        }
        return new DataGrid<licClosingCase>(count,list);
    }

    public licClosingCase findById(String id) {
        return licClosingCaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(licClosingCase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            licClosingCase po = licClosingCaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                licClosingCaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            licClosingCaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        licClosingCaseDao.delete(id);
    }

}