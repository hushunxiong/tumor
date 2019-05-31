/**
 * 
 */
package com.wonders.health.tumor.closingcase.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.closingcase.entity.scClosingCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 胃癌结案DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface scClosingCaseDao extends BaseDao<scClosingCase> {
	public scClosingCase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}