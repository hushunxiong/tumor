/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.utils.SpringContextHolder;
import com.wonders.health.tumor.tumor.dao.CrcRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;

import com.wonders.health.tumor.tumor.vo.CrcRegcaseSearchVo;
import com.wonders.health.tumor.tumor.service.CrcRegcaseService;

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
import java.util.List;
import java.util.Map;
/**
 * 大肠癌初筛信息登记表Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("crcRegcase")
public class CrcRegcaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CrcRegcaseController.class);

    @Autowired
    private CrcRegcaseService crcRegcaseService;

    private static CrcRegcaseDao crcRegcaseDao = SpringContextHolder.getBean(CrcRegcaseDao.class);


    private static CrcRegcaseService crcRegcaseService2 = SpringContextHolder.getBean(CrcRegcaseService.class);

	@RequiresPermissions("tumor:crcRegcase:list")
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
	    Object o= crcRegcaseService.getByManageidAndYear(crcRegcaseDao,"", "");
        return "tumor/crcRegcaseList";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcRegcase:list")
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CrcRegcase> data(CrcRegcaseSearchVo search) {
        DataGrid<CrcRegcase> grid = crcRegcaseService.findPage(search);
        return grid;
    }

    @RequiresPermissions("tumor:crcRegcase:save")
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model , String id) {
        if (StringUtils.isNotBlank(id)) {
            CrcRegcase crcRegcase = crcRegcaseService.findById(id);
            model.addAttribute("crcRegcase", crcRegcase);
        }
        return "tumor/crcRegcaseForm";
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcRegcase:save")
    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid CrcRegcase crcRegcase, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }
        try {
            return crcRegcaseService.saveOrUpdate(crcRegcase, getSessionUser().getId());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }

    @ResponseBody
    @RequiresPermissions("tumor:crcRegcase:del")
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                crcRegcaseService.deleteById(id);
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
            	logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }

    public static void main(String[] args){
        Object o= crcRegcaseService2.getByManageidAndYear(crcRegcaseDao,"", "");
    }
}