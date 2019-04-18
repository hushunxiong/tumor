/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 初筛对象信息DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface CancerPersonInfoDao extends BaseDao<CancerPersonInfo> {
	public CancerPersonInfo get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public CancerPersonInfo getByCardnoAndYType(@Param("personcardno")String personcardno,@Param("type")String type);

}