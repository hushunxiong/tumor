/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.vo.CancerPersonInfoSearchResultVo;
import com.wonders.health.tumor.tumor.vo.CancerPersonInfoSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //初筛一览列表显示
    List<CancerPersonInfo> cancerPersonInfoResultList(CancerPersonInfoSearchVo cancerPersonInfoSearchVo);

    void updateChange(@Param("id")String id);
}