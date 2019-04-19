package com.wonders.health.tumor.tumor.web;


import com.google.common.collect.Lists;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.entity.*;
import com.wonders.health.tumor.tumor.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 新增筛查登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("screening")
public class ScreeningController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ScreeningController.class);

    @Value("${crc_switch_flag}")
    private String crcFlag;

    @Value("${lic_switch_flag}")
    private String licFlag;

    @Value("${sc_switch_flag}")
    private String scFlag;

    @Value("${luc_switch_flag}")
    private String lucFlag;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private CrcRegcaseService crcRegcaseService;
    @Autowired
    private LicRegcaseService licRegcaseService;
    @Autowired
    private LucRegcaseService lucRegcaseService;
    @Autowired
    private ScRegcaseService scRegcaseService;

    @Autowired
    private CrcRiskAssessmentService crcRiskAssessmentService;
    @Autowired
    private LicRiskAssessmentService licRiskAssessmentService;
    @Autowired
    private LucRiskAssessmentService lucRiskAssessmentService;
    @Autowired
    private ScRiskAssessmentService scRiskAssessmentService;



    @RequestMapping(value = {"", "form"}, method = RequestMethod.GET)
    public String form(Model model, String manageId, String checkYear) {

        List<DataOption> years = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            DataOption option = new DataOption();
            String year = DateUtils.formatDate(DateUtils.addYears(new Date(),i), "yyyy");
            option.setId(year);
            option.setText(year);
            years.add(option);
        }
        model.addAttribute("years", AuthUtils.toJson(years));
        model.addAttribute("checkYear", checkYear);

        //个人管理编号
        if (StringUtils.isBlank(manageId)) {
            CancerPersonInfo personInfo = new CancerPersonInfo();
            personInfo.setRegdoc(getSessionUser().getId());
            personInfo.setRegorg(getSessionUser().getOrgCode());
            personInfo.setRegdate(new Date());

            model.addAttribute("personInfo", personInfo);
            model.addAttribute("lucRisk", new LucRiskAssessment());
            model.addAttribute("scRisk", new ScRiskAssessment());
            model.addAttribute("crcRisk", new CrcRiskAssessment());
            model.addAttribute("licRisk", new LicRiskAssessment());
            model.addAttribute("idNumber", "");
            model.addAttribute("flag", "1"); //1：新增
        } else {
            model.addAttribute("flag", "2"); //2：修改
        }

        //TODO
        //各癌症数据库存在标志
        model.addAttribute("crcDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("licDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("scDbflag", "1");  //数据库状态  1：新增 2：修改
        model.addAttribute("lucDbflag", "1"); //数据库状态  1：新增 2：修改

        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("lucFlag", lucFlag);
        return "/register/form";
    }

    @RequestMapping(value = {"", "getBaseData"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxReturn getBaseData( String type, String personcard){
        //个人基本信息
        Optional<CancerPersonInfo> cancerPersonInfo=Optional.of(screeningService.getBaseInfoByCardnoAndType(personcard,type));
        if(cancerPersonInfo.isPresent()){
            return new AjaxReturn(true,"",cancerPersonInfo.get());
        }else{
            logger.error("当前证件号码查不到任何信息");
            return new AjaxReturn(false,"当前证件号码查不到任何信息");
        }
    }


}
