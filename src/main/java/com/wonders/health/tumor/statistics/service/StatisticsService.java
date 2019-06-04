package com.wonders.health.tumor.statistics.service;

import com.google.common.collect.Lists;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.statistics.dao.StatisticsDao;
import com.wonders.health.tumor.statistics.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public List<NegativeSummaryVo> getNegative(SummarySearchVo searchVo) {
        List<NegativeSummaryVo> summaryVoList = statisticsDao.getNegative(searchVo);
        if (summaryVoList != null && summaryVoList.size() > 0) {

            for (NegativeSummaryVo vo : summaryVoList) {
                if (vo != null) {
                    //循环将居住地址拼接放入
                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ));
                }

            }
        }
        return summaryVoList;
    }


    /**
     * 初筛信息汇总（阳性大肠癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<CrcPositiveSummaryVo> getCrcPositive(SummarySearchVo searchVo) {
        List<CrcPositiveSummaryVo> crcVoList = statisticsDao.getCrcPositive(searchVo);
        if (crcVoList != null && crcVoList.size() > 0) {
            AuthUtils a = new AuthUtils();
            for (CrcPositiveSummaryVo vo : crcVoList) {

                //循环通过医疗机构代码获取医疗机构名称
                vo.setRegorg(a.getHospitalByCode(vo.getRegorg()).getName());
                //循环拼接写入居住地址
                vo.setAddress(areaService.getFullAddress(
                        vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                ));
            }
        }
        return new DataGrid<>(crcVoList.size(), crcVoList);
    }
    /**
     * 大肠癌阳性统计导出
     * @param
     * @param searchVo
     */

    /*public void exportDynamicData(HttpServletResponse response, @ModelAttribute SummarySearchVo searchVo){
        //参数设置
        CrcPositiveSummaryVo query = new CrcPositiveSummaryVo();
        //表头列
        List<String> headerList = new ArrayList();
        headerList.add("姓名");
        headerList.add("性别");
        headerList.add("出生日期");
        headerList.add("居住地址");
        headerList.add("移动电话");
        headerList.add("固定电话");
        headerList.add("筛查登记机构");
        headerList.add("危险度评估结果");
        headerList.add("GPT检查结果");
        headerList.add("HBsAg检查结果");
        headerList.add("AFP检查结果");
        headerList.add("肝脏B超检查结果");
        headerList.add("是否确诊");
        headerList.add("是否治疗");
        headerList.add("肿瘤位置");
        headerList.add("肿瘤期别");
        headerList.add("初筛日期");
}
*/


    /**
     * 初筛信息汇总（阳性肝癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<LicPositiveSummaryVo> getLicPositive(SummarySearchVo searchVo) {
        List<LicPositiveSummaryVo> licVoList = statisticsDao.getLicPositive(searchVo);
        if (licVoList != null && licVoList.size() > 0) {
            AuthUtils a = new AuthUtils();
            for (LicPositiveSummaryVo vo : licVoList) {
                if (vo != null) {
                    vo.setRegorg(a.getHospitalByCode(vo.getRegorg()).getName());

                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ));
                }
            }
        }
        return new DataGrid<>(licVoList.size(), licVoList);
    }

    /**
     * 初筛信息汇总（阳性肺癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<LucPositiveSummaryVo> getLucPositive(SummarySearchVo searchVo) {
        List<LucPositiveSummaryVo> lucVoList = statisticsDao.getLucPositive(searchVo);
        if (lucVoList != null && lucVoList.size() > 0) {
            AuthUtils a = new AuthUtils();
            for (LucPositiveSummaryVo vo : lucVoList) {
                if (vo != null) {
                    vo.setRegorg(a.getHospitalByCode(vo.getRegorg()).getName());

                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ));
                }
            }
        }
        return new DataGrid<>(lucVoList.size(), lucVoList);
    }

    /**
     * 初筛信息汇总（阳性胃癌）
     *
     * @param searchVo
     * @return
     */
    public DataGrid<ScPositiveSummaryVo> getScPositive(SummarySearchVo searchVo) {
        List<ScPositiveSummaryVo> scVoList = statisticsDao.getScPositive(searchVo);
        if (scVoList != null && scVoList.size() > 0) {
            AuthUtils a = new AuthUtils();
            for (ScPositiveSummaryVo vo : scVoList) {
                if (vo != null) {
                    vo.setRegorg(a.getHospitalByCode(vo.getRegorg()).getName());

                    vo.setAddress(areaService.getFullAddress(
                            vo.getAddressProvince(), vo.getAddressCity(), vo.getAddressCounty(), vo.getAddressTown(), vo.getAddressCommittee(), vo.getAddressDetail()
                    ));
                }
            }
        }
        return new DataGrid<>(scVoList.size(), scVoList);
    }


    /**
     * 诊断信息收集
     * @param searchVo
     * @return
     */
    public DataGrid<InformationCollectionVo> getInformation(SummarySearchVo searchVo) {
        List<InformationCollectionVo> scVoList = statisticsDao.getInformation(searchVo);
        if (scVoList != null && scVoList.size() > 0) {
            AuthUtils a = new AuthUtils();
            for (InformationCollectionVo vo : scVoList) {
                if (vo != null) {
                    vo.setRegorg(a.getHospitalByCode(vo.getRegorg()).getName());
                }
            }
        }
        return new DataGrid<>(scVoList.size(), scVoList);
    }
}
