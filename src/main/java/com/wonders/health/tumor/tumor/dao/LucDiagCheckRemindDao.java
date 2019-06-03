/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucDiagCheckRemind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 胃癌诊断检查提醒表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface LucDiagCheckRemindDao extends BaseDao<LucDiagCheckRemind> {
	public LucDiagCheckRemind get(@Param("id") String id);

    public int delete(@Param("id") String id);

}