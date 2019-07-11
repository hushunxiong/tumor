/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucRegcase;
import com.wonders.health.tumor.tumor.entity.LucRiskAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 肺癌初筛信息登记DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface LucRegcaseDao extends BaseDao<LucRegcase> {
	public LucRegcase get(@Param("id") String id);

    public int delete(@Param("id") String id);

    /**
     * 根据个人管理编号获得肺癌初筛信息一览
     * @param manageId 个人管理编号
     * @return 肺癌初筛信息一览
     */
    List<LucRegcase> getByManageId(String manageId);
}