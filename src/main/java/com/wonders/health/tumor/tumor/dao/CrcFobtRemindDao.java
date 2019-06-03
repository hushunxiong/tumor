/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcFobtRemind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌便隐血检查提醒表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface CrcFobtRemindDao extends BaseDao<CrcFobtRemind> {
	public CrcFobtRemind get(@Param("id") String id);

    public int delete(@Param("id") String id);

}