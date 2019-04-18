/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.DicHospitalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 医疗机构表DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface DicHospitalInfoDao extends BaseDao<DicHospitalInfo> {

	public List<DicHospitalInfo> findHospitalsByAreaCode(@Param("areaCode") String areaCode);


	public DicHospitalInfo getHospitalById(@Param("orgCode") String orgCode);


}