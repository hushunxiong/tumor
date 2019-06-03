package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.Contants;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.service.FollowService;
import com.wonders.health.tumor.follow.vo.FollowPersonInfoSearchVo;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
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
 * Created by sunyang on 2019/5/31.
 */
@Controller
@RequestMapping("follow")
public class FollowController extends BaseController {

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
    private  Integer yearNum;

    @Autowired
    private FollowService followService;

    /**
     * 随访一览
     * @return
     */
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaCode", areaCode);
        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        return "/follow/followPersonList";
    }

    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerPersonInfo> data(FollowPersonInfoSearchVo search) {
        search.setCrcFlag(crcFlag.toString());
        search.setLucFlag(lucFlag.toString());
        search.setLicFlag(licFlag.toString());
        search.setScFlag(scFlag.toString());
        DataGrid<CancerPersonInfo> grid = followService.findPage(search);

        return grid;
    }

    @RequestMapping(value = "person", method = RequestMethod.GET)
    public String person(Model model, String manageId, String checkYear) {

        if (crcFlag == 1) {
            String crcResult = followService.getCrcResult(manageId, checkYear);
            if (StringUtils.isNotBlank(crcResult)
                    && StringUtils.equals(Contants.CHECKRESULT_YANG, crcResult)) {
                model.addAttribute("crcFlag", "1");
            }

        }
        if (licFlag == 1) {
            String licResult = followService.getLicResult(manageId, checkYear);
            if (StringUtils.isNotBlank(licResult)
                    && StringUtils.equals(Contants.CHECKRESULT_YANG, licResult)) {
                model.addAttribute("licFlag", "1");
            }

        }
        if (lucFlag == 1) {
            String lucResult = followService.getLucResult(manageId, checkYear);
            if (StringUtils.isNotBlank(lucResult)
                    && StringUtils.equals(Contants.CHECKRESULT_YANG, lucResult)) {
                model.addAttribute("lucFlag", "1");
            }
        }
        if (scFlag == 1) {
            String scResult = followService.getScResult(manageId, checkYear);
            if (StringUtils.isNotBlank(scResult)
                    && StringUtils.equals(Contants.CHECKRESULT_YANG, scResult)) {
                model.addAttribute("scFlag", "1");
            }
        }
        return "/follow/followPerson";
    }
}
