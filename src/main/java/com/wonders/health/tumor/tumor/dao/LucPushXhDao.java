/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucPushXh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 徐汇肺癌高危大数据推送表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface LucPushXhDao extends BaseDao<LucPushXh> {
	public LucPushXh get(@Param("pushid") String pushid);

    public int delete(@Param("pushid") String pushid);

    public LucPushXh getByPersoncard(@Param("personcard") String personcard);
}