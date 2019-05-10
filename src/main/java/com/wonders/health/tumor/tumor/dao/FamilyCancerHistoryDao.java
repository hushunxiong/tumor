/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.FamilyCancerHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 危险度评估-一级亲属癌症史表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface FamilyCancerHistoryDao extends BaseDao<FamilyCancerHistory> {
	public FamilyCancerHistory get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public int deleteByCheckId(@Param("checkId") String checkId);

    public void changeIschange(@Param("checkId") String checkId);

}