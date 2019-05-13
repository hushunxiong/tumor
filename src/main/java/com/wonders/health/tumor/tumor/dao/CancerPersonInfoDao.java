/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
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

    /**
     * 根据个人管理编号和初筛年份查询初筛信息
     * @param id
     * @param csnf
     * @return
     */
    public CancerPersonInfo getById(@Param("id") String id,@Param("csnf") String csnf);

    public int delete(@Param("id") String id);

    public CancerPersonInfo getByCardnoAndYType(@Param("personcardno")String personcardno,@Param("type")String type);


    void updateChange(@Param("id")String id);
}