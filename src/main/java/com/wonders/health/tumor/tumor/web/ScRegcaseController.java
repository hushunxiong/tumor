/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.ScRegcase;

import com.wonders.health.tumor.tumor.vo.ScRegcaseSearchVo;
import com.wonders.health.tumor.tumor.service.ScRegcaseService;

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
 * 胃癌初筛信息登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("scRegcase")
public class ScRegcaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ScRegcaseController.class);

    @Autowired
    private ScRegcaseService scRegcaseService;

	@RequiresPermissions("tumor:scRegcase:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/scRegcaseList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:scRegcase:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<ScRegcase> data(ScRegcaseSearchVo search) {
        DataGrid<ScRegcase> grid = scRegcaseService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:scRegcase:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            ScRegcase scRegcase = scRegcaseService.findById(id);
            model.addAttribute("scRegcase", scRegcase);
        }
        return "tumor/scRegcaseForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:scRegcase:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid ScRegcase scRegcase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return scRegcaseService.saveOrUpdate(scRegcase, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:scRegcase:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                scRegcaseService.deleteById(id);
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