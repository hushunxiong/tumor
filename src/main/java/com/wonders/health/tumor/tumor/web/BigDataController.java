package com.wonders.health.tumor.tumor.web;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.entity.CancerDicArea;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.service.HospitalDicService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import com.wonders.health.tumor.tumor.service.BigDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 大数据筛查Controller
 * @author sunyang
 */
@Controller
@RequestMapping("bigData")
public class BigDataController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BigDataController.class);

    @Autowired
    private HospitalDicService hospitalDicService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BigDataService bigDataService;

    @Value("${area_code}")
    private String areaCode;

    @RequiresPermissions("tumor:bigData:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        CancerDicHospitalInfo hospitalInfo = hospitalDicService.getHospital(getSessionUser().getOrgCode());
        CancerDicArea area = areaService.getArea(hospitalInfo.getTown());
        Gson gson = new Gson();
        List<DataOption> datas = Lists.newArrayList();
        DataOption option = new DataOption(hospitalInfo.getTown(), area.getCname());
        datas.add(option);

        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("town", hospitalInfo.getTown());
        model.addAttribute("townData", gson.toJson(datas));
        model.addAttribute("orgCode", getSessionUser().getOrgCode());
        return "/register/bigDataList";
    }
}
