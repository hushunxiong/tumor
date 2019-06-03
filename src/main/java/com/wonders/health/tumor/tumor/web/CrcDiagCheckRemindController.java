/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.CrcDiagCheckRemind;

import com.wonders.health.tumor.tumor.vo.CrcDiagCheckRemindSearchVo;
import com.wonders.health.tumor.tumor.service.CrcDiagCheckRemindService;

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
 * 大肠癌诊断检查提醒表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("crcDiagCheckRemind")
public class CrcDiagCheckRemindController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CrcDiagCheckRemindController.class);

    @Autowired
    private CrcDiagCheckRemindService crcDiagCheckRemindService;

	@RequiresPermissions("tumor:crcDiagCheckRemind:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/crcDiagCheckRemindList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcDiagCheckRemind:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CrcDiagCheckRemind> data(CrcDiagCheckRemindSearchVo search) {
        DataGrid<CrcDiagCheckRemind> grid = crcDiagCheckRemindService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:crcDiagCheckRemind:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            CrcDiagCheckRemind crcDiagCheckRemind = crcDiagCheckRemindService.findById(id);
            model.addAttribute("crcDiagCheckRemind", crcDiagCheckRemind);
        }
        return "tumor/crcDiagCheckRemindForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcDiagCheckRemind:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid CrcDiagCheckRemind crcDiagCheckRemind, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return crcDiagCheckRemindService.saveOrUpdate(crcDiagCheckRemind, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcDiagCheckRemind:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                crcDiagCheckRemindService.deleteById(id);
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