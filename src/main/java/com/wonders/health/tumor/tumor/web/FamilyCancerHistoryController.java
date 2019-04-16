/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.FamilyCancerHistory;

import com.wonders.health.tumor.tumor.vo.FamilyCancerHistorySearchVo;
import com.wonders.health.tumor.tumor.service.FamilyCancerHistoryService;

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
 * 危险度评估-一级亲属癌症史表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("familyCancerHistory")
public class FamilyCancerHistoryController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FamilyCancerHistoryController.class);

    @Autowired
    private FamilyCancerHistoryService familyCancerHistoryService;

	@RequiresPermissions("tumor:familyCancerHistory:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/familyCancerHistoryList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:familyCancerHistory:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<FamilyCancerHistory> data(FamilyCancerHistorySearchVo search) {
        DataGrid<FamilyCancerHistory> grid = familyCancerHistoryService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:familyCancerHistory:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            FamilyCancerHistory familyCancerHistory = familyCancerHistoryService.findById(id);
            model.addAttribute("familyCancerHistory", familyCancerHistory);
        }
        return "tumor/familyCancerHistoryForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:familyCancerHistory:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid FamilyCancerHistory familyCancerHistory, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return familyCancerHistoryService.saveOrUpdate(familyCancerHistory, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:familyCancerHistory:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                familyCancerHistoryService.deleteById(id);
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