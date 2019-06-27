/**
 * 
 */
package com.wonders.health.tumor.closingcase.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.closingcase.entity.LicClosingCase;

import com.wonders.health.tumor.closingcase.vo.LicClosingCaseSearchVo;
import com.wonders.health.tumor.closingcase.service.LicClosingCaseService;

import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 肝癌结案Controller
 * @author zhaomeng
 */
@Controller
@RequestMapping("licClosingCase")
public class LicClosingCaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LicClosingCaseController.class);

    @Autowired
    private LicClosingCaseService licClosingCaseService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${yearNum}")
    private  Integer yearNum;

	@RequiresPermissions("closingcase:LicClosingCase:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("csnf",nds);
        model.addAttribute("role", AuthUtils.judgeRole(AuthUtils.getUser().getOrgCode()));
        model.addAttribute("areaList", AuthUtils.getAreas(areaCode));
        model.addAttribute("areaCode", areaCode);
        return "/closingcase/licClosingCaseList";
    }

    @ResponseBody
    @RequiresPermissions("closingcase:LicClosingCase:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LicClosingCase> data(LicClosingCaseSearchVo search) {
        DataGrid<LicClosingCase> grid = licClosingCaseService.findPage(search);
        return grid;
    }

    @RequiresPermissions("closingcase:LicClosingCase:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LicClosingCase licClosingCase = licClosingCaseService.findById(id);
            model.addAttribute("licClosingCase", licClosingCase);
        }
        return "/closingcase/licClosingCaseForm";
    }

    @ResponseBody
    @RequiresPermissions("closingcase:LicClosingCase:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LicClosingCase licClosingCase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return licClosingCaseService.saveOrUpdate(licClosingCase, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("closingcase:LicClosingCase:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                licClosingCaseService.deleteById(id);
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
            	logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }

}