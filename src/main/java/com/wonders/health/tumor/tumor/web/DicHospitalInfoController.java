/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.tumor.entity.DicHospitalInfo;

import com.wonders.health.tumor.tumor.vo.DicHospitalInfoSearchVo;
import com.wonders.health.tumor.tumor.service.DicHospitalInfoService;

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
import java.util.List;
import java.util.Map;
/**
 * 医疗机构表Controller
 * @author zhaomeng
 */
@Controller
@RequestMapping("dicHospitalInfo")
public class DicHospitalInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(DicHospitalInfoController.class);

    @Autowired
    private DicHospitalInfoService dicHospitalInfoService;

    @ResponseBody
    @RequestMapping("getHospitalsByAreaCode")
    public List<DataOption> getHospitalsByBgjg(String areaCode) {
       List<DataOption> areaList = dicHospitalInfoService.findHospitalsByAreaCode(areaCode,getSessionUser());
        return  areaList;
    }

}