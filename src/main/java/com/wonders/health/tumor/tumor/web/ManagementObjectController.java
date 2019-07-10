package com.wonders.health.tumor.tumor.web;

import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.service.CancerPersonInfoService;
import com.wonders.health.tumor.tumor.vo.CancerPersonInfoSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * 管理对象信息Controller
 * @author sot wangyixian
 */
@Controller
@RequestMapping("managementObject")
public class ManagementObjectController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ManagementObjectController.class);

    @Autowired
    private CancerPersonInfoService cancerPersonInfoService;
    @Autowired
    private AuthServiceI authServiceI;

    @Value("${area_code}")
    private String areaCode;

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
    public String list(Model model) {
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
}
