package com.wonders.health.tumor.statistics.web;

import com.google.common.collect.Lists;
import com.wonders.health.tumor.closingcase.web.crcClosingCaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.statistics.service.StatisticsService;
import com.wonders.health.tumor.statistics.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 统计报表Controller
 * @Author: lxl
 * @CreateDate: 2019/5/31 10:15
 * @UpdateRemark:
 * @Version:
 */
@Controller
@RequestMapping("statistics")
public class StatisticsController {
    private static Logger logger = LoggerFactory.getLogger(crcClosingCaseController.class);

    @Autowired
    private StatisticsService statisticsService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${yearNum}")
    private  Integer yearNum;

    @Value("${crc_switch_flag}")
    private Integer crcFlag;

    @Value("${luc_switch_flag}")
    private Integer lucFlag;

    @Value("${lic_switch_flag}")
    private Integer licFlag;

    @Value("${sc_switch_flag}")
    private Integer scFlag;

    /**
     * 初筛信息汇总表（阴性）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:negative")
    @RequestMapping(value = "negative", method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");
        return "/statistics/negativeSummary";
    }

    @ResponseBody
    @RequestMapping(value = "negativeData")
    public DataGrid<NegativeSummaryVo> getNegative(@ModelAttribute  NegativeSearchVo searchVo){
        DataGrid<NegativeSummaryVo> dataGrid =  statisticsService.getNegative(searchVo);
        return dataGrid;
    }


    /**
     * 初筛信息汇总（阳性大肠癌）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:crcPositive")
    @RequestMapping(value = "crcPositive", method = RequestMethod.GET)
    public String crcList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");

        return "/statistics/crcPositiveSummary";
    }

    @ResponseBody
    @PostMapping("crcPositiveData")
    public DataGrid<CrcPositiveSummaryVo> getCrcPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<CrcPositiveSummaryVo> dataGrid =  statisticsService.getCrcPositive(searchVo);
        return dataGrid;
    }
    //导出
   /* @ResponseBody
    @RequestMapping("/exportCrcData")
    public void exportCrcData(HttpServletResponse response,@ModelAttribute SummarySearchVo searchVo){
    }*/

    /**
     * 初筛信息汇总（阳性肝癌）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:licPositive")
    @RequestMapping(value = "licPositive", method = RequestMethod.GET)
    public String licList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");

        return "/statistics/licPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("licPositiveData")
    public DataGrid<LicPositiveSummaryVo> getLicPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LicPositiveSummaryVo> dataGrid =  statisticsService.getLicPositive(searchVo);
        return dataGrid;
    }

    /**
     * 初筛信息汇总（阳性肺癌）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:lucPositive")
    @RequestMapping(value = "lucPositive", method = RequestMethod.GET)
    public String lucList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");

        return "/statistics/lucPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("lucPositiveData")
    public DataGrid<LucPositiveSummaryVo> getLucPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LucPositiveSummaryVo> dataGrid =  statisticsService.getLucPositive(searchVo);
        return dataGrid;
    }

    /**
     * 初筛信息汇总（阳性胃癌）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:scPositive")
    @RequestMapping(value = "scPositive", method = RequestMethod.GET)
    public String scList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");

        return "/statistics/scPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("scPositiveData")
    public DataGrid<ScPositiveSummaryVo> getScPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<ScPositiveSummaryVo> dataGrid =  statisticsService.getScPositive(searchVo);
        return dataGrid;
    }


    /**
     * 诊断信息收集
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:information")
    @RequestMapping(value = "information", method = RequestMethod.GET)
    public String infoList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear()+"01"+"01");
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("scFlag", scFlag);
        return "/statistics/informationCollection";
    }
    @ResponseBody
    @RequestMapping("informationData")
    public DataGrid<InformationCollectionVo> getInformation(@ModelAttribute InfomationSearchVo searchVo) {
        searchVo.setCrcFlag(crcFlag);
        searchVo.setLicFlag(licFlag);
        searchVo.setLucFlag(lucFlag);
        searchVo.setScFlag(scFlag);
        DataGrid<InformationCollectionVo> dataGrid =  statisticsService.getInformation(searchVo);
        return dataGrid;
    }

}
