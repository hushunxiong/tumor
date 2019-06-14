/**
 * 
 */
package com.wonders.health.tumor.closingcase.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.closingcase.entity.licClosingCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 肝癌结案DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface licClosingCaseDao extends BaseDao<licClosingCase> {

	public licClosingCase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}