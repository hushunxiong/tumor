/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LicAssistCheck;

import com.wonders.health.tumor.tumor.vo.LicAssistCheckSearchVo;
import com.wonders.health.tumor.tumor.service.LicAssistCheckService;

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
 * 肝癌辅助检查表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("licAssistCheck")
public class LicAssistCheckController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LicAssistCheckController.class);

    @Autowired
    private LicAssistCheckService licAssistCheckService;

	@RequiresPermissions("tumor:licAssistCheck:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/licAssistCheckList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:licAssistCheck:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LicAssistCheck> data(LicAssistCheckSearchVo search) {
        DataGrid<LicAssistCheck> grid = licAssistCheckService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:licAssistCheck:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LicAssistCheck licAssistCheck = licAssistCheckService.findById(id);
            model.addAttribute("licAssistCheck", licAssistCheck);
        }
        return "tumor/licAssistCheckForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:licAssistCheck:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LicAssistCheck licAssistCheck, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return licAssistCheckService.saveOrUpdate(licAssistCheck, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:licAssistCheck:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                licAssistCheckService.deleteById(id);
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