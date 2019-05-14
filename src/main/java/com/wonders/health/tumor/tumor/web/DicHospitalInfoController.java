/**
 * 
 */
package com.wonders.health.tumor.tumor.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.tumor.service.DicHospitalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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