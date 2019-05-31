/**
 * 
 */
package com.wonders.health.tumor.follow.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.follow.entity.ScFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 胃癌随访DAO接口
 * @author zhangyi
 */
@Mapper
@Repository
public interface ScFollowDao extends BaseDao<ScFollow> {
	public ScFollow get(@Param("id") String id);

    public int delete(@Param("id") String id);

}