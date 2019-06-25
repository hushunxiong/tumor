/**
 * 
 */
package com.wonders.health.tumor.busremind.dao;

import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.tumor.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 大肠癌结案DAO接口
 * @author menglianghai
 */
@Mapper
@Repository
public interface BusRemindDao{

	public BusRemindResultVo get(@Param("id") String id);

	public List<BusRemindResultVo> getBasic(DataGridSearch search);

	public List<BusRemindResultVo> getCrcFobtRemind(@Param("personcard") String personcard,
													@Param("status") String status,
													@Param("year")String year,
													@Param("startDate")String startDate,
													@Param("endDate")String endDate);

	public List<BusRemindResultVo> getCrcDiag(@Param("personcard")String personcard,
											  @Param("status")String status,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag,
											  @Param("year")String year,
											  @Param("orgCode")String orgCode,
											  @Param("startDate")String startDate,
											  @Param("endDate")String endDate);

	public List<BusRemindResultVo> getLicDiag(@Param("personcard") String personcard,
											  @Param("status")String status,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag,
											  @Param("year")String year,
											  @Param("orgCode")String orgCode,
											  @Param("startDate")String startDate,
											  @Param("endDate")String endDate);

	public List<BusRemindResultVo> getLucDiag(@Param("personcard") String personcard,
											  @Param("status")String status,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag,
											  @Param("year")String year,
											  @Param("orgCode")String orgCode,
											  @Param("startDate")String startDate,
											  @Param("endDate")String endDate);

	public List<BusRemindResultVo> getScDiag(@Param("personcard") String personcard,
											 @Param("status")String status,
											 @Param("crcFlag")String crcFlag,
											 @Param("licFlag")String licFlag,
											 @Param("scFlag")String scFlag,
											 @Param("lucFlag")String lucFlag,
											 @Param("year")String year,
											 @Param("orgCode")String orgCode,
											 @Param("startDate")String startDate,
											 @Param("endDate")String endDate);


    public int delete(@Param("id") String id);

    public void updateCrcFobtRemind(CrcFobtRemind crcFobtRemind);

    public void updateCrcDiag(CrcDiagCheckRemind crcDiagCheckRemind);

    public void updateLicDiag(LicDiagCheckRemind licDiagCheckRemind);

    public void updateScDiag(ScDiagCheckRemind scDiagCheckRemind);

    public void updateLucDiag(LucDiagCheckRemind scDiagCheckRemind);

}