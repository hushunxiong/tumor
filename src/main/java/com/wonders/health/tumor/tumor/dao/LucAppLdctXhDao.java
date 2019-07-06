/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 徐汇肺癌LDCT预约记录表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface LucAppLdctXhDao extends BaseDao<LucAppLdctXh> {
	public LucAppLdctXh get(@Param("id") String id);

    public int delete(@Param("id") String id);

}