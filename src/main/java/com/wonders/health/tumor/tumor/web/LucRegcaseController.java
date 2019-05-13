/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LucRegcase;

import com.wonders.health.tumor.tumor.vo.LucRegcaseSearchVo;
import com.wonders.health.tumor.tumor.service.LucRegcaseService;

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
 * 肺癌初筛信息登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("lucRegcase")
public class LucRegcaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LucRegcaseController.class);

    @Autowired
    private LucRegcaseService lucRegcaseService;

	@RequiresPermissions("tumor:lucRegcase:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/lucRegcaseList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRegcase:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LucRegcase> data(LucRegcaseSearchVo search) {
        DataGrid<LucRegcase> grid = lucRegcaseService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:lucRegcase:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LucRegcase lucRegcase = lucRegcaseService.findById(id);
            model.addAttribute("lucRegcase", lucRegcase);
        }
        return "tumor/lucRegcaseForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRegcase:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LucRegcase lucRegcase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return lucRegcaseService.saveOrUpdate(lucRegcase, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRegcase:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                lucRegcaseService.deleteById(id);
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