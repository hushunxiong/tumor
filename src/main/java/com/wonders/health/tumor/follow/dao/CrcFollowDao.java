/**
 * 
 */
package com.wonders.health.tumor.follow.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.follow.entity.CrcFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 大肠癌随访DAO接口
 * @author sunyang
 */
@Mapper
@Repository
public interface CrcFollowDao extends BaseDao<CrcFollow> {
	public CrcFollow get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public List<CrcFollow> getListByCheckId(@Param("crcCheckId") String crcCheckId);

}