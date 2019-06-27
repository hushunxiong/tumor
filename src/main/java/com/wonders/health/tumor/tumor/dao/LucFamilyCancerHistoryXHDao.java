/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucFamilyCancerHistoryXH;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 历史DAO接口
 * @author xuguobing
 */
@Mapper
@Repository
public interface LucFamilyCancerHistoryXHDao extends BaseDao<LucFamilyCancerHistoryXH> {
	public LucFamilyCancerHistoryXH get(@Param("id") String id);

    public int delete(@Param("id") String id);

}