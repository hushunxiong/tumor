/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;

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

import javax.validation.Valid;
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

}