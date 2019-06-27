/**
 * 
 */
package com.wonders.health.tumor.closingcase.service;

import com.wonders.health.tumor.closingcase.dao.LucClosingCaseDao;
import com.wonders.health.tumor.closingcase.entity.LucClosingCase;
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
 * 肺癌结案Service
 * @author zhaomeng
 */
@Service
@Transactional(readOnly = true)
public class LucClosingCaseService {

    @Autowired
    private LucClosingCaseDao lucClosingCaseDao;

	public DataGrid<LucClosingCase> findPage(DataGridSearch search) {
        Integer count = lucClosingCaseDao.pageCount(search);
        List<LucClosingCase> list = null;
        if (count > 0) {
        	list = lucClosingCaseDao.pageList(search);
        }
        return new DataGrid<LucClosingCase>(count,list);
    }

    public LucClosingCase findById(String id) {
        return lucClosingCaseDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucClosingCase vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucClosingCase po = lucClosingCaseDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucClosingCaseDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucClosingCaseDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucClosingCaseDao.delete(id);
    }

}