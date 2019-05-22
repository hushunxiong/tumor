/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcaseId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 大肠癌初筛id机构代码表DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface CrcRegcaseIdDao extends BaseDao<CrcRegcaseId> {

    public List<CrcRegcaseId> getByAreacode(@Param("areaCode") String areaCode);

}