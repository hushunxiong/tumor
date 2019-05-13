/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRiskAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌危险度评估DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface CrcRiskAssessmentDao extends BaseDao<CrcRiskAssessment> {
	public CrcRiskAssessment get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public int deleteByCheckId(@Param("checkId") String checkId);

}