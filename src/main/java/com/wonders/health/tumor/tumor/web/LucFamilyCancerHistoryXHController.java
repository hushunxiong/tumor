/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.LucFamilyCancerHistoryXH;

import com.wonders.health.tumor.tumor.vo.LucFamilyCancerHistoryXHSearchVo;
import com.wonders.health.tumor.tumor.service.LucFamilyCancerHistoryXHService;

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
@RequestMapping("lucFamilyCancerHistoryXH")
public class LucFamilyCancerHistoryXHController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LucFamilyCancerHistoryXHController.class);

    @Autowired
    private LucFamilyCancerHistoryXHService lucFamilyCancerHistoryXHService;

	@RequiresPermissions("tumor:lucFamilyCancerHistoryXH:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/lucFamilyCancerHistoryXHList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucFamilyCancerHistoryXH:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LucFamilyCancerHistoryXH> data(LucFamilyCancerHistoryXHSearchVo search) {
        DataGrid<LucFamilyCancerHistoryXH> grid = lucFamilyCancerHistoryXHService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:lucFamilyCancerHistoryXH:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            LucFamilyCancerHistoryXH lucFamilyCancerHistoryXH = lucFamilyCancerHistoryXHService.findById(id);
            model.addAttribute("lucFamilyCancerHistoryXH", lucFamilyCancerHistoryXH);
        }
        return "tumor/lucFamilyCancerHistoryXHForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucFamilyCancerHistoryXH:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LucFamilyCancerHistoryXH lucFamilyCancerHistoryXH, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return lucFamilyCancerHistoryXHService.saveOrUpdate(lucFamilyCancerHistoryXH, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:lucFamilyCancerHistoryXH:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                lucFamilyCancerHistoryXHService.deleteById(id);
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