/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LucDiagCheckRemind;

import com.wonders.health.tumor.tumor.vo.LucDiagCheckRemindSearchVo;
import com.wonders.health.tumor.tumor.service.LucDiagCheckRemindService;

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
import com.wonders.health.tumor.common.model.DataGridSearch;

import javax.validation.Valid;
import java.util.Map;
/**
 * 胃癌诊断检查提醒表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("lucDiagCheckRemind")
public class LucDiagCheckRemindController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LucDiagCheckRemindController.class);

    @Autowired
    private LucDiagCheckRemindService lucDiagCheckRemindService;

	@RequiresPermissions("tumor:lucDiagCheckRemind:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/lucDiagCheckRemindList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucDiagCheckRemind:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LucDiagCheckRemind> data(LucDiagCheckRemindSearchVo search) {
        DataGrid<LucDiagCheckRemind> grid = lucDiagCheckRemindService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:lucDiagCheckRemind:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LucDiagCheckRemind lucDiagCheckRemind = lucDiagCheckRemindService.findById(id);
            model.addAttribute("lucDiagCheckRemind", lucDiagCheckRemind);
        }
        return "tumor/lucDiagCheckRemindForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucDiagCheckRemind:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LucDiagCheckRemind lucDiagCheckRemind, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return lucDiagCheckRemindService.saveOrUpdate(lucDiagCheckRemind, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucDiagCheckRemind:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                lucDiagCheckRemindService.deleteById(id);
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