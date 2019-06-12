package com.wonders.health.tumor.statistics.web;

import com.wonders.health.tumor.closingcase.web.crcClosingCaseController;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.tags.DictData;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.statistics.vo.DiagnoseMessage;
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

import javax.servlet.http.HttpServletResponse;
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
    private static Logger logger = LoggerFactory.getLogger(crcClosingCaseController.class);

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

        return "/statistics/crcPositiveSummary";
    }

    @ResponseBody
    @PostMapping("crcPositiveData")
    public DataGrid<CrcPositiveSummaryVo> getCrcPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<CrcPositiveSummaryVo> dataGrid = statisticsService.getCrcPositive(searchVo);
        return dataGrid;
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

        return "/statistics/licPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("licPositiveData")
    public DataGrid<LicPositiveSummaryVo> getLicPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LicPositiveSummaryVo> dataGrid = statisticsService.getLicPositive(searchVo);
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");

        return "/statistics/lucPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("lucPositiveData")
    public DataGrid<LucPositiveSummaryVo> getLucPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<LucPositiveSummaryVo> dataGrid = statisticsService.getLucPositive(searchVo);
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
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("tjRqStart", DateUtils.getYear() + "01" + "01");

        return "/statistics/scPositiveSummary";
    }
    @ResponseBody
    @RequestMapping("scPositiveData")
    public DataGrid<ScPositiveSummaryVo> getScPositive(@ModelAttribute SummarySearchVo searchVo) {
        DataGrid<ScPositiveSummaryVo> dataGrid = statisticsService.getScPositive(searchVo);
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
                                   @RequestParam(required = false) Date csrqStart,
                                   @RequestParam(required = false) Date csrqEnd, HttpServletResponse response) {
        InfomationSearchVo searchVo = new InfomationSearchVo();
        searchVo.setCsrqStart(csrqStart);
        searchVo.setCsrqEnd(csrqEnd);
        searchVo.setRegarea(regarea);
        searchVo.setRegorg(regorg);
        searchVo.setCrcFlag(crcFlag);
        searchVo.setLicFlag(licFlag);
        searchVo.setLucFlag(lucFlag);
        searchVo.setScFlag(scFlag);
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
            diagnoseMessage.setSfhj(dic.generalName("60031", vo.getSfhj()));//是否活检
            diagnoseMessage.setHjjg(dic.generalName("60033", vo.getHjjg()));//活检结果
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
        } catch (Exception e) {//todo 这个异常处理log
            e.printStackTrace();
        }
    }

}
