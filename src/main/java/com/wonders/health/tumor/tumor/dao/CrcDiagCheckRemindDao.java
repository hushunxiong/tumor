/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcDiagCheckRemind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌诊断检查提醒表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface CrcDiagCheckRemindDao extends BaseDao<CrcDiagCheckRemind> {
	public CrcDiagCheckRemind get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public void updateRemindStatus(CrcDiagCheckRemind remind);

}