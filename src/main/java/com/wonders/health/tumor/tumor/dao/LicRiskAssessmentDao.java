/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LicRiskAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 肝癌危险度评估表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface LicRiskAssessmentDao extends BaseDao<LicRiskAssessment> {
	public LicRiskAssessment get(@Param("id") String id);

    public int delete(@Param("id") String id);

}