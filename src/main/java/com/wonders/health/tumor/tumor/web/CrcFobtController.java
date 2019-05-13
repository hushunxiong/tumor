/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.tumor.entity.CrcFobt;

import com.wonders.health.tumor.tumor.vo.CrcFobtSearchVo;
import com.wonders.health.tumor.tumor.service.CrcFobtService;

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
 * 大肠癌便隐血检查表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("crcFobt")
public class CrcFobtController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CrcFobtController.class);

    @Autowired
    private CrcFobtService crcFobtService;

	@RequiresPermissions("tumor:crcFobt:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "tumor/crcFobtList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcFobt:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CrcFobt> data(CrcFobtSearchVo search) {
        DataGrid<CrcFobt> grid = crcFobtService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:crcFobt:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            CrcFobt crcFobt = crcFobtService.findById(id);
            model.addAttribute("crcFobt", crcFobt);
        }
        return "tumor/crcFobtForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcFobt:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid CrcFobt crcFobt, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return crcFobtService.saveOrUpdate(crcFobt, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcFobt:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                crcFobtService.deleteById(id);
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