/**
 * 
 */
package com.wonders.health.tumor.busremind.dao;

import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.tumor.entity.CrcFobtRemind;
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

	public List<BusRemindResultVo> getCrcFobtRemind(@Param("personcard") String personcard);

	public List<BusRemindResultVo> getCrcDiag(@Param("personcard")String personcard,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag);

	public List<BusRemindResultVo> getLicDiag(@Param("personcard") String personcard,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag);

	public List<BusRemindResultVo> getLucDiag(@Param("personcard") String personcard,
											  @Param("crcFlag")String crcFlag,
											  @Param("licFlag")String licFlag,
											  @Param("scFlag")String scFlag,
											  @Param("lucFlag")String lucFlag);

	public List<BusRemindResultVo> getScDiag(@Param("personcard") String personcard,
											 @Param("crcFlag")String crcFlag,
											 @Param("licFlag")String licFlag,
											 @Param("scFlag")String scFlag,
											 @Param("lucFlag")String lucFlag);


    public int delete(@Param("id") String id);

    public void updateCrcFobtRemind(CrcFobtRemind crcFobtRemind);

}