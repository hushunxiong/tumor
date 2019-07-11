/**
 * 
 */
package com.wonders.health.tumor.tumor.dao;

import com.wonders.health.tumor.common.model.BaseDao;
import com.wonders.health.tumor.tumor.entity.LucLDCTCheckXH;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 徐汇肺癌LDCT检查结果表DAO接口
 * @author wangyixian
 */
@Mapper
@Repository
public interface LucLDCTCheckXHDao extends BaseDao<LucLDCTCheckXH> {
	public LucLDCTCheckXH get(@Param("id") String id);

    public int delete(@Param("id") String id);

    /**
     * 根据ldct预约id获取检查结果
     * @param orderId ldct预约id
     * @return 检查结果
     */
    LucLDCTCheckXH getByOrderId(String orderId);

}