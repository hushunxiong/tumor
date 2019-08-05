package com.wonders.health.tumor.statistics.service;

import com.wonders.health.auth.client.vo.System;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.tags.DictData;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.statistics.dao.StatisticsDao;
import com.wonders.health.tumor.statistics.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 报表统计Service
 * @Author: lxl
 * @CreateDate: 2019/5/31 9:49
 * @UpdateRemark:
 * @Version:
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private AreaService areaService;


    /**
     * 初筛信息汇总表（阴性）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<NegativeSummaryVo> getNegative(NegativeSearchVo searchVo) {
        int count = statisticsDao.pageCountNegative(searchVo);

        List<NegativeSummaryVo> summaryVoList=null;
        if (count>0){
        summaryVoList = statisticsDao.getNegative(searchVo);
        if (summaryVoList != null && summaryVoList.size() > 0) {
            for (NegativeSummaryVo vo : summaryVoList) {
                if (vo.getRegorg() != null) {
                    vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                }
                if (vo != null) {
                    //循环将居住地址拼接放入
                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ).replaceAll("null", ""));
                }

            }
        }
        }
        return new DataGrid<>(count, summaryVoList);
    }


    /**
     * 初筛信息汇总（阳性大肠癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<CrcPositiveSummaryVo> getCrcPositive(SummarySearchVo searchVo) {
        int count = statisticsDao.pageCountCrcPositive(searchVo);
        List<CrcPositiveSummaryVo> crcVoList=null;
        if(count>0){
            crcVoList = statisticsDao.getCrcPositive(searchVo);
            if (crcVoList != null && crcVoList.size() > 0) {
                for (CrcPositiveSummaryVo vo : crcVoList) {
                    if (vo.getRegorg() != null) {
                        vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                    }
                    //循环拼接写入居住地址
                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ).replaceAll("null", ""));
                }
            }
        }
        return new DataGrid<>(count, crcVoList);
    }

    /**
     * 初筛信息汇总（阳性肝癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<LicPositiveSummaryVo> getLicPositive(SummarySearchVo searchVo) {
        int count = statisticsDao.pageCountLicPositive(searchVo);
        List<LicPositiveSummaryVo> licVoList = null;
        if (count>0){
         licVoList= statisticsDao.getLicPositive(searchVo);
        if (licVoList != null && licVoList.size() > 0) {
            for (LicPositiveSummaryVo vo : licVoList) {
                if (vo.getRegorg() != null) {
                    vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                }
                if (vo != null) {
                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ).replaceAll("null", ""));
                }
            }
        }
        }
        return new DataGrid<>(count, licVoList);
    }

    /**
     * 初筛信息汇总（阳性肺癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<LucPositiveSummaryVo> getLucPositive(SummarySearchVo searchVo) {
        int count = statisticsDao.pageCountLucPositive(searchVo);
        List<LucPositiveSummaryVo> lucVoList=null;
        if (count>0) {
            lucVoList = statisticsDao.getLucPositive(searchVo);
            if (lucVoList != null && lucVoList.size() > 0) {
                for (LucPositiveSummaryVo vo : lucVoList) {
                    if (vo.getRegorg() != null) {
                        vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                    }
                    if (vo != null) {
                        vo.setAddress(areaService.getFullAddress(
                                vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                        ).replaceAll("null", ""));
                    }
                }
            }
        }
        return new DataGrid<>(count, lucVoList);
    }

    /**
     * 初筛信息汇总（阳性胃癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<ScPositiveSummaryVo> getScPositive(SummarySearchVo searchVo) {
        int count = statisticsDao.pageCountScPositive(searchVo);
        List<ScPositiveSummaryVo> scVoList =null;
        if (count>0) {
             scVoList = statisticsDao.getScPositive(searchVo);
            if (scVoList != null && scVoList.size() > 0) {
                for (ScPositiveSummaryVo vo : scVoList) {
                    if (vo.getRegorg() != null) {
                        vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                    }
                    if (vo != null) {
                        vo.setAddress(areaService.getFullAddress(
                                vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                        ).replaceAll("null", ""));
                    }
                }
            }
        }
        return new DataGrid<>(count, scVoList);
    }


    /**
     * 诊断信息收集
     *
     * @param searchVo
     * @return
     */
    public DataGrid<InformationCollectionVo> getInformation(SummarySearchVo searchVo) {
        int count = statisticsDao.pageCountInformation(searchVo);
        List<InformationCollectionVo> scVoList=null;
        if(count>0){
            scVoList = statisticsDao.getInformation(searchVo);
            if (scVoList != null && scVoList.size() > 0) {
                DictData dic = new DictData();
                String tnm ;
                for (InformationCollectionVo vo : scVoList) {
                    if (vo!=null){
                        if (vo.getZHONGLIUTNM_T()!=null){
                            vo.setZHONGLIUTNM_T(dic.generalName("60044",vo.getZHONGLIUTNM_T()));
                        }
                        if (vo.getZHONGLIUTNM_N()!=null){
                            vo.setZHONGLIUTNM_N(dic.generalName("60045",vo.getZHONGLIUTNM_N()));
                        }
                        if (vo.getZHONGLIUTNM_M()!=null){
                            vo.setZHONGLIUTNM_M(dic.generalName("60046",vo.getZHONGLIUTNM_M()));
                        }
                        tnm=(vo.getZHONGLIUTNM_T())+vo.getZHONGLIUTNM_N()+vo.getZHONGLIUTNM_M();
                        vo.setTNMfq(tnm.replaceAll("null",""));
                        if ("1".equals(vo.getShifouweijing()))
                            vo.setJcxm("胃镜");
                        if ("1".equals(vo.getChangjing()))
                            vo.setJcxm("肠镜");
                        if ("1".equals(vo.getShifouLDCT()))
                            vo.setJcxm("LDCT");
                    }
                    if (vo.getRegorg() != null) {
                        vo.setRegorg(AuthUtils.getHospitalByCode(vo.getRegorg()).getName());
                    }
                }
            }
        }

        return new DataGrid<>(count, scVoList);
    }

    /**
     * 社区卫生服务中心肿瘤早发现进度表(社区登录的场合)
     *
     * @Author:hushunxiong
     * @param searchVo
     * @return
     */
    public DataGrid<SeScheduleListVo> getSeSearch(SeScheduleSearchVo searchVo){
        searchVo.setRegorg("42485016400");
        int count = statisticsDao.pageCountSeSchedule(searchVo);
        List<SeScheduleListVo> seVoList =null;
        if (count>0) {
            seVoList = statisticsDao.getSeSchedule(searchVo);
        }
        return new DataGrid<>(count, seVoList);
    }

    /**
     * 社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)
     *
     * @Author:hushunxiong
     * @param searchVo
     * @return
     */
    public DataGrid<SeScheduleListVo> getSeAreaControl(SeScheduleSearchVo searchVo){
        int count = statisticsDao.pageCountSeAreaControl(searchVo);
        List<SeScheduleListVo> seVoList =null;
        if (count>0) {
            seVoList = statisticsDao.getSeAreaControl(searchVo);
            for (SeScheduleListVo list:seVoList) {
                if (list.getTotal()!=null){
                    list.setRegorg("合计");
                }
            }
        }
        return new DataGrid<>(count, seVoList);
    }

}
