/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.health.auth.client.vo.Hospital;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;

import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
import com.wonders.health.tumor.tumor.service.XkyyRegisterService;
import com.wonders.health.tumor.tumor.service.XkyyReserveService;
import com.wonders.health.tumor.tumor.vo.CancerPersonInfoSearchVo;
import com.wonders.health.tumor.tumor.service.CancerPersonInfoService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * 初筛对象信息Controller
 * @author zhaomeng
 */
@Controller
@RequestMapping("cancerPersonInfo")
public class CancerPersonInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CancerPersonInfoController.class);

    @Autowired
    private CancerPersonInfoService cancerPersonInfoService;

    @Autowired
    private XkyyReserveService xkyyReserveService;

    @Autowired
    private AuthServiceI authServiceI;

    @Value("${area_code}")
    private String areaCode;

    @Value("${shiroServerUrlPrefix}")
    private String prefix;

    @Value("${crc_switch_flag}")
    private  Integer crcFlag;

    @Value("${luc_switch_flag}")
    private  Integer lucFlag;

    @Value("${lic_switch_flag}")
    private  Integer licFlag;

    @Value("${sc_switch_flag}")
    private  Integer scFlag;

    @Value("${yearNum}")
    private  Integer yearNum;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model, String kp) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("orgCode", getSessionUser().getOrgCode());
        String baseUrl = authServiceI.findBaseUrl("肿瘤早发现", getSessionUser().getOrgCode());

        if (StringUtils.equals(prefix, baseUrl)){
            model.addAttribute("ip", "");
        } else {
            model.addAttribute("ip", baseUrl);
        }
        model.addAttribute("kp", kp);

        return "/register/cancerPersonInfoList";
    }

    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerPersonInfo> data(CancerPersonInfoSearchVo search) {
        search.setCrcFlag(crcFlag.toString());
        search.setLucFlag(lucFlag.toString());
        search.setLicFlag(licFlag.toString());
        search.setScFlag(scFlag.toString());
        DataGrid<CancerPersonInfo> grid = cancerPersonInfoService.findPage(search);
        return grid;
    }


    @RequestMapping(value = {"", "form"}, method = RequestMethod.GET)
    public String form(Model model, String id,String csnf) {
        if (StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(csnf)) {
            CancerPersonInfo cancerPersonInfo = cancerPersonInfoService.findByInfoId(id,csnf);
            if(cancerPersonInfo!=null){
                cancerPersonInfo.setCsnf(csnf);
            }
            model.addAttribute("vo", cancerPersonInfo);
        }
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        return "/register/cancerPersonInfoForm";
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid CancerPersonInfo cancerPersonInfo, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return cancerPersonInfoService.saveOrUpdate(cancerPersonInfo, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                cancerPersonInfoService.deleteById(id);
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
                logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }

    /**
     * 初筛信息删除
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delRecords", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delRecords(CancerPersonInfo info) {
        if (info != null) {
            try {
                cancerPersonInfoService.deleteByRegcaseId(info);
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
                logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入参数不能为空");
        }
    }

    /**
     * 徐汇肺癌LDCT预约记录表新增
     * @param manageId
     * @param checkCode
     * @param checkDate
     * @param checkTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertAppCheck", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> insertAppCheck(String manageId, String checkCode, String checkDate, String checkTime) {
        try {
            cancerPersonInfoService.insertAppCheck(manageId, checkCode, checkDate, checkTime, getSessionUser());
            return new AjaxReturn<String>(true, "预约成功");
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<String>(false, "预约异常");
        }
    }

    /**
     * 徐汇肺癌LDCT预约记录取消
     * @param patid
     * @param checkCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelReserve", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> cancelReserve(String ldctid,String patid, String checkCode) {
        try {
            //boolean flag = true;
            boolean flag = xkyyReserveService.cancelReserve(patid, checkCode);
            if (flag) {
                cancerPersonInfoService.deleteAppCheck(ldctid, getSessionUser());
                return new AjaxReturn<String>(true, "取消预约记录成功");
            } else {
                return new AjaxReturn<String>(false, "取消预约记录异常");
            }
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<String>(false, "取消预约记录异常");
        }
    }

    @RequestMapping(value = "printReserve", method = RequestMethod.GET)
    public ModelAndView printReserve(Model model, String id) {
        ModelAndView mav = new ModelAndView("/register/printReserve");

        LucAppLdctXh ldct = cancerPersonInfoService.getLdctInfo(id);
        CancerPersonInfo personInfo = cancerPersonInfoService.findById(id);
        String checkDate = DateUtils.formatDate(ldct.getAppCheckDate(),"yyyy年MM月dd日");
        String checkTime = "";
        String time = ldct.getAppCheckTime();
        if (StringUtils.isNotBlank(time)) {
            int hour = Integer.parseInt(time.substring(0,2));
            if (hour >=12) {
                checkTime = "下午" + time;
            } else {
                checkTime = "上午" + time;
            }
        }
        String orgName = "";
        String orgCode = getSessionUser().getOrgCode();
        if (StringUtils.isNotBlank(orgCode)) {
            Hospital hospital = AuthUtils.getHospitalByCode(orgCode);
            if (hospital != null) {
                orgName = hospital.getName();
            }
        }

        model.addAttribute("name",personInfo.getName());
        model.addAttribute("yyPeriod","目前已为您预约好低剂量螺旋CT检查，请您于" + checkDate + checkTime +"至胸科医院（具体地点请胸科医院提供）做检查。检查当天请携带身份证、医保卡与本预约单，需要医保承担低剂量螺旋CT检查的费用。");
        model.addAttribute("thirdStep","3." + checkDate + "至1号楼取检查报告");
        model.addAttribute("hospitalName", orgName);

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("month",LocalDate.now().getMonthValue());
        model.addAttribute("day",LocalDate.now().getDayOfMonth());
        return mav;
    }

}