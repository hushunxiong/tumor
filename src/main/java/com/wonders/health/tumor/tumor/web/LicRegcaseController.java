/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LicRegcase;

import com.wonders.health.tumor.tumor.vo.LicRegcaseSearchVo;
import com.wonders.health.tumor.tumor.service.LicRegcaseService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;

import javax.validation.Valid;
import java.util.Map;
/**
 * 肝癌初筛信息登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("licRegcase")
public class LicRegcaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LicRegcaseController.class);

    @Autowired
    private LicRegcaseService licRegcaseService;

	@RequiresPermissions("tumor:licRegcase:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/licRegcaseList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:licRegcase:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LicRegcase> data(LicRegcaseSearchVo search) {
        DataGrid<LicRegcase> grid = licRegcaseService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:licRegcase:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LicRegcase licRegcase = licRegcaseService.findById(id);
            model.addAttribute("licRegcase", licRegcase);
        }
        return "tumor/licRegcaseForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:licRegcase:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LicRegcase licRegcase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return licRegcaseService.saveOrUpdate(licRegcase, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:licRegcase:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                licRegcaseService.deleteById(id);
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