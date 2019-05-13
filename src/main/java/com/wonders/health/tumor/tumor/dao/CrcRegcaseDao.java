/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌初筛信息登记表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface CrcRegcaseDao extends BaseDao<CrcRegcase> {
	public CrcRegcase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}