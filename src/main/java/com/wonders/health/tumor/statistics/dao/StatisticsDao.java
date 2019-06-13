package com.wonders.health.tumor.statistics.dao;

import com.wonders.health.tumor.statistics.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description:    统计报表Dao接口
* @Author:         lxl
* @CreateDate:     2019/5/31 9:46
* @UpdateRemark:
* @Version: 
*/
@Mapper
@Repository
public interface StatisticsDao {
    //初筛信息汇总表（阴性）
    List<NegativeSummaryVo> getNegative(SummarySearchVo searchVo);

    //初筛信息汇总（阳性大肠癌）
    List<CrcPositiveSummaryVo> getCrcPositive(SummarySearchVo searchVo);

    //初筛信息汇总（阳性肝癌）
    List<LicPositiveSummaryVo> getLicPositive(SummarySearchVo searchVo);

    //初筛信息汇总（阳性肺癌）
    List<LucPositiveSummaryVo> getLucPositive(SummarySearchVo searchVo);

    //初筛信息汇总（阳性胃癌）
    List<ScPositiveSummaryVo> getScPositive(SummarySearchVo searchVo);

    //诊断信息收集
    List<InformationCollectionVo> getInformation(SummarySearchVo searchVo);
}
