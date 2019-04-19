/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 医疗机构表DAO接口
 * @author xuguobing
 */
@Mapper
@Repository
public interface CancerDicHospitalInfoDao extends BaseDao<CancerDicHospitalInfo> {
	public CancerDicHospitalInfo get(@Param("hospitalId") String hospitalId);

    public int delete(@Param("hospitalId") String hospitalId);

}