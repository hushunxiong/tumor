/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.CancerHistory;

import com.wonders.health.tumor.tumor.vo.CancerHistorySearchVo;
import com.wonders.health.tumor.tumor.service.CancerHistoryService;

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
 * 历史Controller
 * @author xuguobing
 */
@Controller
@RequestMapping("cancerHistory")
public class CancerHistoryController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CancerHistoryController.class);

    @Autowired
    private CancerHistoryService cancerHistoryService;

	@RequiresPermissions("tumor:cancerHistory:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/cancerHistoryList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:cancerHistory:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerHistory> data(CancerHistorySearchVo search) {
        DataGrid<CancerHistory> grid = cancerHistoryService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:cancerHistory:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            CancerHistory cancerHistory = cancerHistoryService.findById(id);
            model.addAttribute("cancerHistory", cancerHistory);
        }
        return "tumor/cancerHistoryForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:cancerHistory:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid CancerHistory cancerHistory, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return cancerHistoryService.saveOrUpdate(cancerHistory, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:cancerHistory:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                cancerHistoryService.deleteById(id);
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