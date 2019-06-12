/**
 * 
 */
package com.wonders.health.tumor.busremind.web;

import com.wonders.health.tumor.busremind.service.BusRemindService;
import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.busremind.vo.BusRemindSearchVo;
import com.wonders.health.tumor.closingcase.entity.crcClosingCase;
import com.wonders.health.tumor.closingcase.vo.crcClosingCaseSearchVo;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
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

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 业务提醒Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("busremind")
public class BusinessRemindController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BusinessRemindController.class);

    @Autowired
    private BusRemindService busRemindService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${yearNum}")
    private  Integer yearNum;

    @Value("${crc_switch_flag}")
    private String crcFlag;

    @Value("${lic_switch_flag}")
    private String licFlag;

    @Value("${sc_switch_flag}")
    private String scFlag;

    @Value("${luc_switch_flag}")
    private String lucFlag;


    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("years",nds);
        model.addAttribute("orgcode", getSessionUser().getOrgCode());
        return "/busremind/busremindList";
    }


    //获取基本信息grid
    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerPersonInfo> data(BusRemindSearchVo search) {
        giveFlag(search);
        DataGrid<CancerPersonInfo> grid = busRemindService.findPersoninfo(search);
        return grid;
    }

    //type==01
    //获取采便器送回提醒下的fobt
    @ResponseBody
    @RequestMapping(value = "detailFobtRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailFobtRemind(String personcard) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getCrcFobtRemind(personcard);
        return grid;
    }


    //type==02
    //获取诊断检查提醒下的crcDiag
    @ResponseBody
    @RequestMapping(value = "detailCrcRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailCrcRemind(String personcard) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getCrcDiag(personcard,crcFlag);
        return grid;
    }

    //获取诊断检查提醒下的licDiag
    @ResponseBody
    @RequestMapping(value = "detailLicRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailLicRemind(String personcard) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getLicDiag(personcard,licFlag);
        return grid;
    }

    //获取诊断检查提醒下的scDiag
    @ResponseBody
    @RequestMapping(value = "detailScRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailScRemind(String personcard) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getScDiag(personcard,scFlag);
        return grid;
    }

    //获取诊断检查提醒下的lucDiag
    @ResponseBody
    @RequestMapping(value = "detaillLucRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detaillLucRemind(String personcard) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getLucDiag(personcard,lucFlag);
        return grid;
    }



    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid crcClosingCase crcClosingCase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return busRemindService.saveOrUpdate(crcClosingCase, getSessionUser().getId());
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
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
            	logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }


    private BusRemindSearchVo giveFlag(BusRemindSearchVo search){
        search.setCrcFlag(crcFlag);
        search.setLicFlag(licFlag);
        search.setLucFlag(lucFlag);
        search.setScFlag(scFlag);
        return search;

    }
}