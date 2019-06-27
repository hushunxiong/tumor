/**
 * 
 */
package com.wonders.health.tumor.closingcase.dao;

import com.wonders.health.tumor.closingcase.entity.ScClosingCase;
import com.wonders.health.tumor.common.model.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 胃癌结案DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface ScClosingCaseDao extends BaseDao<ScClosingCase> {
	public ScClosingCase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}