package com.wonders.health.tumor.tumor.web;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.entity.CancerDicArea;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.service.HospitalDicService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.entity.LucPushXh;
import com.wonders.health.tumor.tumor.service.BigDataService;
import com.wonders.health.tumor.tumor.vo.BigDataSearchVo;
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

import java.util.List;
import java.util.Map;

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
    public String list(Model model, String kp) {
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
        model.addAttribute("kp", kp);
        return "/register/bigDataList";
    }

    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LucPushXh> data(BigDataSearchVo search) {
        String[] risks = search.getRisk().split(",");
        search.setRisks(risks);

        DataGrid<LucPushXh> grid = bigDataService.findPage(search);
        return grid;
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model, String id, String kp) {
        model.addAttribute("pushid", id);
        model.addAttribute("base", bigDataService.getPushData(id));
        model.addAttribute("riskList", bigDataService.getPushRiskData(id));
        model.addAttribute("statusList", new Gson().toJson(bigDataService.getStatusList()));
        model.addAttribute("kp", kp);
        return "/register/bigDataForm";
    }

    @ResponseBody
    @RequestMapping(value = "save", produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(String pushid, String status) {

        try {
            return bigDataService.saveOrUpdate(pushid, status, getSessionUser());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "核实异常");
        }
    }

}
