package com.wonders.health.tumor.tumor.web;


import com.google.common.collect.Lists;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.utils.*;
import com.wonders.health.tumor.tumor.dao.*;
import com.wonders.health.tumor.tumor.entity.*;
import com.wonders.health.tumor.tumor.service.*;
import com.wonders.health.tumor.tumor.vo.ScreeningVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 新增筛查登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("screening")
public class ScreeningController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ScreeningController.class);

    @Value("${crc_switch_flag}")
    private String crcFlag;

    @Value("${lic_switch_flag}")
    private String licFlag;

    @Value("${sc_switch_flag}")
    private String scFlag;

    @Value("${luc_switch_flag}")
    private String lucFlag;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private CancerPersonInfoService cancerPersonInfoService;

    @Autowired
    private CrcRegcaseService crcRegcaseService;
    @Autowired
    private LicRegcaseService licRegcaseService;
    @Autowired
    private LucRegcaseService lucRegcaseService;
    @Autowired
    private ScRegcaseService scRegcaseService;

    @Autowired
    private CrcRiskAssessmentService crcRiskAssessmentService;
    @Autowired
    private LicRiskAssessmentService licRiskAssessmentService;
    @Autowired
    private LucRiskAssessmentService lucRiskAssessmentService;
    @Autowired
    private ScRiskAssessmentService scRiskAssessmentService;

    private static CrcRegcaseDao crcRegcaseDao = SpringContextHolder.getBean(CrcRegcaseDao.class);
    private static LicRegcaseDao licRegcaseDao = SpringContextHolder.getBean(LicRegcaseDao.class);
    private static LucRegcaseDao lucRegcaseDao = SpringContextHolder.getBean(LucRegcaseDao.class);
    private static ScRegcaseDao scRegcaseDao = SpringContextHolder.getBean(ScRegcaseDao.class);

    private static CrcRiskAssessmentDao crcRiskAssessmentDao = SpringContextHolder.getBean(CrcRiskAssessmentDao.class);
    private static LicRiskAssessmentDao licRiskAssessmentDao = SpringContextHolder.getBean(LicRiskAssessmentDao.class);
    private static LucRiskAssessmentDao lucRiskAssessmentDao = SpringContextHolder.getBean(LucRiskAssessmentDao.class);
    private static ScRiskAssessmentDao scRiskAssessmentDao = SpringContextHolder.getBean(ScRiskAssessmentDao.class);

    @Autowired
    private CrcFobtService crcFobtService;
    @Autowired
    private LicAssistCheckService licAssistCheckService;

    @Autowired
    private CancerHistoryService cancerHistoryService;
    @Autowired
    private  FamilyCancerHistoryService familyCancerHistoryService;
    @Autowired
    private LucFamilyCancerHistoryXHService lucFamilyCancerHistoryXHService;

    private static CrcFobtDao crcFobtDao = SpringContextHolder.getBean(CrcFobtDao.class);
    private static LicAssistCheckDao licAssistCheckDao = SpringContextHolder.getBean(LicAssistCheckDao.class);

    private static CancerHistoryDao cancerHistoryDao = SpringContextHolder.getBean(CancerHistoryDao.class);
    private static FamilyCancerHistoryDao familyCancerHistoryDao = SpringContextHolder.getBean(FamilyCancerHistoryDao.class);
    private static LucFamilyCancerHistoryXHDao lucFamilyCancerHistoryXHDao = SpringContextHolder.getBean(LucFamilyCancerHistoryXHDao.class);


    private Boolean isOpen(String flag){
        if(flag.equals("1")|| "1"==flag){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = {"", "form"}, method = RequestMethod.GET)
    public String form(Model model, String manageId, String checkYear) {

        List<DataOption> years = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            DataOption option = new DataOption();
            String year = DateUtils.formatDate(DateUtils.addYears(new Date(),i), "yyyy");
            option.setId(year);
            option.setText(year);
            years.add(option);
        }
        model.addAttribute("years", AuthUtils.toJson(years));
        model.addAttribute("checkYear", checkYear);

        //个人管理编号
        if (StringUtils.isBlank(manageId)) {
            CancerPersonInfo personInfo = new CancerPersonInfo();
            personInfo.setRegdoc(getSessionUser().getId());
            personInfo.setRegorg(getSessionUser().getOrgCode());
            personInfo.setRegdate(new Date());

            model.addAttribute("personInfo", personInfo);
            model.addAttribute("lucRisk", new LucRiskAssessment());
            model.addAttribute("scRisk", new ScRiskAssessment());
            model.addAttribute("crcRisk", new CrcRiskAssessment());
            model.addAttribute("licRisk", new LicRiskAssessment());
            model.addAttribute("idNumber", "");
            model.addAttribute("flag", "1"); //1：新增
        } else {
            model.addAttribute("flag", "2"); //2：修改
        }

        //TODO
        //各癌症数据库存在标志
        model.addAttribute("crcDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("licDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("scDbflag", "1");  //数据库状态  1：新增 2：修改
        model.addAttribute("lucDbflag", "1"); //数据库状态  1：新增 2：修改

        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("lucFlag", lucFlag);
        return "/register/form";
    }


    @RequestMapping(value = {"", "checkHadDone"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxReturn checkHadDone(String year, String type, String personcard){
        String[] cancers={crcFlag,licFlag,scFlag,lucFlag};
        String hadDone=screeningService.checkHadDone(year,type,personcard,cancers);    //true-做过  false-没做过

        if(hadDone=="done"||"done".equals(hadDone)){
            return new AjaxReturn(false,"此人本年度已经进行过筛查");
        }else if(hadDone=="1"||"1".equals(hadDone)){
            logger.error("当前证件号码查不到任何信息");
            return new AjaxReturn(false,"当前证件号码查不到任何信息");
        }else{
            //根据证件号码证件类型获取个人基本信息，有可能从健康档案接口获取
            Optional<CancerPersonInfo> cancerPersonInfo=Optional.of(screeningService.getBaseInfoByCardnoAndType(personcard,type));
            return new AjaxReturn(true,"",cancerPersonInfo.get());
        }
    }

    //新增时检查完身份证号以后重新刷新页面一次
    @RequestMapping(value = {"", "reform"}, method = RequestMethod.GET)
    public String reform(Model model, String manageId, String checkYear,String type,String personcardno) {

        List<DataOption> years = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            DataOption option = new DataOption();
            String year = DateUtils.formatDate(DateUtils.addYears(new Date(),i), "yyyy");
            option.setId(year);
            option.setText(year);
            years.add(option);
        }
        model.addAttribute("years", AuthUtils.toJson(years));
        model.addAttribute("checkYear", checkYear);

        //个人管理编号

        CancerPersonInfo personInfo=screeningService.getBaseInfoByCardnoAndType(personcardno,type);

        personInfo.setRegdoc(getSessionUser().getId());
        personInfo.setRegorg(getSessionUser().getOrgCode());
        personInfo.setRegdate(new Date());

        model.addAttribute("personInfo", personInfo);
//
//        if(manageId==null || manageId=="null" ||"null".equals(manageId)||"".equals(manageId)){
//            manageId=personInfo.getId();
//        }else
        if(manageId!=null && manageId!="null" &&!"null".equals(manageId)&&!"".equals(manageId)){
            ScreeningVo screeningVo=getDetail(manageId,checkYear);
            model.addAttribute("lucRisk", screeningVo.getLucRisk());
            model.addAttribute("scRisk", screeningVo.getScRisk());
            model.addAttribute("crcRisk", screeningVo.getCrcRisk());
            model.addAttribute("licRisk", screeningVo.getLicRisk());

            model.addAttribute("flag", "2"); //2:修改
            //各癌症数据库存在标志
            model.addAttribute("crcDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("licDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("scDbflag", "2");  //数据库状态  1：新增 2：修改
            model.addAttribute("lucDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("idNumber", personInfo.getIdNumber());
        }else{
            model.addAttribute("lucRisk", new LucRiskAssessment());
            model.addAttribute("scRisk", new ScRiskAssessment());
            model.addAttribute("crcRisk", new CrcRiskAssessment());
            model.addAttribute("licRisk", new LicRiskAssessment());

            model.addAttribute("flag", "1"); //1：新增
            //各癌症数据库存在标志
            model.addAttribute("crcDbflag", "1"); //数据库状态  1：新增 2：修改
            model.addAttribute("licDbflag", "1"); //数据库状态  1：新增 2：修改
            model.addAttribute("scDbflag", "1");  //数据库状态  1：新增 2：修改
            model.addAttribute("lucDbflag", "1"); //数据库状态  1：新增 2：修改
            model.addAttribute("idNumber", "");
        }

        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("lucFlag", lucFlag);
        return "/register/form";
    }


    public ScreeningVo getDetail(String manageid, String year){
        ScreeningVo screeningVo=new ScreeningVo();
        String crccheckid="";
        String liccheckid="";

        //大肠癌
        if(isOpen(crcFlag)){
            CrcRegcase crcRegcase=(CrcRegcase)crcRegcaseService.getByManageidAndYear(crcRegcaseDao,manageid,year);
            if(crcRegcase!=null){
                screeningVo.setCrcRegcase(crcRegcase);
                crccheckid=crcRegcase.getIdNumber();
            }

            CrcRiskAssessment crcRiskAssessment=(CrcRiskAssessment)crcRiskAssessmentService.getByCheckid(crcRiskAssessmentDao,crccheckid);
            if(crcRiskAssessment!=null){
                screeningVo.setCrcRisk(crcRiskAssessment);
            }
        }

        //肝癌
        if(isOpen(licFlag)){
            LicRegcase licRegcase=(LicRegcase)licRegcaseService.getByManageidAndYear(licRegcaseDao,manageid,year);
            if(licRegcase!=null){
                screeningVo.setLicRegcase(licRegcase);
                liccheckid=licRegcase.getId();
            }

            LicRiskAssessment licRiskAssessment=(LicRiskAssessment)licRiskAssessmentService.getByCheckid(licRiskAssessmentDao,liccheckid);
            if(licRiskAssessment!=null){
                screeningVo.setLicRisk(licRiskAssessment);
            }
        }

        //胃癌
        if(isOpen(scFlag)){
            ScRegcase scRegcase=(ScRegcase)scRegcaseService.getByManageidAndYear(scRegcaseDao,manageid,year);
            if(scRegcase!=null){
                screeningVo.setScRegcase(scRegcase);
            }

            ScRiskAssessment scRiskAssessment=(ScRiskAssessment)scRiskAssessmentService.getByCheckid(scRiskAssessmentDao,scRegcase.getId());
            if(scRiskAssessment!=null){
                screeningVo.setScRisk(scRiskAssessment);
            }

        }

        //肺癌
        if(isOpen(lucFlag)){
            LucRegcase lucRegcase=(LucRegcase)lucRegcaseService.getByManageidAndYear(lucRegcaseDao,manageid,year);
            if(lucRegcase!=null){
                screeningVo.setLucRegcase(lucRegcase);
            }

            LucRiskAssessment lucRiskAssessment=(LucRiskAssessment)lucRiskAssessmentService.getByCheckid(lucRiskAssessmentDao,lucRegcase.getId());
            if(lucRiskAssessment!=null){
                screeningVo.setLucRisk(lucRiskAssessment);
            }
        }

        //大肠癌便隐血检查表
        CrcFobt crcFobt=(CrcFobt)crcFobtService.getByCheckid(crcFobtDao,crccheckid);
        if(crcFobt!=null){
            screeningVo.setCrcFobt(crcFobt);
        }

        //肝癌辅助检查表
        LicAssistCheck licAssistCheck=(LicAssistCheck)licAssistCheckService.getByCheckid(licAssistCheckDao,liccheckid);
        if(licAssistCheck!=null){
            screeningVo.setLicCheck(licAssistCheck);
        }
        //危险度评估-癌症史表
        List<CancerHistory>cancerHistoryList=cancerHistoryService.getListByManageidAndYear(cancerHistoryDao,manageid,year);
        if(cancerHistoryList.size()>0){
            screeningVo.setHistoryList(cancerHistoryList);
        }
        //一级亲癌症史表
        List<FamilyCancerHistory>familyCancerHistoryList=familyCancerHistoryService.getListByManageidAndYear(familyCancerHistoryDao,manageid,year);
        if(familyCancerHistoryList.size()>0){
            screeningVo.setFamilyCancerHistoryList(familyCancerHistoryList);
        }

        //一级亲癌症史表徐汇肺癌
        List<LucFamilyCancerHistoryXH>lucFamilyCancerHistoryXHList=lucFamilyCancerHistoryXHService.getListByManageidAndYear(lucFamilyCancerHistoryXHDao,manageid,year);
        if(lucFamilyCancerHistoryXHList.size()>0){
            screeningVo.setLucFamilyCancerHistoryXHList(lucFamilyCancerHistoryXHList);
        }

        return screeningVo;
    }

    /**
     *新增时检查idnumber是否已经占用
     **/
    @RequestMapping(value = {"", "checkIdnumber"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxReturn checkIdnumber(String manageid, String idnumber){
        AjaxReturn ajaxReturn=new AjaxReturn();
        Boolean isChecked=false;
        String msg="该id已被占用！";
        if(crcRegcaseService.checkIdnumber(crcRegcaseDao,manageid,idnumber)==null||"".equals(crcRegcaseService.checkIdnumber(crcRegcaseDao,manageid,idnumber))){
            isChecked=true;
        }
        ajaxReturn.setOk(isChecked);
        if(!isChecked){
            ajaxReturn.setMsg(msg);
        }
        return ajaxReturn;
    }


    @RequestMapping(value = {"", "save"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional(readOnly = true)
    public AjaxReturn save(ScreeningVo screeningVo){

        CancerPersonInfo personInfo=screeningVo.getPersonInfo();
        User user=getSessionUser();

        if(personInfo.getId()==null){  //新增
            personInfo.setId(IdGen.uuid());
            personInfo.init(user.getId());
            screeningVo.getCrcRegcase().setManageid(personInfo.getId());
            screeningVo.getScRegcase().setManageid(personInfo.getId());
            screeningVo.getLicRegcase().setManageid(personInfo.getId());
            screeningVo.getLucRegcase().setManageid(personInfo.getId());

            cancerPersonInfoService.saveOrUpdate(personInfo,user.getId());

            if(isOpen(crcFlag)){
                crcRegcaseService.insert(crcRegcaseDao,screeningVo.getCrcRegcase());
                crcRiskAssessmentService.insert(crcRiskAssessmentDao,screeningVo.getCrcRisk());
            }
            if(isOpen(licFlag)){
                licRegcaseService.insert(licRegcaseDao,screeningVo.getLicRegcase());
                licRiskAssessmentService.insert(licRiskAssessmentDao,screeningVo.getLicRisk());
            }
            if(isOpen(lucFlag)){
                lucRegcaseService.insert(lucRegcaseDao,screeningVo.getLucRegcase());
                lucRiskAssessmentService.insert(lucRiskAssessmentDao,screeningVo.getLucRisk());
            }
            if(isOpen(scFlag)){
                scRegcaseService.insert(scRegcaseDao,screeningVo.getScRegcase());
                scRiskAssessmentService.insert(scRiskAssessmentDao,screeningVo.getScRisk());
            }
        }else{
            personInfo.initByUpdate(user.getId());
            cancerPersonInfoService.saveOrUpdate(personInfo,getSessionUser().getId());
            cancerPersonInfoService.updateChange(personInfo.getId());

            if(isOpen(crcFlag)){
                crcRegcaseService.update(crcRegcaseDao,screeningVo.getCrcRegcase());
                crcRiskAssessmentService.update(crcRiskAssessmentDao,screeningVo.getCrcRisk());
            }
            if(isOpen(licFlag)){
                licRegcaseService.update(licRegcaseDao,screeningVo.getLicRegcase());
                licRiskAssessmentService.update(licRiskAssessmentDao,screeningVo.getLicRisk());
            }
            if(isOpen(lucFlag)){
                lucRegcaseService.update(lucRegcaseDao,screeningVo.getLucRegcase());
                lucRiskAssessmentService.update(lucRiskAssessmentDao,screeningVo.getLucRisk());
            }
            if(isOpen(scFlag)){
                scRegcaseService.update(scRegcaseDao,screeningVo.getScRegcase());
                scRiskAssessmentService.update(scRiskAssessmentDao,screeningVo.getScRisk());
            }
        }

        return new AjaxReturn(true,"保存成功",personInfo.getId());
    }

}

