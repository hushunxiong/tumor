/**
 * 
 */
package com.wonders.health.tumor.follow.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.follow.entity.LicFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 肝癌随访管理表DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface LicFollowDao extends BaseDao<LicFollow> {
	public LicFollow get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public List<LicFollow> getListByCheckId(@Param("licCheckId") String licCheckId);

}