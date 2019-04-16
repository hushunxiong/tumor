/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcFobt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌便隐血检查表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface CrcFobtDao extends BaseDao<CrcFobt> {
	public CrcFobt get(@Param("id") String id);

    public int delete(@Param("id") String id);

}