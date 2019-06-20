package com.wonders.health.tumor.tumor.web;

import com.google.common.collect.Lists;
import com.wonders.health.auth.client.vo.Hospital;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.utils.*;
import com.wonders.health.tumor.tumor.dao.*;
import com.wonders.health.tumor.tumor.entity.*;
import com.wonders.health.tumor.tumor.service.*;
import com.wonders.health.tumor.tumor.vo.ScreeningVo;
import com.wonders.healthcloud.archive.client.entity.PersonAddress;
import com.wonders.healthcloud.archive.client.entity.PersonInfo;
import com.wonders.healthcloud.archive.client.util.ArchiveUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

/**
 * 新增筛查登记Controller
 * welcome to the moutain of sh*t
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

    @Value("${archieve_add_flag}")
    private String archieveAddFlag;

    @Value("${area_code}")
    private String areaCode;


    @Value("${healthArchive}")
    private String healthArchive;

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

    @Autowired
    private CrcRegcaseIdService crcRegcaseIdService;

    @Autowired
    private CrcFobtRemindService crcFobtRemindService;

    @Autowired
    private CrcDiagCheckRemindService crcDiagCheckRemindService;

    @Autowired
    private LicDiagCheckRemindService licDiagCheckRemindService;

    @Autowired
    private ScDiagCheckRemindService scDiagCheckRemindService;
    @Autowired
    private LucDiagCheckRemindService lucDiagCheckRemindService;



    @RequestMapping(value = {"", "form"}, method = RequestMethod.GET)
    public String form(Model model, String manageId, String checkYear,String operation) {
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
        model.addAttribute("operation", operation);

        //个人管理编号
        if (StringUtils.isBlank(manageId)) {
            CancerPersonInfo personInfo = new CancerPersonInfo();
            personInfo.setRegdoc(user.getId());
            personInfo.setRegorg(user.getOrgCode());
            personInfo.setRegdate(new Date());
            personInfo.setPersoncardType("01");
            personInfo.setAddressCounty(areaCode);
            personInfo.setPaddressCounty(areaCode);

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

            model.addAttribute("historyListFlag", "2");

            model.addAttribute("idNumber", "");
        } else {
            ScreeningVo screeningVo=getDetail(manageId,checkYear);
            model.addAttribute("personInfo", screeningVo.getPersonInfo());
            model.addAttribute("lucRisk", screeningVo.getLucRisk());
            model.addAttribute("scRisk", screeningVo.getScRisk());
            model.addAttribute("crcRisk", screeningVo.getCrcRisk());
            model.addAttribute("licRisk", screeningVo.getLicRisk());

            model.addAttribute("crcFobt", screeningVo.getCrcFobt());
            model.addAttribute("licCheck",screeningVo.getLicCheck());

            model.addAttribute("crcRegcase", screeningVo.getCrcRegcase());
            model.addAttribute("licRegcase", screeningVo.getLicRegcase());
            model.addAttribute("lucRegcase", screeningVo.getLucRegcase());
            model.addAttribute("scRegcase", screeningVo.getScRegcase());

            model.addAttribute("historyList", screeningVo.getHistoryList());
            model.addAttribute("lucFamilyCancerHistoryXHList", screeningVo.getLucFamilyCancerHistoryXHList());
            model.addAttribute("familyCancerHistoryList", screeningVo.getFamilyCancerHistoryList());

            if(screeningVo.getHistoryList()!=null && screeningVo.getHistoryList().size()>0){
                model.addAttribute("historyListFlag", "1"); //
            }else{
                model.addAttribute("historyListFlag", "2"); //
            }
            if(screeningVo.getLucFamilyCancerHistoryXHList()!=null && screeningVo.getLucFamilyCancerHistoryXHList().size()>0){
                model.addAttribute("lucFamilyCancerHistoryXHListFlag", "1"); //
            }else{
                model.addAttribute("lucFamilyCancerHistoryXHListFlag", "2"); //
            }if(screeningVo.getFamilyCancerHistoryList()!=null && screeningVo.getFamilyCancerHistoryList().size()>0){
                model.addAttribute("familyCancerHistoryListFlag", "1"); //
            }else{
                model.addAttribute("familyCancerHistoryListFlag", "2"); //
            }

            //各癌症数据库存在标志
            model.addAttribute("crcDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("licDbflag", "2"); //数据库状态  1：新增 2：修改
            model.addAttribute("scDbflag", "2");  //数据库状态  1：新增 2：修改
            model.addAttribute("lucDbflag", "2"); //数据库状态  1：新增 2：修改
            if(screeningVo.getCrcRegcase()!=null){
                if(StringUtils.isNotBlank(screeningVo.getCrcRegcase().getIdNumber())){
                    model.addAttribute("idNumber", screeningVo.getCrcRegcase().getIdNumber());
                }
            }
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

        model.addAttribute("jgCode", user.getOrgCode());

        model.addAttribute("nowDate",DateUtils.formatDate(new Date(),"yyyy-mm-dd"));
        return "/register/form";
    }


    @RequestMapping(value = {"", "checkHadDone"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxReturn checkHadDone(String year, String type, String personcard){
        String[] cancers={crcFlag,licFlag,scFlag,lucFlag};
        Boolean xhFlag=false;
        if((areaCode=="310104000000"||"310104000000".equals(areaCode))){
            xhFlag=true;
        }
        String hadDone=screeningService.checkHadDone(year,type,personcard,cancers,xhFlag);    //true-做过  false-没做过

        if(hadDone=="done"||"done".equals(hadDone)){
            return new AjaxReturn(false,"此人本年度已经进行过筛查");
        }else if(hadDone=="1"||"1".equals(hadDone)){
            logger.error("当前证件号码查不到任何信息");
            return new AjaxReturn(false,"当前证件号码查不到任何信息");
        }else{
            //根据证件号码证件类型获取个人基本信息，有可能从健康档案接口获取
            Optional<CancerPersonInfo> cancerPersonInfo=Optional.of(screeningService.getBaseInfoByCardnoAndType(personcard,type));
            ScreeningVo sc=new ScreeningVo();
            CancerPersonInfo cancerPerson=cancerPersonInfo.get();
            if(StringUtils.isBlank(cancerPerson.getPersoncard())){
                cancerPerson=new CancerPersonInfo();
                cancerPerson.setIsNew("1");
                cancerPerson.setPersoncard(personcard);
                cancerPerson.setPersoncardType(type);
                cancerPerson.setPaddressProvince("310000000000");
                cancerPerson.setPaddressCity("310100000000");
                cancerPerson.setPaddressCounty(areaCode);
            }
            sc.setPersonInfo(cancerPerson);
            return new AjaxReturn(true,"",sc);
        }
    }

    /**
     *新增时检查idnumber是否已经占用
     **/
    @RequestMapping(value = {"", "checkIdnumber"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxReturn checkIdnumber(String manageid, String idnumber)throws Exception{
        AjaxReturn ajaxReturn=new AjaxReturn();
        String msg="";
        Boolean isChecked=false;

        String idyear=idnumber.substring(0,2);      //前两位为年份
        String areacode=idnumber.substring(2,7);    //之后3位为107 2位社区代码

        String now= String.valueOf(LocalDate.now().getYear());
        String year=now.substring(2,4);

        CrcRegcaseId dic=crcRegcaseIdService.getByJgcode(getSessionUser().getOrgCode());


        if((!(idyear.equals(year)))||!(areacode.equals(dic.getCode()))){
            msg="该大肠癌id格式不正确！";
        }else{
            if(crcRegcaseService.checkIdnumber(crcRegcaseDao,manageid,idnumber)==null||(crcRegcaseService.checkIdnumber(crcRegcaseDao,manageid,idnumber).size()==0)){
                isChecked=true;
            }else{
                msg="该id已被占用！";
            }
        }
        ajaxReturn.setOk(isChecked);
        ajaxReturn.setMsg(msg);

        return ajaxReturn;
    }


    @RequestMapping(value = {"", "save"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional(readOnly = true)
    public AjaxReturn save(ScreeningVo screeningVo){
        User user=getSessionUser();

        //基本信息处理
        CancerPersonInfo personInfo=screeningVo.getPersonInfo();
        if(StringUtils.isBlank(personInfo.getId())){
            CancerPersonInfo personInfoBase=screeningService.getBaseInfoByCardnoAndType(personInfo.getPersoncard(),personInfo.getPersoncardType());
            if(StringUtils.isNotBlank(personInfoBase.getId())){
                personInfo.setId(personInfoBase.getId());
            }
        }
        if(personInfo.getId()==null|| "".equals(personInfo.getId()) || personInfo.getPersoncardType()=="02" || "02".equals( personInfo.getPersoncardType())){   //新增
            String isNew=personInfo.getIsNew();
            if(StringUtils.isNotBlank(isNew) && !("02".equals( personInfo.getPersoncardType()))){  //这个人应该调用健康档案接口
                PersonInfo person=new PersonInfo();

                BeanUtils.copyProperties(personInfo, person);
                person.setPersoncardNo(personInfo.getPersoncard());
                person.setPhone(personInfo.getTelephone());
                person.setMobilePhone(personInfo.getMobile());
                person.setNationOther(personInfo.getNationOther());

                person.setMarriage(personInfo.getMarriage());
                person.setEducation(personInfo.getEducation());
                person.setMedicalPaymentcode(personInfo.getPaymentSituation());
                person.setPaddressOther(personInfo.getPaddressDetail());

                List<PersonAddress> personAddressList = new ArrayList<>();
                if(personAddressList!=null && personAddressList.size() > 0 ){
                    //居住地址
                    personAddressList.stream().filter((personAddress) -> "1".equals(personAddress.getIscurrentuse()))
                            .forEach(personAddress -> {
                                personAddress.setProvince(personInfo.getAddressProvince());
                                personAddress.setCity(personInfo.getAddressCity());
                                personAddress.setCounty(personInfo.getAddressCounty());
                                personAddress.setTown(personInfo.getAddressTown());
                                personAddress.setCommittee(personInfo.getAddressCommittee());
                                personAddress.setOther(personInfo.getAddressDetail());
                            });
                }
                person.setAddresses(personAddressList);
                if(archieveAddFlag=="1"|| "1".equals(archieveAddFlag)){
                    ArchiveUtil.submitArchive(healthArchive + "/api/submit", person);
                }
            }
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
                if(StringUtils.isNotBlank(history.getIcd10())){
                    history.setCancerName(getCancerName(history.getIcd10(),"60020"));
                }
                if(StringUtils.isNotBlank(history.getHospitalCode())){
                    history.setHospitalName(AuthUtils.getHospitalByCode(history.getHospitalCode()).getName());
                }

            });
        }

        List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList=screeningVo.getLucFamilyCancerHistoryXHList();  //亲属历史表-徐汇
        List<FamilyCancerHistory> familyCancerHistoryList=screeningVo.getFamilyCancerHistoryList();                 //亲属历史表  不需做额外处理

        if((areaCode=="310104000000"||"310104000000".equals(areaCode))){
            if(lucFamilyCancerHistoryXHList!=null&&lucFamilyCancerHistoryXHList.size()>0){
                screeningVo.getLucRisk().setQinshuAizhengshi("1");
                lucFamilyCancerHistoryXHList.stream().forEach(family->{
                    if(family!=null  &&(StringUtils.isNotBlank(family.getRelation())|| family.getAge()!=null || family.getLived()!=null )){
                        if(StringUtils.isNotBlank(family.getIcd10())){
                            family.setCancerName(getCancerName(family.getIcd10(),"60020"));
                        }
                    }
                });
            }else{
                screeningVo.getLucRisk().setQinshuAizhengshi("2");
            }
        }

        cancerHistoryService.deleteAllByPersonId(cancerHistoryDao,personInfo.getId()); //全删本人癌症史数据
        if(historyList!=null){
            screeningVo.getLucRisk().setAizhengshi("1");
            screeningVo.getCrcRisk().setAizhengshi("1");
            screeningVo.getLicRisk().setAizhengshi("1");
            screeningVo.getScRisk().setAizhengshi("1");

            historyList.stream().forEach(history->{
                if(history!=null && (StringUtils.isNotBlank(history.getIcd10())||history.getAge()!=null
                        || StringUtils.isNotBlank(history.getHospitalCode()))){                                        //插入历史癌症表
                    history.setId(IdGen.uuid());
                    history.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    if((areaCode=="310104000000"||"310104000000".equals(areaCode))){
                        if(isOpen(crcFlag)){
                            if(personInfo.getId()!=null){
                                history.setIschange("2");
                            }else{
                                history.setIschange("1");
                            }
                        }
                    }
                    history.setManageid(personInfo.getId());
                    history.setCreateBy(user.getId());
                    history.setUpdateBy(user.getId());
                    cancerHistoryService.insert(cancerHistoryDao,history);
                }
            });
        }

        if((areaCode=="310104000000"||"310104000000".equals(areaCode))){//插入亲属历史表-徐汇
            lucFamilyCancerHistoryXHService.deleteAllByPersonId(lucFamilyCancerHistoryXHDao,personInfo.getId());
            if(lucFamilyCancerHistoryXHList!=null&&lucFamilyCancerHistoryXHList.size()>0){
                lucFamilyCancerHistoryXHList.stream().forEach(luc->{

                    if(luc!=null &&(StringUtils.isNotBlank(luc.getRelation())||luc.getAge()!=null ||luc.getLived()!=null )){
                        luc.setId(IdGen.uuid());
                        luc.setCheckId(personInfo.getId());
                        luc.setCreateBy(user.getId());
                        if(personInfo.getId()!=null){
                            luc.setUpdateBy(user.getId());
                        }
                        if(StringUtils.isNotBlank(luc.getIcd10())){
                            luc.setCancerName(getCancerName(luc.getIcd10(),"60020"));
                        }
                        luc.setUpdateBy(user.getId());
                        lucFamilyCancerHistoryXHService.insert(lucFamilyCancerHistoryXHDao,luc);
                    }

                });
            }
        }else{                                                              //插入亲属历史表-非徐汇
            familyCancerHistoryService.deleteAllByPersonId(familyCancerHistoryDao,personInfo.getId());
            if(familyCancerHistoryList!=null){
                familyCancerHistoryList.stream().forEach(family->{
                    if(family!=null &&(StringUtils.isNotBlank(family.getRelation())|| family.getAge()!=null || family.getLived()!=null )){ //插入亲属历史表
                        family.setId(IdGen.uuid());
                        family.setCheckId(personInfo.getId());
                        family.setCreateBy(user.getId());
                        family.setIschange("2");
                        family.setUpdateBy(user.getId());
                        familyCancerHistoryService.insert(familyCancerHistoryDao,family);
                    }
                });
            }
        }

        if(isOpen(crcFlag)){
            CrcRegcase crcRegcase=screeningVo.getCrcRegcase();
            if(crcRegcase!=null  && !checkObjAllFieldsIsNull(crcRegcase)){
                crcRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                if(crcRegcase.getCheckResult()=="阴性" || "阴性".equals(crcRegcase.getCheckResult())){
                    crcRegcase.setCheckResult("1");
                }else  if(crcRegcase.getCheckResult()=="阳性" || "阳性".equals(crcRegcase.getCheckResult())){
                    crcRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(crcRegcase.getId())){
                    if(personInfo.getId()!=null){
                        crcRegcase.setIschange("2");
                    }else{
                        crcRegcase.setIschange("1");
                    }
                    crcRegcaseService.update(crcRegcaseDao,crcRegcase);
                }else{
                    crcRegcase.setId(IdGen.uuid());
                    crcRegcase.setManageid(personInfo.getId());
                    crcRegcase.setRegionCode(areaCode);
                    crcRegcase.setRegorg(user.getOrgCode());
                    crcRegcase.setRegdoc(user.getId());
                    crcRegcase.setRegdate(new Date());
                    crcRegcase.setSubmitsStatus("1");
                    crcRegcase.setCloseStatus("2");
                    crcRegcase.setCreateBy(user.getId());
                    crcRegcase.setIschange("2");
                    crcRegcase.init();
                    crcRegcaseService.insert(crcRegcaseDao,crcRegcase);
                }
            }

            CrcRiskAssessment crcrisk=screeningVo.getCrcRisk();
            if(crcrisk!=null && !checkObjAllFieldsIsNull(crcrisk)){
                crcrisk.setAssessmentDoc(screeningVo.getPersonInfo().getRegdoc());
                crcrisk.setAssessmentDocName(AuthUtils.getUserById(screeningVo.getPersonInfo().getRegdoc()).getName());
                if(crcrisk.getAssessmentResult()=="阴性" || "阴性".equals(crcrisk.getAssessmentResult())){
                    crcrisk.setAssessmentResult("1");
                }else  if(crcrisk.getAssessmentResult()=="阳性" || "阳性".equals(crcrisk.getAssessmentResult())){
                    crcrisk.setAssessmentResult("2");
                }

                if(StringUtils.isNotBlank(crcrisk.getId())){
                    crcRiskAssessmentService.update(crcRiskAssessmentDao,crcrisk);
                }else{
                    crcrisk.setId(IdGen.uuid());
                    crcrisk.setCrcCheckId(crcRegcase.getId());
                    crcrisk.setRiskQualityFlag("1");
                    crcrisk.setCreateBy(user.getId());
                    crcrisk.setAssessmentDate(new Date());
                    crcrisk.init();
                    crcRiskAssessmentService.insert(crcRiskAssessmentDao,crcrisk);
                }
            }

        }
        if(isOpen(licFlag)){
            LicRegcase licRegcase=screeningVo.getLicRegcase();
            if(licRegcase!=null && !checkObjAllFieldsIsNull(licRegcase)){
                licRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                if(licRegcase.getCheckResult()=="阴性" || "阴性".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("1");
                }else  if(licRegcase.getCheckResult()=="阳性" || "阳性".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(licRegcase.getId())){
                    licRegcaseService.update(licRegcaseDao,licRegcase);
                }else{
                    licRegcase.setId(IdGen.uuid());
                    licRegcase.setManageid(personInfo.getId());
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

                //处理新需求
                if("2".equals(licRegcase.getCheckResult())||(licRegcase.getCheckResult()=="2")){
                    LicDiagCheckRemind licDiagCheckRemind=licDiagCheckRemindService.findByCheckId(licRegcase.getId());
                    if(licDiagCheckRemind==null || StringUtils.isBlank(licDiagCheckRemind.getId())){    //新增时处理
                        licDiagCheckRemind=new LicDiagCheckRemind();
                        licDiagCheckRemind.setCreateDate(new Date());
                        licDiagCheckRemind.init();
                    }else{
                        licDiagCheckRemind.setUpdateBy(user.getId());
                        licDiagCheckRemind.setUpdateDate(new Date());
                    }
                    licDiagCheckRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    licDiagCheckRemind.setLicCheckId(licRegcase.getId());

                    licDiagCheckRemind.setRemindStatus("01");
                    licDiagCheckRemind.setPerRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),30)));
                    licDiagCheckRemindService.saveOrUpdate(licDiagCheckRemind,user.getId());
                }

            }
            LicRiskAssessment licrisk=screeningVo.getLicRisk();
            if(licrisk!=null && !checkObjAllFieldsIsNull(licrisk)){
                licrisk.setAssessmentDoc(screeningVo.getPersonInfo().getRegdoc());
                licrisk.setAssessmentDocName(AuthUtils.getUserById(screeningVo.getPersonInfo().getRegdoc()).getName());
                if(licrisk.getAssessmentResult()=="阴性" || "阴性".equals(licrisk.getAssessmentResult())){
                    licrisk.setAssessmentResult("1");
                }else  if(licrisk.getAssessmentResult()=="阳性" || "阳性".equals(licrisk.getAssessmentResult())){
                    licrisk.setAssessmentResult("2");
                }
                if(StringUtils.isNotBlank(licrisk.getId())){
                    licRiskAssessmentService.update(licRiskAssessmentDao,licrisk);
                }else{
                    licrisk.setId(IdGen.uuid());
                    licrisk.setLicCheckId(licRegcase.getId());
                    licrisk.setRiskQualityFlag("1");
                    licrisk.setCreateBy(user.getId());
                    licrisk.setAssessmentDate(new Date());
                    licrisk.init();
                    licRiskAssessmentService.insert(licRiskAssessmentDao,licrisk);
                }
            }
        }
        if(isOpen(lucFlag)){
            LucRegcase lucRegcase=screeningVo.getLucRegcase();
            lucRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
            if(lucRegcase!=null  && !checkObjAllFieldsIsNull(lucRegcase)){
                if(lucRegcase.getCheckResult()=="阴性" || "阴性".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("1");
                }else if(lucRegcase.getCheckResult()=="阳性" || "阳性".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(lucRegcase.getId())){
                    lucRegcaseService.update(lucRegcaseDao,lucRegcase);
                }else{
                    lucRegcase.setId(IdGen.uuid());
                    lucRegcase.setManageid(personInfo.getId());
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
                //处理肺癌新需求
                if("2".equals(lucRegcase.getCheckResult())||(lucRegcase.getCheckResult()=="2")){
                    LucDiagCheckRemind lucDiagCheckRemind=lucDiagCheckRemindService.findByCheckId(lucRegcase.getId());
                    if(lucDiagCheckRemind==null || StringUtils.isBlank(lucDiagCheckRemind.getId())){
                        lucDiagCheckRemind=new LucDiagCheckRemind();
                        lucDiagCheckRemind.setCreateDate(new Date());
                        lucDiagCheckRemind.init();
                    }else{
                        lucDiagCheckRemind.setUpdateBy(user.getId());
                        lucDiagCheckRemind.setUpdateDate(new Date());
                    }

                    lucDiagCheckRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    lucDiagCheckRemind.setLucCheckId(lucRegcase.getId());

                    lucDiagCheckRemind.setRemindStatus("01");
                    lucDiagCheckRemind.setPerRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),30)));
                    lucDiagCheckRemindService.saveOrUpdate(lucDiagCheckRemind,user.getId());
                }
            }
            LucRiskAssessment lucrisk=screeningVo.getLucRisk();
            if(lucrisk!=null  && !checkObjAllFieldsIsNull(lucrisk)){
                lucrisk.setAssessmentDoc(screeningVo.getPersonInfo().getRegdoc());
                lucrisk.setAssessmentDocName(AuthUtils.getUserById(screeningVo.getPersonInfo().getRegdoc()).getName());

                if(lucrisk.getAssessmentResult()=="阴性" || "阴性".equals(lucrisk.getAssessmentResult())){
                    lucrisk.setAssessmentResult("1");
                }else  if(lucrisk.getAssessmentResult()=="阳性" || "阳性".equals(lucrisk.getAssessmentResult())){
                    lucrisk.setAssessmentResult("2");
                }
                if(StringUtils.isNotBlank(lucrisk.getId())){
                    lucRiskAssessmentService.update(lucRiskAssessmentDao,lucrisk);
                }else{
                    lucrisk.setId(IdGen.uuid());
                    lucrisk.setLucCheckId(lucRegcase.getId());
                    lucrisk.setRiskQualityFlag("1");
                    lucrisk.setCreateBy(user.getId());
                    lucrisk.setAssessmentDate(new Date());
                    lucrisk.init();
                    lucRiskAssessmentService.insert(lucRiskAssessmentDao,lucrisk);
                }
            }


        }
        if(isOpen(scFlag)){
            ScRegcase scRegcase=screeningVo.getScRegcase();
            if(scRegcase!=null && !checkObjAllFieldsIsNull(scRegcase) ){
                scRegcase.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                if(scRegcase.getCheckResult()=="阴性" || "阴性".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("1");
                }else if(scRegcase.getCheckResult()=="阳性" || "阳性".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("2");
                }
                if(StringUtils.isNotBlank(scRegcase.getId())){
                    scRegcaseService.update(scRegcaseDao,scRegcase);
                }else{
                    scRegcase.setId(IdGen.uuid());
                    scRegcase.setManageid(personInfo.getId());
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

                //处理胃癌新需求
                if("2".equals(scRegcase.getCheckResult())||(scRegcase.getCheckResult()=="2")){
                    ScDiagCheckRemind scDiagCheckRemind=scDiagCheckRemindService.findByCheckId(scRegcase.getId());
                    if(scDiagCheckRemind==null || StringUtils.isBlank(scDiagCheckRemind.getId())){
                        scDiagCheckRemind=new ScDiagCheckRemind();
                        scDiagCheckRemind.setCreateDate(new Date());
                        scDiagCheckRemind.init();
                    }else{
                        scDiagCheckRemind.setUpdateBy(user.getId());
                        scDiagCheckRemind.setUpdateDate(new Date());
                    }
                    scDiagCheckRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                    scDiagCheckRemind.setScCheckId(scRegcase.getId());

                    scDiagCheckRemind.setRemindStatus("01");
                    scDiagCheckRemind.setPerRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),30)));
                    scDiagCheckRemindService.saveOrUpdate(scDiagCheckRemind,user.getId());
                }
            }

            ScRiskAssessment scrisk=screeningVo.getScRisk();
            if(scrisk!=null && !checkObjAllFieldsIsNull(scrisk)){
                scrisk.setAssessmentDoc(screeningVo.getPersonInfo().getRegdoc());
                scrisk.setAssessmentDocName(AuthUtils.getUserById(screeningVo.getPersonInfo().getRegdoc()).getName());

                if(scrisk.getAssessmentResult()=="阴性" || "阴性".equals(scrisk.getAssessmentResult())){
                    scrisk.setAssessmentResult("1");
                }else  if(scrisk.getAssessmentResult()=="阳性" || "阳性".equals(scrisk.getAssessmentResult())){
                    scrisk.setAssessmentResult("2");
                }
                if(StringUtils.isNotBlank(scrisk.getId())){
                    scRiskAssessmentService.update(scRiskAssessmentDao,scrisk);
                }else{
                    scrisk.setId(IdGen.uuid());
                    scrisk.setScCheckId(scRegcase.getId());
                    scrisk.setRiskQualityFlag("1");
                    scrisk.setCreateBy(user.getId());
                    scrisk.setAssessmentDate(new Date());
                    scrisk.init();
                    scRiskAssessmentService.insert(scRiskAssessmentDao,screeningVo.getScRisk());
                }
            }

        }



        CrcFobt crcFobt=screeningVo.getCrcFobt();
        if(!checkObjAllFieldsIsNull(crcFobt)){
            if(crcFobt.getFobtResult()=="阴性" || "阴性".equals(crcFobt.getFobtResult())){
                crcFobt.setFobtResult("1");
            }else if(crcFobt.getFobtResult()=="阳性" || "阳性".equals(crcFobt.getFobtResult())){
                crcFobt.setFobtResult("2");
            }
            if(StringUtils.isBlank(crcFobt.getId())){
                crcFobt.setId(IdGen.uuid());
                crcFobt.setCrcCheckId(screeningVo.getCrcRegcase().getId());
                crcFobt.setFobtDocName(AuthUtils.getUserById(crcFobt.getFobtDoc()).getName());
                crcFobt.setCreateBy(user.getId());
                crcFobt.init();
                crcFobtService.insert(crcFobtDao,crcFobt);
            }else{
                crcFobt.setFobtDocName(AuthUtils.getUserById(crcFobt.getFobtDoc()).getName());
                crcFobt.setUpdateBy(user.getId());
                crcFobt.initByUpdate();
                crcFobtService.update(crcFobtDao,crcFobt);
            }


            //处理新需求  往大肠癌便隐血检查提醒表插入数
            if(isOpen(crcFlag)){
                CrcDiagCheckRemind crcCheckRemind=crcDiagCheckRemindService.findByCheckId(screeningVo.getCrcRegcase().getId()); //诊断检查提醒表记录
                CrcFobtRemind fobtRemind=crcFobtRemindService.findByCheckId(screeningVo.getCrcRegcase().getId());   //大肠癌便隐血检查提醒表

                //若大肠癌便隐血检查提醒表.提醒状态 != 完成提醒的场合
                if( fobtRemind==null || fobtRemind.getFobtRemindStatus()!="04"){
                    if(fobtRemind==null||StringUtils.isBlank(fobtRemind.getId())){
                        fobtRemind=new CrcFobtRemind();
                        fobtRemind.setCreateDate(new Date());
                        fobtRemind.init();
                    }else{
                        fobtRemind.setUpdateBy(user.getId());
                        fobtRemind.setUpdateDate(new Date());
                    }

                    fobtRemind.setCrcCheckId(screeningVo.getCrcRegcase().getId());
                    fobtRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));

                    if(StringUtils.isNotBlank(crcFobt.getFirstFobtResult())&&(StringUtils.isNotBlank(crcFobt.getSecondFobtResult()))){ //两次都做
                        if("2".equals(crcFobt.getFobtResult())){//若大肠癌初筛结果为阳性  大肠癌诊断结果提醒表插入数据
                            if(crcCheckRemind==null || StringUtils.isBlank(crcCheckRemind.getId())){
                                crcCheckRemind=new CrcDiagCheckRemind();
                                crcCheckRemind.setCrcCheckId(screeningVo.getCrcRegcase().getId());
                                crcCheckRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                                crcCheckRemind.setCreateDate(new Date());
                            }
                            crcCheckRemind.setRemindStatus("01");
                            crcCheckRemind.setPerRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),30)));
                            crcCheckRemind.init();
                            crcDiagCheckRemindService.saveOrUpdate(crcCheckRemind,user.getId());
                        }
                        fobtRemind.setFobtRemindStatus("02");
                        crcFobtRemindService.saveOrUpdate(fobtRemind,user.getId());  //提醒表插入数据
                    }else if(StringUtils.isNotBlank(crcFobt.getFirstFobtResult())&&(StringUtils.isBlank(crcFobt.getSecondFobtResult()))){  //只做第一次 新增修改都一样
                        fobtRemind.setFobtRemindStatus("01");
                        fobtRemind.setPerFobtRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),7)));
                        crcFobtRemindService.saveOrUpdate(fobtRemind,user.getId());
                    }else{  //两次都没做
                        if(fobtRemind!=null){
                            fobtRemind.setFirstFobtRemindDate(null);
                            fobtRemind.setFirstFobtRemindType(null);
                            fobtRemind.setSecondFobtRemindDate(null);
                            fobtRemind.setSecondFobtRemindType(null);
                        }
                        fobtRemind.setFobtRemindStatus("01");
                        fobtRemind.setPerFobtRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),7)));
                        crcFobtRemindService.saveOrUpdate(fobtRemind,user.getId());
                    }
                }
            }

        }else{  //为空,无便隐血检查结果  这里是为了防止没有做结果，一般不会使用
            if(isOpen(crcFlag)){
                CrcFobtRemind crcFobtRemind=new CrcFobtRemind();
                crcFobtRemind.setCrcCheckId(screeningVo.getCrcRegcase().getIdNumber());
                crcFobtRemind.setCheckYear(Integer.valueOf(screeningVo.getCheckYear()));
                crcFobtRemind.setCreateDate(new Date());
                crcFobtRemind.setFobtRemindStatus("01");
                crcFobtRemind.setPerFobtRemindDate(DateUtils.parseDate(DateUtils.beforNumberDay(new Date(),7)));
                crcFobtRemindService.saveOrUpdate(crcFobtRemind,user.getId());
            }
        }


        LicAssistCheck licAssistCheck=screeningVo.getLicCheck();
        if(!checkObjAllFieldsIsNull(licAssistCheck)){
            licAssistCheck.setLicAssistDocName(AuthUtils.getUserById(crcFobt.getFobtDoc()).getName());
            if(licAssistCheck.getLicAssistResult()=="阴性" || "阴性".equals(licAssistCheck.getLicAssistResult())){
                licAssistCheck.setLicAssistResult("1");
            }else if(licAssistCheck.getLicAssistResult()=="阳性" || "阳性".equals(licAssistCheck.getLicAssistResult())){
                licAssistCheck.setLicAssistResult("2");
            }

            if(StringUtils.isBlank(licAssistCheck.getId())){
                licAssistCheck.setId(IdGen.uuid());
                licAssistCheck.setLicCheckId(screeningVo.getLicRegcase().getId());
                licAssistCheck.setCreateBy(user.getId());
                licAssistCheck.init();
                licAssistCheckService.insert(licAssistCheckDao,licAssistCheck);
            }else{
                licAssistCheck.setUpdateBy(user.getId());
                licAssistCheck.initByUpdate();
                licAssistCheckService.update(licAssistCheckDao,licAssistCheck);
            }

        }

        return new AjaxReturn(true,"保存成功",personInfo.getId());
    }

    @RequestMapping(value = {"", "deleteSelfHistory"}, method = RequestMethod.GET)
    @ResponseBody
    public String deleteSelfHistory(String id,String manageId,String checkYear){
        try{
            if(StringUtils.isNotBlank(id)){
                cancerHistoryService.deleteById(id);
                if(StringUtils.isNotBlank(manageId)){
                    cancerHistoryService.isChange(manageId,checkYear);
                }
            }
            return "1";
        }catch (Exception e){
            return "2";
        }
    }

    @RequestMapping(value = {"", "deleteFamHistory"}, method = RequestMethod.GET)
    @ResponseBody
    public String deleteFamHistory(String id,String checkId){
        try{
            if(StringUtils.isNotBlank(id)){
                familyCancerHistoryService.deleteById(id);
                if(StringUtils.isNotBlank(checkId)){
                    familyCancerHistoryService.isChange(checkId);
                }
            }
            return "1";
        }catch (Exception e){
            return "2";
        }
    }

    @RequestMapping(value = {"", "printSuggest"}, method = RequestMethod.GET)
    public String printSuggest(Model model,String name,String aizhengs,String byx,String gtp,String hbs,String afp,String bus){
        model.addAttribute("name",name);
        model.addAttribute("byx",byx);
        model.addAttribute("gtp",gtp);
        model.addAttribute("hbs",hbs);
        model.addAttribute("afp",afp);
        if(bus=="1" || bus=="01"||"1".equals(bus)||"01".equals(bus)){
            bus="阴性";
        } if(bus=="2" || bus=="02" ||"2".equals(bus)||"02".equals(bus)){
            bus="阳性";
        }
        model.addAttribute("bus",bus);
        model.addAttribute("curtime",DateUtils.getDate("yyyy-MM-dd"));
        String aizheng="";
        if(StringUtils.isNotBlank(aizhengs)){
            if(aizhengs.contains("1")){
                aizheng+="大肠癌";
                model.addAttribute("dca",1);
            }
            if(aizhengs.contains("2")){
                if(StringUtils.isNotBlank(aizheng)){
                    aizheng+="，";
                }
                aizheng+="肝癌";
            }
            if(aizhengs.contains("3")){
                if(StringUtils.isNotBlank(aizheng)){
                    aizheng+="，";
                }
                aizheng+="胃癌";
                model.addAttribute("wa",1);
            }
            if(aizhengs.contains("4")){
                if(StringUtils.isNotBlank(aizheng)){
                    aizheng+="，";
                }
                aizheng+="肺癌";
                model.addAttribute("fa",1);
            }
        }
        model.addAttribute("aizheng",aizheng);
        User user=getSessionUser();
        Hospital hos=AuthUtils.getHospitalByCode(user.getOrgCode());

        if(hos!=null){
            String hosname=hos.getName();
            if(hosname.contains("社区卫生服务中心")){
                hosname=hosname.replace("社区卫生服务中心","");
            }
            model.addAttribute("hosName",hosname);
        }

        model.addAttribute("flag",1);
        return "/register/printSuggest";
    }


    @RequestMapping(value = {"", "printSuggest1"}, method = RequestMethod.GET)
    public ModelAndView printSuggest1(Model model,String name,String aizhengs,String byx,String gtp,String hbs,String afp,String bus){
        ModelAndView mav = new ModelAndView("/register/printSuggestPage");
        model.addAttribute("name",name);
        model.addAttribute("byx",byx);
        model.addAttribute("gtp",gtp);
        model.addAttribute("hbs",hbs);
        model.addAttribute("afp",afp);
        model.addAttribute("year",LocalDate.now().getYear());
        model.addAttribute("month",LocalDate.now().getMonthValue());
        model.addAttribute("day",LocalDate.now().getDayOfMonth());
        if(bus=="1" || bus=="01"||"1".equals(bus)||"01".equals(bus)){
            bus="阴性";
        } if(bus=="2" || bus=="02" ||"2".equals(bus)||"02".equals(bus)){
            bus="阳性";
        }
        model.addAttribute("bus",bus);
        model.addAttribute("curtime",DateUtils.getDate("yyyy-MM-dd"));

        model.addAttribute("aizheng",aizhengs);
        User user=getSessionUser();
        Hospital hos=AuthUtils.getHospitalByCode(user.getOrgCode());

        if(hos!=null){
            String hosname=hos.getName();
            if(hosname.contains("社区卫生服务中心")){
                hosname=hosname.replace("社区卫生服务中心","");
            }
            model.addAttribute("hosName",hosname);
        }

        model.addAttribute("flag",2);
        return mav;
    }



    //简化计算flag成boolean
    private Boolean isOpen(String flag){
        if(flag.equals("1")|| "1"==flag){
            return true;
        }else{
            return false;
        }
    }

    //获取实体对象数据
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
                }else if(crcRegcase.getCheckResult()=="2" || "2".equals(crcRegcase.getCheckResult())){
                    crcRegcase.setCheckResult("阳性");
                }

                crccheckid=crcRegcase.getId();
                CrcRiskAssessment crcRiskAssessment=(CrcRiskAssessment)crcRiskAssessmentService.getByCheckid(crcRiskAssessmentDao,crccheckid);
                if(crcRiskAssessment!=null){
                    if(crcRiskAssessment.getAssessmentResult()=="1" || "1".equals(crcRiskAssessment.getAssessmentResult())){
                        crcRiskAssessment.setAssessmentResult("阴性");
                    }else if(crcRiskAssessment.getAssessmentResult()=="2" || "2".equals(crcRiskAssessment.getAssessmentResult())){
                        crcRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setCrcRisk(crcRiskAssessment);
                }
            }else{
                crcRegcase=new CrcRegcase();
                CrcRiskAssessment crcRiskAssessment=new CrcRiskAssessment();
                screeningVo.setCrcRisk(crcRiskAssessment);
            }
            screeningVo.setCrcRegcase(crcRegcase);
        }

        //肝癌
        if(isOpen(licFlag)){
            LicRegcase licRegcase=(LicRegcase)licRegcaseService.getByManageidAndYear(licRegcaseDao,manageid,year);
            if(licRegcase!=null){
                if(licRegcase.getCheckResult()=="1" || "1".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("阴性");
                }else if(licRegcase.getCheckResult()=="2" || "2".equals(licRegcase.getCheckResult())){
                    licRegcase.setCheckResult("阳性");
                }
                liccheckid=licRegcase.getId();
                LicRiskAssessment licRiskAssessment=(LicRiskAssessment)licRiskAssessmentService.getByCheckid(licRiskAssessmentDao,liccheckid);
                if(licRiskAssessment!=null){
                    if(licRiskAssessment.getAssessmentResult()=="1" || "1".equals(licRiskAssessment.getAssessmentResult())){
                        licRiskAssessment.setAssessmentResult("阴性");
                    }else if(licRiskAssessment.getAssessmentResult()=="2" || "2".equals(licRiskAssessment.getAssessmentResult())){
                        licRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setLicRisk(licRiskAssessment);
                }
            }else{
                licRegcase=new LicRegcase();
                LicRiskAssessment licRiskAssessment=new LicRiskAssessment();
                screeningVo.setLicRisk(licRiskAssessment);
            }
            screeningVo.setLicRegcase(licRegcase);
        }

        //胃癌
        if(isOpen(scFlag)){
            ScRegcase scRegcase=(ScRegcase)scRegcaseService.getByManageidAndYear(scRegcaseDao,manageid,year);
            if(scRegcase!=null){
                if(scRegcase.getCheckResult()=="1" || "1".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("阴性");
                }else if(scRegcase.getCheckResult()=="2" || "2".equals(scRegcase.getCheckResult())){
                    scRegcase.setCheckResult("阳性");
                }
                ScRiskAssessment scRiskAssessment=(ScRiskAssessment)scRiskAssessmentService.getByCheckid(scRiskAssessmentDao,scRegcase.getId());
                if(scRiskAssessment!=null){
                    if(scRiskAssessment.getAssessmentResult()=="1" || "1".equals(scRiskAssessment.getAssessmentResult())){
                        scRiskAssessment.setAssessmentResult("阴性");
                    }else if(scRiskAssessment.getAssessmentResult()=="2" || "2".equals(scRiskAssessment.getAssessmentResult())){
                        scRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setScRisk(scRiskAssessment);
                }
            }else{
                scRegcase=new ScRegcase();
                ScRiskAssessment scRiskAssessment=new ScRiskAssessment();
                screeningVo.setScRisk(scRiskAssessment);
            }
            screeningVo.setScRegcase(scRegcase);
        }

        //肺癌
        if(isOpen(lucFlag)){
            LucRegcase lucRegcase=(LucRegcase)lucRegcaseService.getByManageidAndYear(lucRegcaseDao,manageid,year);
            if(lucRegcase!=null){
                if(lucRegcase.getCheckResult()=="1" || "1".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("阴性");
                }else if(lucRegcase.getCheckResult()=="2" || "2".equals(lucRegcase.getCheckResult())){
                    lucRegcase.setCheckResult("阳性");
                }

                LucRiskAssessment lucRiskAssessment=(LucRiskAssessment)lucRiskAssessmentService.getByCheckid(lucRiskAssessmentDao,lucRegcase.getId());
                if(lucRiskAssessment!=null){
                    if(lucRiskAssessment.getAssessmentResult()=="1" || "1".equals(lucRiskAssessment.getAssessmentResult())){
                        lucRiskAssessment.setAssessmentResult("阴性");
                    }else if(lucRiskAssessment.getAssessmentResult()=="2" || "2".equals(lucRiskAssessment.getAssessmentResult())){
                        lucRiskAssessment.setAssessmentResult("阳性");
                    }
                    screeningVo.setLucRisk(lucRiskAssessment);
                }
            }else{
                lucRegcase=new LucRegcase();
                LucRiskAssessment lucRiskAssessment=new LucRiskAssessment();
                screeningVo.setLucRisk(lucRiskAssessment);
            }
            screeningVo.setLucRegcase(lucRegcase);
        }

        //大肠癌便隐血检查表
        if(StringUtils.isNotBlank(crccheckid)){
            CrcFobt crcFobt=(CrcFobt)crcFobtService.getByCheckid(crcFobtDao,crccheckid);
            if(crcFobt==null){
                crcFobt=new CrcFobt(getSessionUser());
            }else{
                if(crcFobt.getFobtResult()=="2" || "2".equals(crcFobt.getFobtResult())){
                    crcFobt.setFobtResult("阳性");
                }else if(crcFobt.getFobtResult()=="1" || "1".equals(crcFobt.getFobtResult())){
                    crcFobt.setFobtResult("阴性");
                }
            }
            screeningVo.setCrcFobt(crcFobt);
        }else{
            CrcFobt crcFobt=new CrcFobt(getSessionUser());
            screeningVo.setCrcFobt(crcFobt);
        }

        if(StringUtils.isNotBlank(liccheckid)){
            //肝癌辅助检查表
            LicAssistCheck licAssistCheck=(LicAssistCheck)licAssistCheckService.getByCheckid(licAssistCheckDao,liccheckid);
            if(licAssistCheck==null){
                licAssistCheck=new LicAssistCheck(getSessionUser());
            }else{
                if(licAssistCheck.getLicAssistResult() =="2" || "2".equals(licAssistCheck.getLicAssistResult())){
                    licAssistCheck.setLicAssistResult("阳性");
                }else if(licAssistCheck.getLicAssistResult()=="1" || "1".equals(licAssistCheck.getLicAssistResult())){
                    licAssistCheck.setLicAssistResult("阴性");
                }
            }
            screeningVo.setLicCheck(licAssistCheck);
        }else{
            LicAssistCheck licAssistCheck=new LicAssistCheck(getSessionUser());
            screeningVo.setLicCheck(licAssistCheck);
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

    //根据字典表 id 获取癌症名称
    private String getCancerName(String cancerType,String dicCode){
       return DictUtils.generalForMap(dicCode).get(cancerType).getName();
    }

    //判断对象是否所有属性都为空
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

