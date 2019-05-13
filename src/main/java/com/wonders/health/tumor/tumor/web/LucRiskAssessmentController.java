/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LucRiskAssessment;

import com.wonders.health.tumor.tumor.vo.LucRiskAssessmentSearchVo;
import com.wonders.health.tumor.tumor.service.LucRiskAssessmentService;

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
 * 肺癌危险度评估表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("lucRiskAssessment")
public class LucRiskAssessmentController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LucRiskAssessmentController.class);

    @Autowired
    private LucRiskAssessmentService lucRiskAssessmentService;

	@RequiresPermissions("tumor:lucRiskAssessment:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/lucRiskAssessmentList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRiskAssessment:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LucRiskAssessment> data(LucRiskAssessmentSearchVo search) {
        DataGrid<LucRiskAssessment> grid = lucRiskAssessmentService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:lucRiskAssessment:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LucRiskAssessment lucRiskAssessment = lucRiskAssessmentService.findById(id);
            model.addAttribute("lucRiskAssessment", lucRiskAssessment);
        }
        return "tumor/lucRiskAssessmentForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRiskAssessment:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LucRiskAssessment lucRiskAssessment, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return lucRiskAssessmentService.saveOrUpdate(lucRiskAssessment, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucRiskAssessment:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                lucRiskAssessmentService.deleteById(id);
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