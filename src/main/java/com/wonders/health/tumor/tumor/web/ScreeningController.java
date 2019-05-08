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
import com.wonders.healthcloud.archive.client.entity.PersonInfo;
import freemarker.template.utility.DateUtil;
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

import java.lang.reflect.Field;
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

    @Value("${area_code}")
    private String areaCode;

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
        User user=getSessionUser();
        if(StringUtils.isBlank(checkYear)){
            checkYear= DateUtils.getYear();
        }

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
            personInfo.setRegdoc(user.getId());
            personInfo.setRegorg(user.getOrgCode());
            personInfo.setRegdate(new Date());

            model.addAttribute("personInfo", personInfo);
            model.addAttribute("lucRisk", new LucRiskAssessment(getSessionUser()));
            model.addAttribute("scRisk", new ScRiskAssessment(getSessionUser()));
            model.addAttribute("crcRisk", new CrcRiskAssessment(getSessionUser()));
            model.addAttribute("licRisk", new LicRiskAssessment(getSessionUser()));
            model.addAttribute("crcFobt", new CrcFobt(getSessionUser()));
            model.addAttribute("licCheck", new LicAssistCheck(getSessionUser()));

            model.addAttribute("crcRegcase", new CrcRegcase());
            model.addAttribute("licRegcase", new LicRegcase());
            model.addAttribute("lucRegcase", new LucRegcase());
            model.addAttribute("scRegcase", new ScRegcase());

            model.addAttribute("idNumber", "");
            model.addAttribute("flag", "1"); //1：新增
        } else {
            ScreeningVo screeningVo=getDetail(manageId,checkYear);
            model.addAttribute("personInfo", screeningVo.getPersonInfo());
            model.addAttribute("lucRisk", screeningVo.getLucRisk());
            model.addAttribute("scRisk", screeningVo.getScRisk());
            model.addAttribute("crcRisk", screeningVo.getCrcRisk());
            model.addAttribute("licRisk", screeningVo.getLicRisk());

            model.addAttribute("crcRegcase", screeningVo.getCrcRegcase());
            model.addAttribute("licRegcase", screeningVo.getLicRegcase());
            model.addAttribute("lucRegcase", screeningVo.getLucRegcase());
            model.addAttribute("scRegcase", screeningVo.getScRegcase());

            model.addAttribute("flag", "2"); //2:修改
            //各癌症数据库存在标志
            model.addAttribute("crcDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("licDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("scDbflag", "2");  //数据库状态  1：新增 2：修改
            model.addAttribute("lucDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("idNumber", screeningVo.getCrcRegcase().getIdNumber());
            model.addAttribute("flag", "2"); //2：修改
        }

        //各癌症数据库存在标志
        model.addAttribute("crcDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("licDbflag", "1"); //数据库状态  1：新增 2：修改
        model.addAttribute("scDbflag", "1");  //数据库状态  1：新增 2：修改
        model.addAttribute("lucDbflag", "1"); //数据库状态  1：新增 2：修改

        model.addAttribute("crcFlag", crcFlag);
        model.addAttribute("licFlag", licFlag);
        model.addAttribute("scFlag", scFlag);
        model.addAttribute("lucFlag", lucFlag);
        model.addAttribute("areaCode", areaCode);

        model.addAttribute("nowDate",DateUtils.formatDate(new Date(),"yyyy-mm-dd"));
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
            ScreeningVo sc=new ScreeningVo();
            sc.setPersonInfo(cancerPersonInfo.get());
            return new AjaxReturn(true,"",sc);
        }
    }

    //新增时检查完身份证号以后重新刷新页面一次
    @RequestMapping(value = {"", "reform"}, method = RequestMethod.GET)
    public String reform(Model model, String manageId, String checkYear,String type,String personcardno) {

        User user=getSessionUser();
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

        personInfo.setRegdoc(user.getId());
        personInfo.setRegorg(user.getOrgCode());
        personInfo.setRegdate(new Date());
        personInfo.setPersoncardType(type);
        personInfo.setPersoncard(personcardno);

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
        CancerPersonInfo cancerPersonInfo=cancerPersonInfoService.findById(manageid);
        if(cancerPersonInfo!=null){
            screeningVo.setPersonInfo(cancerPersonInfo);
        }

        //大肠癌
        if(isOpen(crcFlag)){
            CrcRegcase crcRegcase=(CrcRegcase)crcRegcaseService.getByManageidAndYear(crcRegcaseDao,manageid,year);
            if(crcRegcase!=null){
                if(crcRegcase.getCheckResult()=="1" || "1".equals(crcRegcase.getCheckResult())){
                    crcRegcase.setCheckResult("阴性");
                }else{
                    crcRegcase.setCheckResult("阳性");
                }
                screeningVo.setCrcRegcase(crcRegcase);
                crccheckid=crcRegcase.getId();
                CrcRiskAssessment crcRiskAssessment=(CrcRiskAssessment)crcRiskAssessmentService.getByCheckid(crcRiskAssessmentDao,crccheckid);
                if(crcRiskAssessment!=null){
                    if(crcRiskAssessment.getAssessmentResult()=="1" || "1".equals(crcRiskAssessment.getAssessmentResult())){
                        crcRiskAssessment.setAssessmentResult("阴性");
                    }else{
                        crcRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setCrcRisk(crcRiskAssessment);
                }
            }
        }

        //肝癌
        if(isOpen(licFlag)){
            LicRegcase licRegcase=(LicRegcase)licRegcaseService.getByManageidAndYear(licRegcaseDao,manageid,year);
            if(licRegcase!=null){
                if(licRegcase.getCheckResult()=="1" || "1".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("阴性");
                }else{
                    licRegcase.setCheckResult("阳性");
                }
                screeningVo.setLicRegcase(licRegcase);
                liccheckid=licRegcase.getId();
                LicRiskAssessment licRiskAssessment=(LicRiskAssessment)licRiskAssessmentService.getByCheckid(licRiskAssessmentDao,liccheckid);
                if(licRiskAssessment!=null){
                    if(licRiskAssessment.getAssessmentResult()=="1" || "1".equals(licRiskAssessment.getAssessmentResult())){
                        licRiskAssessment.setAssessmentResult("阴性");
                    }else{
                        licRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setLicRisk(licRiskAssessment);
                }
            }
        }

        //胃癌
        if(isOpen(scFlag)){
            ScRegcase scRegcase=(ScRegcase)scRegcaseService.getByManageidAndYear(scRegcaseDao,manageid,year);
            if(scRegcase!=null){
                if(scRegcase.getCheckResult()=="1" || "1".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("阴性");
                }else{
                    scRegcase.setCheckResult("阳性");
                }
                screeningVo.setScRegcase(scRegcase);
                ScRiskAssessment scRiskAssessment=(ScRiskAssessment)scRiskAssessmentService.getByCheckid(scRiskAssessmentDao,scRegcase.getId());
                if(scRiskAssessment!=null){
                    if(scRiskAssessment.getAssessmentResult()=="1" || "1".equals(scRiskAssessment.getAssessmentResult())){
                        scRiskAssessment.setAssessmentResult("阴性");
                    }else{
                        scRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setScRisk(scRiskAssessment);
                }
            }
        }

        //肺癌
        if(isOpen(lucFlag)){
            LucRegcase lucRegcase=(LucRegcase)lucRegcaseService.getByManageidAndYear(lucRegcaseDao,manageid,year);
            if(lucRegcase!=null){
                if(lucRegcase.getCheckResult()=="1" || "1".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("阴性");
                }else{
                    lucRegcase.setCheckResult("阳性");
                }
                screeningVo.setLucRegcase(lucRegcase);
                LucRiskAssessment lucRiskAssessment=(LucRiskAssessment)lucRiskAssessmentService.getByCheckid(lucRiskAssessmentDao,lucRegcase.getId());
                if(lucRiskAssessment!=null){
                    if(lucRiskAssessment.getAssessmentResult()=="1" || "1".equals(lucRiskAssessment.getAssessmentResult())){
                        lucRiskAssessment.setAssessmentResult("阴性");
                    }else{
                        lucRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setLucRisk(lucRiskAssessment);
                }
            }
        }

        //大肠癌便隐血检查表
        if(StringUtils.isNotBlank(crccheckid)){
            CrcFobt crcFobt=(CrcFobt)crcFobtService.getByCheckid(crcFobtDao,crccheckid);
            if(crcFobt!=null){
                screeningVo.setCrcFobt(crcFobt);
            }
        }
        if(StringUtils.isNotBlank(liccheckid)){
            //肝癌辅助检查表
            LicAssistCheck licAssistCheck=(LicAssistCheck)licAssistCheckService.getByCheckid(licAssistCheckDao,liccheckid);
            if(licAssistCheck!=null){
                screeningVo.setLicCheck(licAssistCheck);
            }
        }

        //危险度评估-癌症史表
        List<CancerHistory>cancerHistoryList=cancerHistoryService.getListByManageidAndYear(cancerHistoryDao,manageid,year);
        if(cancerHistoryList.size()>0){
            screeningVo.setHistoryList(cancerHistoryList);
        }
        //一级亲癌症史表
        List<FamilyCancerHistory>familyCancerHistoryList=familyCancerHistoryService.getListByPersonId(familyCancerHistoryDao,cancerPersonInfo.getId());
        if(familyCancerHistoryList.size()>0){
            screeningVo.setFamilyCancerHistoryList(familyCancerHistoryList);
        }

        //一级亲癌症史表徐汇肺癌
        List<LucFamilyCancerHistoryXH>lucFamilyCancerHistoryXHList=lucFamilyCancerHistoryXHService.getListByPersonId(lucFamilyCancerHistoryXHDao,cancerPersonInfo.getId());
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
        User user=getSessionUser();

        //基本信息处理
        CancerPersonInfo personInfo=screeningVo.getPersonInfo();
        CancerPersonInfo personInfoBase=screeningService.getBaseInfoByCardnoAndType(personInfo.getPersoncard(),personInfo.getPersoncardType());
        if(personInfoBase!=null){
            personInfo.setId(personInfoBase.getId());
        }
        if(personInfo.getId()==null){   //新增
            personInfo.init(user.getId());
            cancerPersonInfoService.saveOrUpdate(personInfo,user.getId());
        }else{                          //修改
            personInfo.initByUpdate(user.getId());
            cancerPersonInfoService.saveOrUpdate(personInfo,user.getId());
            cancerPersonInfoService.updateChange(personInfo.getId());
        }

        //处理插入表的癌症名称和癌症code
        List<CancerHistory> historyList=screeningVo.getHistoryList();  //本人癌症史表
        if(historyList!=null&& historyList.size()>0){
            historyList.stream().forEach(history->{
                history.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                history.setCancerName(getCancerName(history.getIcd10(),"60020"));
                history.setHospitalName(AuthUtils.getHospitalByCode(history.getHospitalCode()).getName());
            });
        }

        List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList=screeningVo.getLucFamilyCancerHistoryXHList();  //亲属历史表-徐汇
        List<FamilyCancerHistory> familyCancerHistoryList=screeningVo.getFamilyCancerHistoryList();                 //亲属历史表  不需做额外处理

        if((areaCode=="310104000000"||"310104000000".equals(areaCode))&&lucFamilyCancerHistoryXHList.size()>0&&lucFamilyCancerHistoryXHList!=null){
            lucFamilyCancerHistoryXHList.stream().forEach(family->{
                family.setCancerName(getCancerName(family.getIcd10(),"60020"));
            });
        }


        if(historyList!=null){
            historyList.stream().forEach(history->{
                if(StringUtils.isNotBlank(history.getId())){ //修改历史癌症表
                    history.setUpdateBy(user.getId());
                    if((areaCode=="310104000000"||"310104000000".equals(areaCode))){
                        history.setIschange("1");
                    }
                    cancerHistoryService.update(cancerHistoryDao,history);
                }else{                                        //插入历史癌症表
                    history.setId(IdGen.uuid());
                    history.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    history.setManageid(personInfo.getId());
                    history.setCreateBy(user.getId());
                    cancerHistoryService.insert(cancerHistoryDao,history);
                }
            });
        }

        if((areaCode=="310104000000"||"310104000000".equals(areaCode))&&lucFamilyCancerHistoryXHList.size()>0&&lucFamilyCancerHistoryXHList!=null){
            lucFamilyCancerHistoryXHList.stream().forEach(luc->{
                if(StringUtils.isNotBlank(luc.getId())){    //修改亲属历史表-徐汇
                    luc.setUpdateBy(user.getId());
                    lucFamilyCancerHistoryXHService.update(lucFamilyCancerHistoryXHDao,luc);
                }else{                                      //插入亲属历史表-徐汇
                    luc.setId(IdGen.uuid());
                    luc.setCheckId(user.getId());
                    luc.setCreateBy(user.getId());
                    lucFamilyCancerHistoryXHService.insert(lucFamilyCancerHistoryXHDao,luc);
                }

            });
        }else{
            if(familyCancerHistoryList!=null){
                familyCancerHistoryList.stream().forEach(family->{
                    if(StringUtils.isNotBlank(family.getId())){ //修改亲属历史表
                        family.setUpdateBy(user.getId());
                        familyCancerHistoryService.update(familyCancerHistoryDao,family);
                    }else{                                      //插入亲属历史表
                        family.setId(IdGen.uuid());
                        family.setCheckId(personInfo.getId());
                        family.setCreateBy(user.getId());
                        familyCancerHistoryService.insert(familyCancerHistoryDao,family);
                    }
                });
            }
        }

        if(isOpen(crcFlag)){
            CrcRegcase crcRegcase=screeningVo.getCrcRegcase();
            if(crcRegcase!=null  && !checkObjAllFieldsIsNull(crcRegcase)){
                if(crcRegcase.getCheckResult()=="阴性" || "阴性".equals(crcRegcase.getCheckResult())){
                    crcRegcase.setCheckResult("1");
                }else{
                    crcRegcase.setCheckResult("2");
                }
                if(crcRegcase.getId()!=null){
                    crcRegcaseService.update(crcRegcaseDao,crcRegcase);
                }else{
                    crcRegcase.setId(IdGen.uuid());
                    crcRegcase.setManageid(personInfo.getId());
                    crcRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    crcRegcase.setRegionCode(areaCode);
                    crcRegcase.setRegorg(user.getOrgCode());
                    crcRegcase.setRegdoc(user.getId());
                    crcRegcase.setRegdate(new Date());
                    crcRegcase.setSubmitsStatus("1");
                    crcRegcase.setCloseStatus("2");
                    crcRegcase.setCreateBy(user.getId());
                    crcRegcase.init();
                    crcRegcaseService.insert(crcRegcaseDao,crcRegcase);
                }
            }
            CrcRiskAssessment crcrisk=screeningVo.getCrcRisk();
            if(crcrisk!=null && !checkObjAllFieldsIsNull(crcrisk)){
                if(crcrisk.getCrcCheckId()!=null){
                    crcRiskAssessmentService.update(crcRiskAssessmentDao,crcrisk);
                }else{
                    crcrisk.setId(IdGen.uuid());
                    crcrisk.setCrcCheckId(crcRegcase.getId());
                    crcrisk.setAssessmentDocName(AuthUtils.getUserById(crcrisk.getAssessmentDoc()).getName());
                    crcrisk.setAssessmentDate(new Date());
                    crcrisk.setRiskQualityFlag("1");
                    crcrisk.setCreateBy(user.getId());
                    crcrisk.init();
                    crcRiskAssessmentService.insert(crcRiskAssessmentDao,crcrisk);
                }

            }
        }
        if(isOpen(licFlag)){
            LicRegcase licRegcase=screeningVo.getLicRegcase();
            if(licRegcase!=null && !checkObjAllFieldsIsNull(licRegcase)){
                if(licRegcase.getCheckResult()=="阴性" || "阴性".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("1");
                }else{
                    licRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(licRegcase.getManageid())){
                    licRegcaseService.update(licRegcaseDao,licRegcase);
                }else{
                    licRegcase.setId(IdGen.uuid());
                    licRegcase.setManageid(personInfo.getId());
                    licRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    licRegcase.setRegionCode(areaCode);
                    licRegcase.setRegorg(user.getOrgCode());
                    licRegcase.setRegdoc(user.getId());
                    licRegcase.setRegdate(new Date());
                    licRegcase.setSubmitsStatus("1");
                    licRegcase.setCloseStatus("2");
                    licRegcase.setCreateBy(user.getId());
                    licRegcase.init();
                    licRegcaseService.insert(licRegcaseDao,licRegcase);
                }
            }
            LicRiskAssessment licrisk=screeningVo.getLicRisk();
            if(licrisk!=null && !checkObjAllFieldsIsNull(licrisk)){
                if(StringUtils.isNotBlank(licrisk.getLicCheckId())){
                    licRiskAssessmentService.update(licRiskAssessmentDao,licrisk);
                }else{
                    licrisk.setId(IdGen.uuid());
                    licrisk.setLicCheckId(licRegcase.getId());
                    licrisk.setAssessmentDocName(AuthUtils.getUserById(licrisk.getAssessmentDoc()).getName());
                    licrisk.setAssessmentDate(new Date());
                    licrisk.setRiskQualityFlag("1");
                    licrisk.setCreateBy(user.getId());
                    licrisk.init();
                    licRiskAssessmentService.insert(licRiskAssessmentDao,licrisk);
                }

            }
        }
        if(isOpen(lucFlag)){
            LucRegcase lucRegcase=screeningVo.getLucRegcase();
            if(lucRegcase!=null  && !checkObjAllFieldsIsNull(lucRegcase)){
                if(lucRegcase.getCheckResult()=="阴性" || "阴性".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("1");
                }else{
                    lucRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(lucRegcase.getManageid())){
                    lucRegcaseService.update(lucRegcaseDao,lucRegcase);
                }else{
                    lucRegcase.setId(IdGen.uuid());
                    lucRegcase.setManageid(personInfo.getId());
                    lucRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    lucRegcase.setRegionCode(areaCode);
                    lucRegcase.setRegorg(user.getOrgCode());
                    lucRegcase.setRegdoc(user.getId());
                    lucRegcase.setRegdate(new Date());
                    lucRegcase.setSubmitsStatus("1");
                    lucRegcase.setCloseStatus("2");
                    lucRegcase.setCreateBy(user.getId());
                    lucRegcase.init();
                    lucRegcaseService.insert(lucRegcaseDao,lucRegcase);
                }
            }
            LucRiskAssessment lucrisk=screeningVo.getLucRisk();
            if(lucrisk!=null  && !checkObjAllFieldsIsNull(lucrisk)){
                if(StringUtils.isNotBlank(lucrisk.getLucCheckId())){
                    lucRiskAssessmentService.update(lucRiskAssessmentDao,lucrisk);
                }else{
                    lucrisk.setId(IdGen.uuid());
                    lucrisk.setLucCheckId(lucRegcase.getId());
                    lucrisk.setAssessmentDocName(AuthUtils.getUserById(lucrisk.getAssessmentDoc()).getName());
                    lucrisk.setAssessmentDate(new Date());
                    lucrisk.setRiskQualityFlag("1");
                    lucrisk.setCreateBy(user.getId());
                    lucrisk.init();
                    lucRiskAssessmentService.insert(lucRiskAssessmentDao,lucrisk);
                }

            }
        }
        if(isOpen(scFlag)){
            ScRegcase scRegcase=screeningVo.getScRegcase();
            if(scRegcase!=null && !checkObjAllFieldsIsNull(scRegcase) ){
                if(scRegcase.getCheckResult()=="阴性" || "阴性".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("1");
                }else{
                    scRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(scRegcase.getManageid())){
                    scRegcaseService.update(scRegcaseDao,scRegcase);
                }else{
                    scRegcase.setId(IdGen.uuid());
                    scRegcase.setManageid(personInfo.getId());
                    scRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    scRegcase.setRegionCode(areaCode);
                    scRegcase.setRegorg(user.getOrgCode());
                    scRegcase.setRegdoc(user.getId());
                    scRegcase.setRegdate(new Date());
                    scRegcase.setSubmitsStatus("1");
                    scRegcase.setCloseStatus("2");
                    scRegcase.setCreateBy(user.getId());
                    scRegcase.init();
                    scRegcaseService.insert(scRegcaseDao,scRegcase);
                }
            }

            ScRiskAssessment scrisk=screeningVo.getScRisk();
            if(scrisk!=null && !checkObjAllFieldsIsNull(scrisk)){
                if(StringUtils.isNotBlank(scrisk.getScCheckId())){
                    scRiskAssessmentService.update(scRiskAssessmentDao,scrisk);
                }else{
                    scrisk.setId(IdGen.uuid());
                    scrisk.setScCheckId(scRegcase.getId());
                    scrisk.setAssessmentDocName(AuthUtils.getUserById(scrisk.getAssessmentDoc()).getName());
                    scrisk.setAssessmentDate(new Date());
                    scrisk.setRiskQualityFlag("1");
                    scrisk.setCreateBy(user.getId());
                    scrisk.init();
                    scRiskAssessmentService.insert(scRiskAssessmentDao,screeningVo.getScRisk());
                }
            }
        }
        return new AjaxReturn(true,"保存成功",personInfo.getId());
    }

    //根据字典表 id 获取癌症名称
    private String getCancerName(String cancerType,String dicCode){
       return DictUtils.generalForMap(dicCode).get(cancerType).getName();
    }

    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }

        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}

