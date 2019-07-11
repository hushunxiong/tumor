/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.LucLDCTCheckXHDao;
import com.wonders.health.tumor.tumor.entity.LucLDCTCheckXH;
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
 * 徐汇肺癌LDCT检查结果表Service
 * @author wangyixian
 */
@Service
@Transactional(readOnly = true)
public class LucLDCTCheckXHService {

    @Autowired
    private LucLDCTCheckXHDao lucLDCTCheckXHDao;

	public DataGrid<LucLDCTCheckXH> findPage(DataGridSearch search) {
        Integer count = lucLDCTCheckXHDao.pageCount(search);
        List<LucLDCTCheckXH> list = null;
        if (count > 0) {
        	list = lucLDCTCheckXHDao.pageList(search);
        }
        return new DataGrid<LucLDCTCheckXH>(count,list);
    }

    public LucLDCTCheckXH findById(String id) {
        return lucLDCTCheckXHDao.get(id);
    }

    public LucLDCTCheckXH findByOrderId(String orderId) {
        return lucLDCTCheckXHDao.getByOrderId(orderId);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LucLDCTCheckXH vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            LucLDCTCheckXH po = lucLDCTCheckXHDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                lucLDCTCheckXHDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            lucLDCTCheckXHDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        lucLDCTCheckXHDao.delete(id);
    }

}