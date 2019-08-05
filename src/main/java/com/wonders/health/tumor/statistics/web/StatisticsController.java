package com.wonders.health.tumor.statistics.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.JsonArray;
import com.wonders.health.tumor.closingcase.web.CrcClosingCaseController;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.tags.DictData;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.excel.ExcelUtils;
import com.wonders.health.tumor.statistics.service.StatisticsService;
import com.wonders.health.tumor.statistics.vo.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
    private static Logger logger = LoggerFactory.getLogger(CrcClosingCaseController.class);

    @Autowired
    private StatisticsService statisticsService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${yearNum}")
    private Integer yearNum;

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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        return "/statistics/negativeSummary";
    }

    @ResponseBody
    @RequestMapping(value = "negativeData")
    public DataGrid<NegativeSummaryVo> getNegative(@ModelAttribute NegativeSearchVo searchVo) {
        Integer count = 0;
        if (searchVo.getCrc()) {
            count++;
        }
        if (searchVo.getLic()) {
            count++;
        }
        if (searchVo.getLuc()) {
            count++;
        }
        if (searchVo.getSc()) {
            count++;
        }
        searchVo.setCount(count);
        DataGrid<NegativeSummaryVo> dataGrid = statisticsService.getNegative(searchVo);
        return dataGrid;
    }

    /**
     * 日期转换
     */
    public  static  Date foramt(String  str){
        Date date = null;
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 初筛信息汇总表（阴性）导出
     * @param regarea
     * @param regorg
     * @param csrqStart
     * @param csrqEnd
     * @param response
     */
    @ResponseBody
    @RequestMapping("/exportNegativeData")
    public void exportNegativeData(   @RequestParam(required = false) String regarea,
                                      @RequestParam(required = false) String regorg,
                                      @RequestParam(required = false) String csrqStart,
                                      @RequestParam(required = false) String csrqEnd,
                                      @RequestParam(required = false) boolean crc,
                                      @RequestParam(required = false) boolean lic,
                                      @RequestParam(required = false) boolean luc,
                                      @RequestParam(required = false) boolean sc,
                                      @RequestParam(required = false) int pageIndex,
                                      @RequestParam(required = false) int pageSize,
                                      HttpServletResponse response) {
        NegativeSearchVo searchVo = new NegativeSearchVo();
        Integer count = 0;
        if (crc==true) {
            count++;
        }
        if (lic==true) {
            count++;
        }
        if (luc==true) {
            count++;
        }
        if (sc==true) {
            count++;
        }
        if(count==0){
            throw new RuntimeException("必须选择一个复选框");
        }else{
            searchVo.setCount(count);
        }
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setCrc(crc);
        searchVo.setLic(lic);
        searchVo.setLuc(luc);
        searchVo.setSc(sc);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);

        DataGrid<NegativeSummaryVo> list = statisticsService.getNegative(searchVo);
        DictData dic = new DictData();
        List<ExportNegative> datas = list.getData().stream().map(vo -> {
            ExportNegative negative = new ExportNegative();
            negative.setXm(vo.getXm());
            negative.setXb(dic.generalName("60023",vo.getXb()));
            negative.setAddress(vo.getAddress());
            negative.setBirthday(vo.getBirthday());
            negative.setMobile(vo.getMobile());
            negative.setTelephone(vo.getTelephone());
            negative.setRegorg(vo.getRegorg());
            negative.setCsrq(vo.getCsrq());
            return negative;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("初筛信息汇总表（阴性）导出异常", e);
        }
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        return "/statistics/crcPositiveSummary";
    }

    @ResponseBody
    @PostMapping("crcPositiveData")
    public DataGrid<CrcPositiveSummaryVo> getCrcPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<CrcPositiveSummaryVo> dataGrid = statisticsService.getCrcPositive(searchVo);
        return dataGrid;
    }

    /**
     * 大肠癌阳性导出
     * @param regarea
     * @param regorg
     * @param csrqStart
     * @param csrqEnd
     * @param response
     */
    @ResponseBody
    @RequestMapping("/exportCrcPositiveData")
    public void exportCrcPositiveData(@RequestParam(required = false) String regarea,
                                      @RequestParam(required = false) String regorg,
                                      @RequestParam(required = false) String csrqStart,
                                      @RequestParam(required = false) String csrqEnd,
                                      @RequestParam(required = false) int pageIndex,
                                      @RequestParam(required = false) int pageSize,
                                      HttpServletResponse response) {
        SummarySearchVo searchVo = new SummarySearchVo();
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);

        DataGrid<CrcPositiveSummaryVo> list = statisticsService.getCrcPositive(searchVo);
        DictData dic = new DictData();
        List<ExportCrcPositive> datas = list.getData().stream().map(vo -> {
            ExportCrcPositive exportCrcPositive = new ExportCrcPositive();
            exportCrcPositive.setXm(vo.getXm());
            exportCrcPositive.setXb(dic.generalName("60023",vo.getXb()));
            exportCrcPositive.setBirthday(vo.getBirthday());
            exportCrcPositive.setAddress(vo.getAddress());
            exportCrcPositive.setMobile(vo.getMobile());
            exportCrcPositive.setTelephone(vo.getTelephone());
            exportCrcPositive.setRegorg(vo.getRegorg());
            exportCrcPositive.setAssessmentResult(dic.generalName("60001",vo.getAssessmentResult()));
            exportCrcPositive.setFobtResult(dic.generalName("60001",vo.getFobtResult()));
            exportCrcPositive.setChangjing(dic.generalName("60013",vo.getChangjing()));
            exportCrcPositive.setSfqz(dic.generalName("60013",vo.getSfqz()));
            exportCrcPositive.setSfzl(dic.generalName("60013",vo.getSfzl()));
            exportCrcPositive.setZlwz(dic.generalName("60031",vo.getZlwz()));
            exportCrcPositive.setZlqb(dic.generalName("60051",vo.getZlqb()));
            exportCrcPositive.setCsrq(vo.getCsrq());
            return exportCrcPositive;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("大肠癌阳性导出异常", e);
        }
    }



    /**
     * 初筛信息汇总（阳性肝癌）
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:licPositive")
    @RequestMapping(value = "licPositive", method = RequestMethod.GET)
    public String licList(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());

        return "/statistics/licPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("licPositiveData")
    public DataGrid<LicPositiveSummaryVo> getLicPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LicPositiveSummaryVo> dataGrid = statisticsService.getLicPositive(searchVo);
        return dataGrid;
    }

    /**
     * 肝癌导出
     * @param regarea
     * @param regorg
     * @param csrqStart
     * @param csrqEnd
     * @param response
     */
    @ResponseBody
    @RequestMapping("/exportLicPositiveData")
    public void exportLicPositiveData(@RequestParam(required = false) String regarea,
                                      @RequestParam(required = false) String regorg,
                                      @RequestParam(required = false) String csrqStart,
                                      @RequestParam(required = false) String csrqEnd,
                                      @RequestParam(required = false) int pageIndex,
                                      @RequestParam(required = false) int pageSize,
                                      HttpServletResponse response) {

        SummarySearchVo searchVo = new SummarySearchVo();
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);

        DataGrid<LicPositiveSummaryVo> list = statisticsService.getLicPositive(searchVo);
        DictData dic = new DictData();
        List<ExportLicPositive> datas = list.getData().stream().map(vo -> {
            ExportLicPositive licPositive = new ExportLicPositive();
            licPositive.setXm(vo.getXm());
            licPositive.setXb(dic.generalName("60023",vo.getXb()));
            licPositive.setBirthday(vo.getBirthday());
            licPositive.setAddress(vo.getAddress());
            licPositive.setMobile(vo.getMobile());
            licPositive.setTelephone(vo.getTelephone());
            licPositive.setRegorg(vo.getRegorg());
            licPositive.setAssessmentResult(dic.generalName("60001",vo.getAssessmentResult()));
            licPositive.setAFP(vo.getAFP());
            licPositive.setGTP(vo.getGTP());
            licPositive.setHbeg(vo.getHbeg());
            licPositive.setBus(dic.generalName("60001",vo.getBus()));
            licPositive.setSfqz(dic.generalName("60013",vo.getSfqz()));
            licPositive.setSfzl(dic.generalName("60013",vo.getSfzl()));
            licPositive.setZlwz(vo.getZlwz());
            licPositive.setZlqb(dic.generalName("60051",vo.getZlqb()));
            licPositive.setCsrq(vo.getCsrq());
            return licPositive;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("肝癌阳性导出异常", e);
        }
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        return "/statistics/lucPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("lucPositiveData")
    public DataGrid<LucPositiveSummaryVo> getLucPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LucPositiveSummaryVo> dataGrid = statisticsService.getLucPositive(searchVo);
        return dataGrid;
    }

    /**
     * 肺癌导出
     * @param regarea
     * @param regorg
     * @param csrqStart
     * @param csrqEnd
     * @param response
     */
    @ResponseBody
    @RequestMapping("/exportLucPositiveData")
    public void exportLucPositiveData(@RequestParam(required = false) String regarea,
                                      @RequestParam(required = false) String regorg,
                                      @RequestParam(required = false) String csrqStart,
                                      @RequestParam(required = false) String csrqEnd,
                                      @RequestParam(required = false) int pageIndex,
                                      @RequestParam(required = false) int pageSize,
                                      HttpServletResponse response) {
        SummarySearchVo searchVo = new SummarySearchVo();
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);

        DataGrid<LucPositiveSummaryVo> list = statisticsService.getLucPositive(searchVo);
        DictData dic = new DictData();
        List<ExportLucPositive> datas = list.getData().stream().map(vo -> {
            ExportLucPositive lucPositive = new ExportLucPositive();
            lucPositive.setXm(vo.getXm());
            lucPositive.setXb(dic.generalName("60023",vo.getXb()));
            lucPositive.setBirthday(vo.getBirthday());
            lucPositive.setAddress(vo.getAddress());
            lucPositive.setMobile(vo.getMobile());
            lucPositive.setTelephone(vo.getTelephone());
            lucPositive.setRegorg(vo.getRegorg());
            lucPositive.setAssessmentResult(dic.generalName("60001",vo.getAssessmentResult()));
            lucPositive.setShifouLDCT(dic.generalName("60013",vo.getShifouLDCT()));
            lucPositive.setLDCTJieguo(vo.getLDCTJieguo());
            lucPositive.setSfqz(dic.generalName("60013",vo.getSfqz()));
            lucPositive.setSfzl(dic.generalName("60013",vo.getSfzl()));
            lucPositive.setZlwz(vo.getZlwz());
            lucPositive.setZlqb(dic.generalName("60051",vo.getZlqb()));
            lucPositive.setCsrq(vo.getCsrq());
            return lucPositive;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("肺癌阳性导出异常", e);
        }
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());

        return "/statistics/scPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("scPositiveData")
    public DataGrid<ScPositiveSummaryVo> getScPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<ScPositiveSummaryVo> dataGrid = statisticsService.getScPositive(searchVo);
        return dataGrid;
    }


    @ResponseBody
    @RequestMapping("/exportScPositiveData")
    public void exportScPositiveData(@RequestParam(required = false) String regarea,
                                      @RequestParam(required = false) String regorg,
                                      @RequestParam(required = false) String csrqStart,
                                      @RequestParam(required = false) String csrqEnd,
                                     @RequestParam(required = false) int pageIndex,
                                     @RequestParam(required = false) int pageSize,
                                     HttpServletResponse response) {
        SummarySearchVo searchVo = new SummarySearchVo();
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);

        DataGrid<ScPositiveSummaryVo> list = statisticsService.getScPositive(searchVo);
        DictData dic = new DictData();
        List<ExportScPositive> datas = list.getData().stream().map(vo -> {
            ExportScPositive scPositive = new ExportScPositive();
            scPositive.setXm(vo.getXm());
            scPositive.setXb(dic.generalName("60023",vo.getXb()));
            scPositive.setBirthday(vo.getBirthday());
            scPositive.setAddress(vo.getAddress());
            scPositive.setMobile(vo.getMobile());
            scPositive.setTelephone(vo.getTelephone());
            scPositive.setRegorg(vo.getRegorg());
            scPositive.setAssessmentResult(dic.generalName("60001",vo.getAssessmentResult()));
            scPositive.setSfwj(dic.generalName("60013",vo.getSfwj()));
            scPositive.setWjjcjg(vo.getWjjcjg());
            scPositive.setSfqz(dic.generalName("60013",vo.getSfqz()));
            scPositive.setSfzl(dic.generalName("60013",vo.getSfzl()));
            scPositive.setZlwz(vo.getZlwz());
            scPositive.setZlqb(dic.generalName("60051",vo.getZlqb()));
            scPositive.setCsrq(vo.getCsrq());
            return scPositive;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("胃癌阳性导出异常", e);
        }
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        return "/statistics/informationCollection";
    }
    @ResponseBody
    @RequestMapping("informationData")
    public DataGrid<InformationCollectionVo> getInformation(@ModelAttribute InfomationSearchVo searchVo) {
        searchVo.setCrcFlag(crcFlag);
        searchVo.setLicFlag(licFlag);
        searchVo.setLucFlag(lucFlag);
        searchVo.setScFlag(scFlag);
        DataGrid<InformationCollectionVo> dataGrid = statisticsService.getInformation(searchVo);
        return dataGrid;
    }

    /**
     * 诊断信息收集统计导出
     */

    @ResponseBody

    @RequestMapping("/exportInformationData")
    public void exportDynamicData2(@RequestParam(required = false) String regarea,
                                   @RequestParam(required = false) String regorg,
                                   @RequestParam(required = false) String csrqStart,
                                   @RequestParam(required = false) String csrqEnd,
                                   @RequestParam(required = false) int pageIndex,
                                   @RequestParam(required = false) int pageSize,
                                   HttpServletResponse response) {
        InfomationSearchVo searchVo = new InfomationSearchVo();
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setCrcFlag(crcFlag);
        searchVo.setLicFlag(licFlag);
        searchVo.setLucFlag(lucFlag);
        searchVo.setScFlag(scFlag);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);
        DataGrid<InformationCollectionVo> list = statisticsService.getInformation(searchVo);
        DictData dic = new DictData();
        List<DiagnoseMessage> datas = list.getData().stream().map(vo -> {
            DiagnoseMessage diagnoseMessage = new DiagnoseMessage();
            diagnoseMessage.setXm(vo.getXm());
            diagnoseMessage.setPersoncardType(dic.generalName("60022", vo.getPersoncardType()));//证件类型
            diagnoseMessage.setPersoncard(vo.getPersoncard());
            diagnoseMessage.setRegorg(vo.getRegorg());
            diagnoseMessage.setJcxm(vo.getJcxm());
            diagnoseMessage.setBbbw(vo.getBbbw());
            diagnoseMessage.setZldx(vo.getZldx());
            diagnoseMessage.setSfhj(dic.generalName("60013", vo.getSfhj()));//是否活检
            diagnoseMessage.setHjjg(vo.getHjjg());//活检结果
            diagnoseMessage.setSfbl(dic.generalName("60013", vo.getSfbl()));//是否病理
            diagnoseMessage.setBllx(vo.getBllx());
            diagnoseMessage.setTNMfq(vo.getTNMfq().replaceAll("null",""));
            diagnoseMessage.setSfrq(vo.getSfrq());
            return diagnoseMessage;
        }).collect(Collectors.toList());
        if (datas == null || datas.size() == 0) {
            return;
        }
        try {
            ExcelUtils.exportExcel(response, datas);
        } catch (Exception e) {
            logger.error("诊断信息收集导出异常", e);
        }
    }


    /**
     * 社区卫生服务中心肿瘤早发现进度表(社区登录的场合)
     * @Author: hushunxiong
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:seSchedule")
    @RequestMapping(value = "seSchedule", method = RequestMethod.GET)
    public String seList(Model model) {
        model.addAttribute("year",DateUtils.getYear());
        model.addAttribute("flag", 1);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        return "/statistics/seScheduleList";
    }
    @ResponseBody
    @RequestMapping("seData")
    public DataGrid<SeScheduleListVo> getSchedule(@ModelAttribute SeScheduleSearchVo searchVo) {
        DataGrid<SeScheduleListVo> seScheduleList=statisticsService.getSeSearch(searchVo);
        return seScheduleList;
    }

    /**
     * 社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出
     */

    @ResponseBody
    @RequestMapping("/exportSecheduleData")
    public void exportSechedule(@RequestParam(required = false) String regorg,
                                   @RequestParam(required = false) String year,
                                   @RequestParam(required = false) String summary,
                                   @RequestParam(required = false) int pageIndex,
                                   @RequestParam(required = false) int pageSize,
                                   HttpServletResponse response) {
        SeScheduleSearchVo searchVo = new SeScheduleSearchVo();
        searchVo.setYear(year);
        searchVo.setRegorg(regorg);
        searchVo.setSummary(summary);
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);
        DataGrid<SeScheduleListVo> list = statisticsService.getSeSearch(searchVo);
        // 大肠癌
        if("1".equals(searchVo.getSummary())){
            List<ExportCrcSchedule> datas = list.getData().stream().map(vo -> {
                    ExportCrcSchedule exportCrcSchedule = new ExportCrcSchedule();
                    exportCrcSchedule.setYf(vo.getMonth());
                    exportCrcSchedule.setDjrs(vo.getNum());
                    exportCrcSchedule.setWcscrs(vo.getSummary());
                    exportCrcSchedule.setScyxs(vo.getRegCaseNum());
                    exportCrcSchedule.setScyxl(vo.getRegCaseRate());
                    exportCrcSchedule.setCjjcs(vo.getCrcCheckNum());
                    exportCrcSchedule.setCjjcl(vo.getCrcCheckRate());
                    exportCrcSchedule.setAqqbbs(vo.getCrcLesionNum());
                    exportCrcSchedule.setAqqbbl(vo.getCrcLesionRate());
                    exportCrcSchedule.setDcabls(vo.getCrcCaseNum());
                    exportCrcSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                    exportCrcSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                    return exportCrcSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出异常", e);
            }
        }
        // 肝癌
        if("2".equals(searchVo.getSummary())){
            List<ExportLicSchedule> datas = list.getData().stream().map(vo -> {
                ExportLicSchedule exportLicSchedule = new ExportLicSchedule();
                exportLicSchedule.setYf(vo.getMonth());
                exportLicSchedule.setDjrs(vo.getNum());
                exportLicSchedule.setWcscrs(vo.getSummary());
                exportLicSchedule.setScyxs(vo.getRegCaseNum());
                exportLicSchedule.setScyxl(vo.getRegCaseRate());
                exportLicSchedule.setGabls(vo.getLicCaseNum());
                exportLicSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportLicSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportLicSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出异常", e);
            }
        }
        // 肺癌
        if("3".equals(searchVo.getSummary())){
            List<ExportLucSchedule> datas = list.getData().stream().map(vo -> {
                ExportLucSchedule exportLucSchedule = new ExportLucSchedule();
                exportLucSchedule.setYf(vo.getMonth());
                exportLucSchedule.setDjrs(vo.getNum());
                exportLucSchedule.setWcscrs(vo.getSummary());
                exportLucSchedule.setScyxs(vo.getRegCaseNum());
                exportLucSchedule.setScyxl(vo.getRegCaseRate());
                exportLucSchedule.setCtjcs(vo.getLucCheckNum());
                exportLucSchedule.setCtjcl(vo.getLucCheckRate());
                exportLucSchedule.setFabls(vo.getLucCaseNum());
                exportLucSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportLucSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportLucSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出异常", e);
            }
        }
        // 胃癌
        if("4".equals(searchVo.getSummary())){
            List<ExportScSchedule> datas = list.getData().stream().map(vo -> {
                ExportScSchedule exportScSchedule = new ExportScSchedule();
                exportScSchedule.setYf(vo.getMonth());
                exportScSchedule.setDjrs(vo.getNum());
                exportScSchedule.setWcscrs(vo.getSummary());
                exportScSchedule.setScyxs(vo.getRegCaseNum());
                exportScSchedule.setScyxl(vo.getRegCaseRate());
                exportScSchedule.setWjjcs(vo.getScCheckNum());
                exportScSchedule.setWjjcl(vo.getScCheckRate());
                exportScSchedule.setWabls(vo.getScCaseNum());
                exportScSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportScSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportScSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(社区登录的场合)导出异常", e);
            }
        }
    }

    /**
     * 社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)
     * @Author: hushunxiong
     * @param model
     * @return
     */
    @RequiresPermissions("statistics:seAreaControl")
    @RequestMapping(value = "seAreaControl", method = RequestMethod.GET)
    public String seAreaControlList(Model model) {
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("orgCode", AuthUtils.getUser().getOrgCode());
        model.addAttribute("flag", 1);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01");
        return "/statistics/seAreaControlList";
    }
    @ResponseBody
    @RequestMapping("seAreaControlData")
    public DataGrid<SeScheduleListVo> getAreaControl(@ModelAttribute SeScheduleSearchVo searchVo) {
        DataGrid<SeScheduleListVo> seAreaControlList=statisticsService.getSeAreaControl(searchVo);
        return seAreaControlList;
    }

    /**
     * 社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出
     */

    @ResponseBody

    @RequestMapping("/exportAreaControlData")
    public void exportSeAreaControl(@RequestParam(required = false) String regorg,
                                    @RequestParam(required = false) String summary,
                                @RequestParam(required = false) String csrqStart,
                                @RequestParam(required = false) String csrqEnd,
                                @RequestParam(required = false) int pageIndex,
                                @RequestParam(required = false) int pageSize,
                                HttpServletResponse response) {
        SeScheduleSearchVo searchVo = new SeScheduleSearchVo();
        searchVo.setRegorg(regorg);
        searchVo.setSummary(summary);
        searchVo.setCsrqStart(foramt(csrqStart));
        searchVo.setCsrqEnd(foramt(csrqEnd));
        searchVo.setPageIndex(pageIndex);
        searchVo.setPageSize(pageSize);
        DataGrid<SeScheduleListVo> list = statisticsService.getSeAreaControl(searchVo);
        // 大肠癌
        if("1".equals(searchVo.getSummary())){
            List<ExportCrcSeAreaControl> datas = list.getData().stream().map(vo -> {
                ExportCrcSeAreaControl exportCrcSchedule = new ExportCrcSeAreaControl();
                exportCrcSchedule.setJgmc(vo.getRegorg());
                exportCrcSchedule.setDjrs(vo.getNum());
                exportCrcSchedule.setWcscrs(vo.getSummary());
                exportCrcSchedule.setScyxs(vo.getRegCaseNum());
                exportCrcSchedule.setScyxl(vo.getRegCaseRate());
                exportCrcSchedule.setCjjcs(vo.getCrcCheckNum());
                exportCrcSchedule.setCjjcl(vo.getCrcCheckRate());
                exportCrcSchedule.setAqqbbs(vo.getCrcLesionNum());
                exportCrcSchedule.setAqqbbl(vo.getCrcLesionRate());
                exportCrcSchedule.setDcabls(vo.getCrcCaseNum());
                exportCrcSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportCrcSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportCrcSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出异常", e);
            }
        }
        // 肝癌
        if("2".equals(searchVo.getSummary())){
            List<ExportLicSeAreaControl> datas = list.getData().stream().map(vo -> {
                ExportLicSeAreaControl exportLicSchedule = new ExportLicSeAreaControl();
                exportLicSchedule.setJgmc(vo.getRegorg());
                exportLicSchedule.setDjrs(vo.getNum());
                exportLicSchedule.setWcscrs(vo.getSummary());
                exportLicSchedule.setScyxs(vo.getRegCaseNum());
                exportLicSchedule.setScyxl(vo.getRegCaseRate());
                exportLicSchedule.setGabls(vo.getLicCaseNum());
                exportLicSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportLicSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportLicSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出异常", e);
            }
        }
        // 肺癌
        if("3".equals(searchVo.getSummary())){
            List<ExportLucSeAreaControl> datas = list.getData().stream().map(vo -> {
                ExportLucSeAreaControl exportLucSchedule = new ExportLucSeAreaControl();
                exportLucSchedule.setJgmc(vo.getRegorg());
                exportLucSchedule.setDjrs(vo.getNum());
                exportLucSchedule.setWcscrs(vo.getSummary());
                exportLucSchedule.setScyxs(vo.getRegCaseNum());
                exportLucSchedule.setScyxl(vo.getRegCaseRate());
                exportLucSchedule.setCtjcs(vo.getLucCheckNum());
                exportLucSchedule.setCtjcl(vo.getLucCheckRate());
                exportLucSchedule.setFabls(vo.getLucCaseNum());
                exportLucSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportLucSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportLucSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出异常", e);
            }
        }
        // 胃癌
        if("4".equals(searchVo.getSummary())){
            List<ExportScSeAreaControl> datas = list.getData().stream().map(vo -> {
                ExportScSeAreaControl exportScSchedule = new ExportScSeAreaControl();
                exportScSchedule.setJgmc(vo.getRegorg());
                exportScSchedule.setDjrs(vo.getNum());
                exportScSchedule.setWcscrs(vo.getSummary());
                exportScSchedule.setScyxs(vo.getRegCaseNum());
                exportScSchedule.setScyxl(vo.getRegCaseRate());
                exportScSchedule.setWjjcs(vo.getScCheckNum());
                exportScSchedule.setWjjcl(vo.getScCheckRate());
                exportScSchedule.setWabls(vo.getScCaseNum());
                exportScSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportScSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportScSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出异常", e);
            }
        }
        // 不分类
        if("5".equals(searchVo.getSummary())){
            List<ExportNoCategoreSeAreaControl> datas = list.getData().stream().map(vo -> {
                ExportNoCategoreSeAreaControl exportScSchedule = new ExportNoCategoreSeAreaControl();
                exportScSchedule.setJgmc(vo.getRegorg());
                exportScSchedule.setDjrs(vo.getNum());
                exportScSchedule.setWcscrs(vo.getSummary());
                exportScSchedule.setScyxs(vo.getRegCaseNum());
                exportScSchedule.setScyxl(vo.getRegCaseRate());
                exportScSchedule.setQzjcs(vo.getConfirmeNum());
                exportScSchedule.setQzjcl(vo.getConfirmeRate());
                exportScSchedule.setQzabls(vo.getConfirmeCancerNum());
                exportScSchedule.setQzzqs(vo.getEarlyDiagnosisNum());
                exportScSchedule.setQzzql(vo.getEarlyDiagnosisRate());
                return exportScSchedule;
            }).collect(Collectors.toList());
            if (datas == null || datas.size() == 0) {
                return;
            }
            try {
                ExcelUtils.exportExcel(response, datas);
            } catch (Exception e) {
                logger.error("社区卫生服务中心肿瘤早发现进度表(区疾控登录的场合)导出异常", e);
            }
        }

    }

}
