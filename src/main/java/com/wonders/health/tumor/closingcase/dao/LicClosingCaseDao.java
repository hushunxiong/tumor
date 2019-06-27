/**
 * 
 */
package com.wonders.health.tumor.closingcase.dao;

import com.wonders.health.tumor.closingcase.entity.LicClosingCase;
import com.wonders.health.tumor.common.model.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 肝癌结案DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface LicClosingCaseDao extends BaseDao<LicClosingCase> {

	public LicClosingCase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}