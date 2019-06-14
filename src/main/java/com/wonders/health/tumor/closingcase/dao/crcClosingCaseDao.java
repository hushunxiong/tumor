/**
 * 
 */
package com.wonders.health.tumor.closingcase.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.closingcase.entity.crcClosingCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 大肠癌结案DAO接口
 * @author zhaomeng
 */
@Mapper
@Repository
public interface crcClosingCaseDao extends BaseDao<crcClosingCase> {
	public crcClosingCase get(@Param("id") String id);

    public int delete(@Param("id") String id);

}