/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.CancerHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 历史DAO接口
 * @author xuguobing
 */
@Mapper
@Repository
public interface CancerHistoryDao extends BaseDao<CancerHistory> {
	public CancerHistory get(@Param("id") String id);

    public int delete(@Param("id") String id);

    public int deleteByManageIdAndYear(@Param("manageId") String manageId,@Param("checkYear") String checkYear);

    public void changeIschange(@Param("manageId") String manageId,@Param("checkYear") String checkYear);

}