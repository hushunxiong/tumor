package com.wonders.health.tumor.tumor.web;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.health.auth.client.vo.Hospital;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.entity.CancerDic;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.entity.*;
import com.wonders.health.tumor.tumor.service.*;
import com.wonders.health.tumor.tumor.vo.CancerPersonInfoSearchVo;
import com.wonders.health.tumor.tumor.vo.ScreeningVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理对象信息Controller
 *
 * @author sot wangyixian
 */
@Controller
@RequestMapping("managementObject")
public class ManagementObjectController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ManagementObjectController.class);

    @Autowired
    private CancerPersonInfoService cancerPersonInfoService;

    @Autowired
    private ManagementObjectService managementObjectService;

    @Autowired
    private LucAppLdctXhService lucAppLdctXhService;

    @Autowired
    private LucLDCTCheckXHService lucLDCTCheckXHService;

    @Autowired
    private AuthServiceI authServiceI;

    @Autowired
    private AreaService areaService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${crc_switch_flag}")
    private Integer crcFlag;

    @Value("${luc_switch_flag}")
    private Integer lucFlag;

    @Value("${lic_switch_flag}")
    private Integer licFlag;

    @Value("${sc_switch_flag}")
    private Integer scFlag;

    @Value("${yearNum}")
    private Integer yearNum;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds, Collections.reverseOrder());
        model.addAttribute("csnf", nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("ip", authServiceI.findBaseUrl("肿瘤早发现", getSessionUser().getOrgCode()));

        return "/management/managementObjectList";
    }

    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerPersonInfo> data(Model model, CancerPersonInfoSearchVo search) {
        search.setCrcFlag(crcFlag.toString());
        search.setLucFlag(lucFlag.toString());
        search.setLicFlag(licFlag.toString());
        search.setScFlag(scFlag.toString());
        DataGrid<CancerPersonInfo> grid = cancerPersonInfoService.findPage(search);
        return grid;
    }

    @RequestMapping(value = {"", "detail"}, method = RequestMethod.GET)
    public String detail(Model model, String manageId) {

        CancerPersonInfo cancerPersonInfo = cancerPersonInfoService.findById(manageId);
        model.addAttribute("personInfo", cancerPersonInfo);

        List<LucRegcase> lucRegcaseList = managementObjectService.getLucRegcaseListByManageId(manageId);
        model.addAttribute("lucRegcaseList", lucRegcaseList);

        // 取最后一个筛查登记作为筛查年份
        if (lucRegcaseList.size() > 0) {
            model.addAttribute("checkYear", lucRegcaseList.get(lucRegcaseList.size() - 1).getCheckYear());
        }

        // 获得徐汇肺癌LDCT预约记录表
        List<LucAppLdctXh> lucAppLdctXhList = managementObjectService.getLucAppLdctXhListByManageId(manageId);
        model.addAttribute("lucAppLdctXhList", lucAppLdctXhList);

        // 低密度螺旋CT检查信息
        List<LucLDCTCheckXH> lucLDCTCheckXHSList = new ArrayList<>();
        for (LucAppLdctXh item : lucAppLdctXhList) {
            LucLDCTCheckXH lucLDCTCheckXH = lucLDCTCheckXHService.findByOrderId(item.getId());
            if (lucLDCTCheckXH != null) {
                lucLDCTCheckXH.setLucAppLdctXh(item);
                lucLDCTCheckXHSList.add(lucLDCTCheckXH);
            }
        }
        model.addAttribute("lucLDCTCheckXHSList", lucLDCTCheckXHSList);

        return "/management/detail";
    }

    @RequestMapping(value = {"", "ldctCheckDetail"}, method = RequestMethod.GET)
    public String ldctCheckDetail(Model model, String manageId, String ldcdOrderId) {
        CancerPersonInfo cancerPersonInfo = cancerPersonInfoService.findById(manageId);
        if (cancerPersonInfo != null) {
            //居住地址拼接
            cancerPersonInfo.setAddressDetail(areaService.getFullAddress(
                    cancerPersonInfo.getAddressProvince(), cancerPersonInfo.getAddressCity(), cancerPersonInfo.getAddressCounty(),
                    cancerPersonInfo.getAddressTown(), cancerPersonInfo.getAddressCommittee(), cancerPersonInfo.getAddressDetail()));
            model.addAttribute("personInfo", cancerPersonInfo);
        } else {
            model.addAttribute("personInfo", new CancerPersonInfo());
        }

        LucAppLdctXh lucAppLdctXh = lucAppLdctXhService.findById(ldcdOrderId);
        if (lucAppLdctXh != null) {
            // 管理医疗机构的获取
            Hospital hos = AuthUtils.getHospitalByCode(lucAppLdctXh.getAppointmentOrg());
            if (hos != null) {
                lucAppLdctXh.setAppointmentOrg(hos.getName());
            }

            model.addAttribute("ldctApp", lucAppLdctXh);

            LucLDCTCheckXH lucLDCTCheckXH = lucLDCTCheckXHService.findByOrderId(lucAppLdctXh.getId());
            if (lucLDCTCheckXH != null) {
                model.addAttribute("ldctCheck", lucLDCTCheckXH);
            } else {
                model.addAttribute("ldctCheck", new LucLDCTCheckXH());
            }
        } else {
            model.addAttribute("ldctApp", new LucAppLdctXh());
            model.addAttribute("ldctCheck", new LucLDCTCheckXH());
        }

        return "/management/ldctCheckDetail";
    }
}
