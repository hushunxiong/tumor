/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucPushriskXh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 徐汇肺癌危险因素推送表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface LucPushriskXhDao extends BaseDao<LucPushriskXh> {
	public LucPushriskXh get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public List<LucPushriskXh> getRiskData(@Param("pushid") String pushid);
}