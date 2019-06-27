/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LicRegcase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 肝癌初筛信息登记DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface LicRegcaseDao extends BaseDao<LicRegcase> {
	public LicRegcase get(@Param("id") String id);

    public int delete(@Param("id") String id);
}