/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucAppDiagXh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 徐汇肺癌专病门诊预约记录表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface LucAppDiagXhDao extends BaseDao<LucAppDiagXh> {
	public LucAppDiagXh get(@Param("id") String id);

    public int delete(@Param("id") String id);

}