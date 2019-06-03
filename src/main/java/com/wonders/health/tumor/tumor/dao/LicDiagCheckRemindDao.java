/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LicDiagCheckRemind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 肝癌诊断检查提醒表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface LicDiagCheckRemindDao extends BaseDao<LicDiagCheckRemind> {
	public LicDiagCheckRemind get(@Param("id") String id);

    public int delete(@Param("id") String id);

}