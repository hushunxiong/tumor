/**
 * 
 */
package com.wonders.health.tumor.busremind.web;

import com.wonders.health.tumor.busremind.service.BusRemindService;
import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.busremind.vo.BusRemindSearchVo;
import com.wonders.health.tumor.closingcase.entity.CrcClosingCase;
import com.wonders.health.tumor.closingcase.vo.CrcClosingCaseSearchVo;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.tumor.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业务提醒Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("busremind")
public class BusinessRemindController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BusinessRemindController.class);

    @Autowired
    private BusRemindService busRemindService;

    @Value("${area_code}")
    private String areaCode;

    @Value("${yearNum}")
    private  Integer yearNum;

    @Value("${crc_switch_flag}")
    private String crcFlag;

    @Value("${lic_switch_flag}")
    private String licFlag;

    @Value("${sc_switch_flag}")
    private String scFlag;

    @Value("${luc_switch_flag}")
    private String lucFlag;


    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model) {
        List<String> nds = DateUtils.getYearBefore(Integer.parseInt(DateUtils.getYear()), yearNum);
        Collections.sort(nds,Collections.reverseOrder());
        model.addAttribute("years",nds);
        model.addAttribute("orgcode", getSessionUser().getOrgCode());
        if(crcFlag=="0" || "0".equals(crcFlag)){
            model.addAttribute("type","02");
        }else{
            model.addAttribute("type","");
        }
        return "/busremind/busremindList";
    }


    //获取基本信息grid
    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CancerPersonInfo> data(BusRemindSearchVo search) {
        giveFlag(search);
        DataGrid<CancerPersonInfo> grid = busRemindService.findPersoninfo(search);
        return grid;
    }

    //type==01
    //获取采便器送回提醒下的fobt
    @ResponseBody
    @RequestMapping(value = "detailFobtRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailFobtRemind(String personcard,String status) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getCrcFobtRemind(personcard,status);
        return grid;
    }


    //type==02
    //获取诊断检查提醒下的crcDiag
    @ResponseBody
    @RequestMapping(value = "detailCrcRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailCrcRemind(String personcard,String status) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getCrcDiag(personcard,status,crcFlag);
        return grid;
    }

    //获取诊断检查提醒下的licDiag
    @ResponseBody
    @RequestMapping(value = "detailLicRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailLicRemind(String personcard,String status) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getLicDiag(personcard,status,licFlag);
        return grid;
    }

    //获取诊断检查提醒下的scDiag
    @ResponseBody
    @RequestMapping(value = "detailScRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detailScRemind(String personcard,String status) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getScDiag(personcard,status,scFlag);
        return grid;
    }

    //获取诊断检查提醒下的lucDiag
    @ResponseBody
    @RequestMapping(value = "detaillLucRemind", produces = "application/json; charset=utf-8")
    public DataGrid<BusRemindResultVo> detaillLucRemind(String personcard,String status) {
        DataGrid<BusRemindResultVo> grid = busRemindService.getLucDiag(personcard,status,lucFlag);
        return grid;
    }



    //更新大肠癌便隐血提醒  传参解释：id为对应便隐血检查提醒表id，type为电话（01）或者上门（02），first代表第一次提醒日期是否为空，second代表第二次提醒日期是否为空
    @ResponseBody
    @RequestMapping(value = "updateCrcFobtRemind", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> updateCrcFobtRemind(String id,String type, String first,String second,String res1,String res2) {
        try {
            AjaxReturn<Map<String, String>> res=new AjaxReturn<Map<String, String>>();
            CrcFobtRemind vo=new CrcFobtRemind();
            vo.setId(id);
            vo.setUpdateBy(getSessionUser().getId());
            vo.setUpdateDate(new Date());

            if((StringUtils.isBlank(res1)||res1==null||res1=="null"||"null".equals(res1))&&(StringUtils.isBlank(res2)||res2==null||res2=="null"||"null".equals(res2))){//第一次检查结果为空
                if((StringUtils.isBlank(first)||first==null||first=="null"||"null".equals(first))&&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))){                //第一次提醒日期、第二次提醒日期均为空的场合（电话、上门）
                    vo.setPerFobtRemindDate(DateUtils.addDate(new Date(),7));
                    vo.setFobtRemindStatus("01");
                    vo.setFirstFobtRemindDate(new Date());
                    vo.setFirstFobtRemindType(type);
                    res=busRemindService.updateCrcFobtRemind(vo);
                }else if(StringUtils.isNotBlank(first)&&first!="null"&&!"null".equals(first)&&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))){      //第一次提醒日期不为空、第二次提醒日期为空的场合（电话、上门）
                    vo.setFobtRemindStatus("04");
                    vo.setSecondFobtRemindDate(new Date());
                    vo.setSecondFobtRemindType(type);
                    res=busRemindService.updateCrcFobtRemind(vo);
                }
            }else if(StringUtils.isNotBlank(res1)&&res1!="null"&&!"null".equals(res1)&&(StringUtils.isBlank(res2)||res2==null||res2=="null"||"null".equals(res2))){
                vo.setFobtRemindStatus("04");
                vo.setSecondFobtRemindDate(new Date());
                vo.setSecondFobtRemindType(type);
                res=busRemindService.updateCrcFobtRemind(vo);
            }else{
                res=new AjaxReturn<Map<String, String>>(false, "操作异常");
            }
            return res;
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "操作异常");
        }
    }

    //更新大肠癌诊断检查提醒  传参解释：id为对应诊断检查提醒表id，type为电话（01）或者上门（02），first代表第一次提醒日期是否为空，second代表第二次提醒日期是否为空
    @ResponseBody
    @RequestMapping(value = "updateCrcDiag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> updateCrcDiag(String id,String type, String first,String second) {
        try {
            AjaxReturn<Map<String, String>> res=new AjaxReturn<Map<String, String>>();
            CrcDiagCheckRemind vo=new CrcDiagCheckRemind();

            vo.setId(id);
            vo.setUpdateBy(getSessionUser().getId());
            vo.setUpdateDate(new Date());
            if((StringUtils.isBlank(first)||first==null||first=="null"||"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//两次提醒日期都为空
                vo.setFirstRemindDate(new Date());
                vo.setFirstRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("01");
                res=busRemindService.updateCrcDiag(vo);
            }else if((StringUtils.isNotBlank(first)||first!=null||first!="null"||!"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//后一次提醒日期都为空
                vo.setSecondRemindDate(new Date());
                vo.setSecondRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("04");
                res=busRemindService.updateCrcDiag(vo);
            }
            return res;
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "操作异常");
        }
    }

    //更新肝癌诊断检查提醒  传参解释：id为对应诊断检查提醒表id，type为电话（01）或者上门（02），first代表第一次提醒日期是否为空，second代表第二次提醒日期是否为空
    @ResponseBody
    @RequestMapping(value = "updateLicDiag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> updateLicDiag(String id,String type, String first,String second) {
        try {
            LicDiagCheckRemind vo=new LicDiagCheckRemind();
            vo.setId(id);
            vo.setUpdateBy(getSessionUser().getId());
            vo.setUpdateDate(new Date());
            AjaxReturn<Map<String, String>> res=new AjaxReturn<Map<String, String>>();
            if((StringUtils.isBlank(first)||first==null||first=="null"||"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//2次提醒日期都为空
                vo.setFirstRemindDate(new Date());
                vo.setFirstRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("01");
                res=busRemindService.updateLicDiag(vo);
            }else if((StringUtils.isNotBlank(first)||first!=null||first!="null"||!"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//后1次提醒日期都为空
                vo.setSecondRemindDate(new Date());
                vo.setSecondRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("04");
                res=busRemindService.updateLicDiag(vo);
            }

            return res;
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "操作异常");
        }
    }


    //更新胃癌诊断检查提醒  传参解释：id为对应诊断检查提醒表id，type为电话（01）或者上门（02），first代表第一次提醒日期是否为空，second代表第二次提醒日期是否为空
    @ResponseBody
    @RequestMapping(value = "updateScDiag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> updateScDiag(String id,String type, String first,String second) {
        try {
            AjaxReturn<Map<String, String>> res=new AjaxReturn<Map<String, String>>();
            ScDiagCheckRemind vo=new ScDiagCheckRemind();

            vo.setId(id);
            vo.setUpdateBy(getSessionUser().getId());
            vo.setUpdateDate(new Date());
            if((StringUtils.isBlank(first)||first==null||first=="null"||"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//三次提醒日期都为空
                vo.setFirstRemindDate(new Date());
                vo.setFirstRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("01");
                res=busRemindService.updateScDiag(vo);
            }else if((StringUtils.isNotBlank(first)||first!=null||first!="null"||!"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//后两次提醒日期都为空
                vo.setSecondRemindDate(new Date());
                vo.setSecondRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("04");
                res=busRemindService.updateScDiag(vo);
            }
            return res;
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "操作异常");
        }
    }

    //更新肺癌诊断检查提醒  传参解释：id为对应诊断检查提醒表id，type为电话（01）或者上门（02），first代表第一次提醒日期是否为空，second代表第二次提醒日期是否为空
    @ResponseBody
    @RequestMapping(value = "updateLucDiag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> updateLucDiag(String id,String type, String first,String second,String res1,String res2) {
        try {
            AjaxReturn<Map<String, String>> res=new AjaxReturn<Map<String, String>>();
            LucDiagCheckRemind vo=new LucDiagCheckRemind();

            vo.setId(id);
            vo.setUpdateBy(getSessionUser().getId());
            vo.setUpdateDate(new Date());
            if((StringUtils.isBlank(first)||first==null||first=="null"||"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//三次提醒日期都为空
                vo.setFirstRemindDate(new Date());
                vo.setFirstRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("01");
                res=busRemindService.updateLucDiag(vo);
            }else if((StringUtils.isNotBlank(first)||first!=null||first!="null"||!"null".equals(first))
                    &&(StringUtils.isBlank(second)||second==null||second=="null"||"null".equals(second))) {//后两次提醒日期都为空
                vo.setSecondRemindDate(new Date());
                vo.setSecondRemindType(type);
                vo.setPerRemindDate(DateUtils.addDate(new Date(),30));
                vo.setRemindStatus("04");
                res=busRemindService.updateLucDiag(vo);
            }
            return res;
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "操作异常");
        }
    }




    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
            	logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }


    private BusRemindSearchVo giveFlag(BusRemindSearchVo search){
        search.setCrcFlag(crcFlag);
        search.setLicFlag(licFlag);
        search.setLucFlag(lucFlag);
        search.setScFlag(scFlag);
        return search;

    }
}