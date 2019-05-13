/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.tumor.dao.CancerDicHospitalInfoDao;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
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
 * 医疗机构表Service
 * @author xuguobing
 */
@Service
@Transactional(readOnly = true)
public class CancerDicHospitalInfoService {

    @Autowired
    private CancerDicHospitalInfoDao cancerDicHospitalInfoDao;

	public DataGrid<CancerDicHospitalInfo> findPage(DataGridSearch search) {
        Integer count = cancerDicHospitalInfoDao.pageCount(search);
        List<CancerDicHospitalInfo> list = null;
        if (count > 0) {
        	list = cancerDicHospitalInfoDao.pageList(search);
        }
        return new DataGrid<CancerDicHospitalInfo>(count,list);
    }

    public CancerDicHospitalInfo findById(String hospitalId) {
        return cancerDicHospitalInfoDao.get(hospitalId);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CancerDicHospitalInfo vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getHospitalId())) { //修改
            CancerDicHospitalInfo po = cancerDicHospitalInfoDao.get(vo.getHospitalId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                cancerDicHospitalInfoDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
				vo.init(userId);
				cancerDicHospitalInfoDao.insert(vo);
				return new AjaxReturn<Map<String, String>>(true, "保存成功");
            }
        }
        return new AjaxReturn<Map<String, String>>(false, "联合主键需要传入");
    }

    @Transactional(readOnly = false)
    public void deleteById(String hospitalId) {
        cancerDicHospitalInfoDao.delete(hospitalId);
    }

}