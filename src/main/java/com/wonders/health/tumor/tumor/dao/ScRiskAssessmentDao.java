/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.ScRiskAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 胃癌危险度评估表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface ScRiskAssessmentDao extends BaseDao<ScRiskAssessment> {
	public ScRiskAssessment get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public int deleteByCheckId(@Param("checkId") String checkId);

}