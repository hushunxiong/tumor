package com.wonders.health.tumor.follow.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.follow.entity.LucFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 肺癌随访DAO接口
 * @author zhangyi
 */
@Mapper
@Repository
public interface LucFollowDao extends BaseDao<LucFollow> {
	public LucFollow get(@Param("id") String id);

    public int delete(@Param("id") String id);

    List<LucFollow> queryLucFollowListByLucRegcaseId(@Param("lucRegcaseId")String lucRegcaseId);

}