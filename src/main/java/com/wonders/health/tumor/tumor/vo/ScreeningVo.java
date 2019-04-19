package com.wonders.health.tumor.tumor.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wonders.health.tumor.tumor.entity.CancerHistory;
import com.wonders.health.tumor.tumor.entity.FamilyCancerHistory;
import com.wonders.health.tumor.tumor.entity.LucFamilyCancerHistoryXH;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 *综合vo
 *
 **/
public class ScreeningVo {

    private static final long serialVersionUID = 1L;

    @Length(max=32)
    private String id;	// 个人管理编号

    @Length(max=2)
    private String personcardType;	// 证件类型

    @Length(max=50)
    private String personcard;	// 证件号码

    @Length(max=50)
    private String name;	// 姓名

    @Length(max=1)
    private String gender;	// 性别

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birth;	// 出生日期

    @Length(max=50)
    private String telephone;	// 固定电话

    @Length(max=50)
    private String mobile;	// 移动电话

    @Length(max=2)
    private String nation;	// 民族 cdc_dic_personinfo id=00003

    @Length(max=16)
    private String nationOther;	// 其他民族

    @Length(max=2)
    private String marriage;	// 婚姻状况代码cdc_dic_personinfo id=00009

    @Length(max=2)
    private String education;	// 文化程度(学历)代码cdc_dic_personinfo id=00006

    @Length(max=2)
    private String paymentSituation;	// 医疗保险类型 cdc_dic_personinfo id=00241

    @Length(max=2)
    private String profession;	// 职业代码,参照cdc_dic_personinfo,id=00007，pcode is not null 第一级代码

    @Length(max=200)
    private String otherProfession;	// 其他职业

    @Length(max=32)
    private String paddressProvince;	// 户籍地-省（自治区、直辖市）

    @Length(max=32)
    private String paddressCity;	// 户籍地-市（地区）

    @Length(max=32)
    private String paddressCounty;	// 户籍地-县（区）

    @Length(max=32)
    private String paddressTown;	// 户籍地-乡（镇、街道）

    @Length(max=32)
    private String paddressCommittee;	// 户籍地-居委会、村

    @Length(max=200)
    private String paddressDetail;	// 户籍地址-详细

    @Length(max=32)
    private String addressProvince;	// 居住地-省（自治区、直辖市）

    @Length(max=32)
    private String addressCity;	// 居住地-市（地区）

    @Length(max=32)
    private String addressCounty;	// 居住地-县（区）

    @Length(max=32)
    private String addressTown;	// 居住地-乡（镇、街道）

    @Length(max=32)
    private String addressCommittee;	// 居住地-居委会、村

    @Length(max=200)
    private String addressDetail;	// 居住地址-详细

    @Length(max=32)
    private String regorg;	// 登记医疗机构代码

    @Length(max=32)
    private String regdoc;	// 登记医生代码

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;	// 登记日期

    @Length(max=1)
    private String ischange;	// 更新标志cdc_dic_personinfo id=60013 code=1：是；code=2：否

    //大肠癌相关字段  统一添加了crc后缀

    @Length(max=32)
    private String idCrc;	// 主键

    @Length(max=32)
    @NotNull
    private String manageidCrc;	// 个人管理编号

    @Length(max=64)
    @NotNull
    private String idNumberCrc;	// 大肠癌初筛ID：2位年份+5位机构编码+5位序号

    @NotNull
    private Integer checkYearCrc;	// 初筛年度

    @Length(max=1)
    private String checkResultCrc;	// 判定结果

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cbqReturnDate1;	// 第一次采便器交回日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cbqReturnDate2;	// 第二次采便器交回日期

    @Length(max=32)
    @NotNull
    private String regionCodeCrc;	// 初筛数据所属行政区划代码

    @Length(max=32)
    @NotNull
    private String regorgCrc;	// 登记医疗机构代码

    @Length(max=32)
    @NotNull
    private String regdocCrc;	// 登记医生代码

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdateCrc;	// 登记日期

    @Length(max=1)
    @NotNull
    private String submitsStatusCrc;	// 数据提交状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitDateCrc;	// 数据提交日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transDateCrc;	// 转诊单打印日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date printDateCrc;	// 通知单打印日期

    @Length(max=1)
    @NotNull
    private String closeStatusCrc;	// 结案状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDateCrc;	// 结案日期

    @Length(max=1)
    private String closeResultCrc;	// 结案结果

    @Length(max=1)
    private String checkQualityFlagCrc;	// 初筛登记质控标志

    @Length(max=1)
    private String checkresultQualityFlagCrc;	// 初筛结果通知质控标志

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeCrc;	// 上传时间

    @Length(max=32)
    private String sourceIdCrc;	// 来源代码

    @Length(max=1)
    private String ischangeCrc;	// 更新标志

//肝癌相关字段  统一加后缀Lic

    @Length(max=32)
    private String idLic;	// 主键

    @Length(max=32)
    @NotNull
    private String manageidLic;	// 个人管理编号

    @NotNull
    private Integer checkYearLic;	// 初筛年度

    @Length(max=1)
    private String checkResultLic;	// 判定结果

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fzjcDateLic;	// 辅助检查录入日期

    @Length(max=32)
    @NotNull
    private String regionCodeLic;	// 初筛数据所属行政区划代码

    @Length(max=32)
    @NotNull
    private String regorgLic;	// 登记医疗机构代码

    @Length(max=32)
    @NotNull
    private String regdocLic;	// 登记医生代码

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdateLic;	// 登记日期

    @Length(max=1)
    @NotNull
    private String submitsStatusLic;	// 数据提交状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitDateLic;	// 数据提交日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transDateLic;	// 转诊单打印日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date printDateLic;	// 通知单打印日期

    @Length(max=1)
    @NotNull
    private String closeStatusLic;	// 结案状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDateLic;	// 结案日期

    @Length(max=1)
    private String closeResultLic;	// 结案结果

    @Length(max=1)
    private String checkQualityFlagLic;	// 初筛登记质控标志

    @Length(max=1)
    private String checkresultQualityFlagLic;	// 初筛结果通知质控标志


    private Date uploadTimeLic;	// 上传时间

    @Length(max=32)
    private String sourceIdLic;	// 来源代码


    //胃癌相关字段 统一添加后缀Sc

    @Length(max=32)
    private String idSc;	// 主键

    @Length(max=32)
    @NotNull
    private String manageidSc;	// 个人管理编号

    @NotNull
    private Integer checkYearSc;	// 初筛年度

    @Length(max=1)
    private String checkResultSc;	// 判定结果

    @Length(max=32)
    @NotNull
    private String regionCodeSc;	// 初筛数据所属行政区划代码

    @Length(max=32)
    @NotNull
    private String regorgSc;	// 登记医疗机构代码

    @Length(max=32)
    @NotNull
    private String regdocSc;	// 登记医生代码

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdateSc;	// 登记日期

    @Length(max=1)
    @NotNull
    private String submitsStatusSc;	// 数据提交状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitDateSc;	// 数据提交日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transDateSc;	// 转诊单打印日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date printDateSc;	// 通知单打印日期

    @Length(max=1)
    @NotNull
    private String closeStatusSc;	// 结案状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDateSc;	// 结案日期

    @Length(max=1)
    private String closeResultSc;	// 结案结果

    @Length(max=1)
    private String checkQualityFlagSc;	// 初筛登记质控标志

    @Length(max=1)
    private String checkresultQualityFlagSc;	// 初筛结果通知质控标志

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeSc;	// 上传时间

    @Length(max=32)
    private String sourceIdSc;	// 来源代码


    //肺癌相关字段 统一添加后缀Luc
    @Length(max=32)
    private String idLuc;	// 主键

    @Length(max=32)
    @NotNull
    private String manageidLuc;	// 个人管理编号

    @NotNull
    private Integer checkYearLuc;	// 初筛年度

    @Length(max=1)
    private String checkResultLuc;	// 判定结果

    @Length(max=32)
    @NotNull
    private String regionCodeLuc;	// 初筛数据所属行政区划代码

    @Length(max=32)
    @NotNull
    private String regorgLuc;	// 登记医疗机构代码

    @Length(max=32)
    @NotNull
    private String regdocLuc;	// 登记医生代码

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdateLuc;	// 登记日期

    @Length(max=1)
    @NotNull
    private String submitsStatusLuc;	// 数据提交状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitDateLuc;	// 数据提交日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transDateLuc;	// 转诊单打印日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date printDateLuc;	// 通知单打印日期

    @Length(max=1)
    @NotNull
    private String closeStatusLuc;	// 结案状态

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDateLuc;	// 结案日期

    @Length(max=1)
    private String closeResultLuc;	// 结案结果

    @Length(max=1)
    private String checkQualityFlagLuc;	// 初筛登记质控标志

    @Length(max=1)
    private String checkresultQualityFlagLuc;	// 初筛结果通知质控标志

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeLuc;	// 上传时间

    @Length(max=32)
    private String sourceIdLuc;	// 来源代码

    //大肠癌危险度评估 后缀 CrcR

    @Length(max=32)
    private String idCrcR;	// 主键

    @Length(max=32)
    @NotNull
    private String crcCheckId;	// 大肠癌初筛信息登记id

    @Length(max=1)
    private String manxingfuxie;	// 慢性腹泻史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String manxingbianmi;	// 慢性便秘史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String nianyexuebian;	// 粘液和或血便史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String lanweiyan;	// 慢性阑尾炎或阑尾切除史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String dannangyan;	// 有无慢性胆囊炎或胆囊切除史 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String jingshenchuangshang;	// 有无精神创伤 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String aizhengshi;	// 有无癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在癌症史表中有对应记录)

    @Length(max=1)
    private String qinshuAizhengshi;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

    @Length(max=1)
    private String changxirou;	// 有无肠息肉史 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String xiyan;	// 是否吸烟 cdc_dic_personinfo id=60008code=1：不吸；code=2：过去吸，现在不吸；code=3：现在吸

    @Length(max=1)
    private String xuexichong;	// 有无血吸虫病史 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer ganrannian;	// 血吸虫病感染年份

    @Length(max=1)
    @NotNull
    private String assessmentResultCrcR;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String assessmentDocCrcR;	// 评估医生代码

    @Length(max=64)
    @NotNull
    private String assessmentDocNameCrcR;	// 评估医生姓名

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assessmentDateCrcR;	// 评估日期

    @Length(max=1)
    @NotNull
    private String riskQualityFlagCrcR;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeCrcR;	// 上传时间

    @Length(max=1)
    private String ischangeCrcR;	// 更新标志 cdc_dic_personinfo id=60013code=1：是；code=2：否


    //肝癌危险度评估 后缀LicR
    @Length(max=32)
    private String idLicR;	// 主键

    @Length(max=32)
    @NotNull
    private String licCheckId;	// 肝癌初筛信息登记id

    @Length(max=1)
    private String yigan;	// 有无乙型肝炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String binggan;	// 有无丙型肝炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String ganyinghua;	// 有无肝硬化 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String aizhengshiLicR;	// 有无癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在癌症史表中有对应记录)

    @Length(max=1)
    private String qinshuAizhengshiLicR;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

    @Length(max=1)
    @NotNull
    private String assessmentResultLicR;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String assessmentDocLicR;	// 评估医生代码

    @Length(max=64)
    @NotNull
    private String assessmentDocNameLicR;	// 评估医生签名

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assessmentDateLicR;	// 评估日期

    @Length(max=1)
    @NotNull
    private String riskQualityFlagLicR;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeLicR;	// 上传时间

    //胃癌危险度评估  后缀 ScR
    @Length(max=32)
    private String idScR;	// 主键

    @Length(max=32)
    @NotNull
    private String scCheckId;	// 胃癌初筛信息登记id

    @Length(max=1)
    private String xinxianshucai;	// 常吃食物-新鲜蔬菜 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）

    @Length(max=1)
    private String xinxianshuiguo;	// 常吃食物-新鲜水果 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）

    @Length(max=1)
    private String xinxianroulei;	// 常吃食物-新鲜肉类 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）

    @Length(max=1)
    private String jiagongroulei;	// 常吃食物-加工肉类 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）

    @Length(max=1)
    private String meibianshiwu;	// 常吃食物-霉变食物 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）

    @Length(max=1)
    private String hejiu;	// 近12个月是否喝酒 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String hejiupinlv;	// 喝酒频率 cdc_dic_personinfo id=60010code=1：每天；code=2：5-6天/周；code=3：3-4天/周；code=4：1-2天/周；code=5：1-3天/月；code=6：少于1天/月

    @Length(max=1)
    private String shifouzhidaohejiuliang;	// 是否知道饮酒量 cdc_dic_personinfo id=60011code=1：知道；code=2：不知道


    private Integer hejiuliang;	// 饮酒量

    @Length(max=1)
    private String weisuoxingweiyan;	// 萎缩性胃炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String shierzhichangkuiyang;	// 十二指肠溃疡 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String weichuxue;	// 胃出血 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String shiguanyan;	// 食管炎 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String weixirou;	// 胃息肉 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String canwei;	// 手术后残胃 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String pinxue;	// 恶性贫血 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String aizhengshiScR;	// 有无癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在癌症史表中有对应记录)

    @Length(max=1)
    private String qinshuAizhengshiScR;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

    @Length(max=1)
    @NotNull
    private String assessmentResultScR;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String assessmentDocScR;	// 评估医生代码

    @Length(max=64)
    @NotNull
    private String assessmentDocNameScR;	// 评估医生签名

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assessmentDateScR;	// 评估日期

    @Length(max=1)
    @NotNull
    private String riskQualityFlagScR;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeScR;	// 上传时间

    //肺癌危险度评估 后缀LucR

    @Length(max=32)
    private String idLucR;	// 主键

    @Length(max=32)
    @NotNull
    private String lucCheckId;	// 肺癌初筛信息登记id

    @Length(max=1)
    private String chufangyouyan;	// 长期厨房油烟接触 cdc_dic_personinfo id=60012 c ode=1：每周<1次；code=2：每周1～3次；code=3：每周>3次；code=4：每天

    @Length(max=1)
    private String youyanjiechupinlv;	// 油烟接触频率 cdc_dic_personinfo id=60009code=1：从不；code=2：很少（<2次/周）；code=3：经常（>=2次/周）


    private Integer youyanjiechunianshu;	// 油烟接触年数

    @Length(max=1)
    private String chouyouyanji;	// 是否使用抽油烟机 cdc_dic_personinfo id=60013code=1：是；code=2：否

    @Length(max=1)
    private String chouyouyanjinianshu;	// 使用抽油烟机年数 cdc_dic_personinfo id=60013code=1：是；code=2：否

    @Length(max=1)
    private String xiyanLucR;	// 是否吸烟 cdc_dic_personinfo id=60008code=1：不吸；code=2：过去吸，现在不吸；code=3：现在吸


    private Integer zuihouyicxiyan;	// 最后一次吸烟距今多少年


    private Integer kaishixiyannianling;	// 开始吸烟年龄


    private Integer meitianxiyanliang;	// 平均每天吸烟量

    @Length(max=1)
    private String beidongxiyan;	// 被动吸烟情况 cdc_dic_personinfo id=60015code=1：是的，<=1小时/天；code=2：是的，1～2小时/天；code=3：是的，>2小时/天；code=4：否

    @Length(max=10)
    private String beidongxiyanduixiang;	// 被动吸烟对象 cdc_dic_personinfo id=60016code=1：配偶；code=2：同居家庭成员；code=3：工作场合同事 多选，以,分隔


    private Integer beidongxiyannianshu;	// 被动吸烟累计年数

    @Length(max=1)
    private String zhiyezhiaiwuzhi;	// 职业致癌物质接触 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=2)
    private String zhiaiwuzhileixing;	// 致癌物质类型 cdc_dic_personinfo id=60022多选，以,分隔


    private Integer zhiaiwuzhiNianshu;	// 致癌物质累计接触年

    @Length(max=1)
    private String xiaochuan;	// 哮喘 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer xiaochuanNianshu;	// 哮喘患病年数

    @Length(max=1)
    private String zhiqiguanyan;	// 慢性支气管炎 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer zhiqiguanyanNianshu;	// 慢性支气管炎患病年数

    @Length(max=1)
    private String feiqizhong;	// 肺气肿 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer feiqizhongNianshu;	// 肺气肿患病年数

    @Length(max=1)
    private String feijiehe;	// 肺结核 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer feijieheNianshu;	// 肺结核患病年数

    @Length(max=1)
    private String guichenzhuo;	// 硅沉着病 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer guichenzhuoNianshu;	// 硅沉着病患病年数

    @Length(max=1)
    private String xianweihua;	// 肺间质纤维化 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer xianweihuaNianshu;	// 肺间质纤维化患病年数

    @Length(max=1)
    private String manzufei;	// 慢性阻塞性肺炎 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer manzufeiNianshu;	// 慢性阻塞性肺炎患病年数

    @Length(max=1)
    private String qitafeibing;	// 患有其他肺部疾病 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer qitafeibingNianshu;	// 其他肺部疾病患病年数

    @Length(max=1)
    private String ganke;	// 近期有持续性干咳 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer gankeTianshu;	// 持续性干咳患病天数

    @Length(max=1)
    private String tanzhongdaixue;	// 近期有痰中带血 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer tanzhongdaixTianshu;	// 痰中带血患病天数

    @Length(max=1)
    private String qiji;	// 近期活动后气急 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer qijiTianshu;	// 活动后气急患病天数

    @Length(max=1)
    private String xiongmen;	// 近期有胸闷 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer xiongmenTianshu;	// 胸闷患病天数

    @Length(max=1)
    private String xiongtong;	// 近期有胸痛 cdc_dic_personinfo id=60007code=1：有；code=2：无


    private Integer xiongtongTianshu;	// 胸痛患病天数

    @Length(max=1)
    private String shengyinsiya;	// 近期声音嘶哑 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String tongbuweifeiyan;	// 近期反复同部位的肺炎 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=1)
    private String feijiejie;	// 既往放射性检查或诊疗是否发现肺部结节 cdc_dic_personinfo id=60007 code=1：有；code=2：无

    @Length(max=100)
    private String jiejiedaxiao;	// 结节大小

    @Length(max=200)
    private String jiejiexingtai;	// 结节形态

    @Length(max=1)
    private String maoboli;	// 毛玻璃样改变 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String shixingjiejie;	// 实性结节 cdc_dic_personinfo id=60007code=1：有；code=2：无

    @Length(max=1)
    private String aizhengshiLucR;	// 有无癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在癌症史表中有对应记录)

    @Length(max=1)
    private String qinshuAizhengshiLucR;	// 一级亲属癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在亲属大肠癌史中有对应记录)

    @Length(max=1)
    @NotNull
    private String assessmentResultLucR;	// 危险度评估结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String assessmentDocLucR;	// 评估医生代码

    @Length(max=64)
    @NotNull
    private String assessmentDocNameLucR;	// 评估医生签名

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assessmentDateLucR;	// 评估日期

    @Length(max=1)
    @NotNull
    private String riskQualityFlagLucR;	// 危险度评估质控标志 cdc_dic_personinfo id=60005 code=1：未参与；code=2：已参与

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeLucR;	// 上传时间

    //大肠癌便隐血 后缀CrcF
    @Length(max=32)
    private String idCrcF;	// 主键

    @Length(max=32)
    @NotNull
    private String crcCheckIdCrcF;	// 大肠癌初筛信息登记id

    @Length(max=1)
    private String firstFobtResult;	// 第一次便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @Length(max=1)
    private String secondFobtResult;	// 第二次便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date firstFobtDate;	// 第一次便隐血检查时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date secondFobtDate;	// 第一次便隐血检查时间

    @Length(max=1)
    private String fobtResult;	// 便隐血检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String fobtDoc;	// 检查医生代码

    @Length(max=64)
    @NotNull
    private String fobtDocName;	// 检查医生姓名

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTimeCrcF;	// 上传时间

    @Length(max=1)
    private String ischangeCrcF;	// 更新标志 cdc_dic_personinfo id=60013 code=1：是；code=2：否


    //肝癌辅助检查表 后缀Lica

    @Length(max=32)
    private String idLica;	// 主键

    @Length(max=32)
    @NotNull
    private String licCheckIdLica;	// 肝癌初筛信息登记id


    private Integer gtp;	// GTP检查结果

    @Length(max=32)
    private String hbeg;	// HBeg检查结果


    private Integer afp;	// AFP检查结果

    @Length(max=1)
    private String bus;	// B超检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @Length(max=1)
    private String licAssistResult;	// 肝癌辅助检查结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

    @Length(max=32)
    @NotNull
    private String licAssistDoc;	// 检查医生代码

    @Length(max=64)
    @NotNull
    private String licAssistDocName;	// 检查医生姓名

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date licAssistDate;	// 检查日期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;	// 上传时间

    //癌症史表
    private List<CancerHistory> historyList;

    //一级亲癌症史表
    private List<FamilyCancerHistory>familyCancerHistoryList;

    //一级亲癌症史表（徐汇）
    private List<LucFamilyCancerHistoryXH>lucFamilyCancerHistoryXHList;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersoncardType() {
        return personcardType;
    }

    public void setPersoncardType(String personcardType) {
        this.personcardType = personcardType;
    }

    public String getPersoncard() {
        return personcard;
    }

    public void setPersoncard(String personcard) {
        this.personcard = personcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationOther() {
        return nationOther;
    }

    public void setNationOther(String nationOther) {
        this.nationOther = nationOther;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPaymentSituation() {
        return paymentSituation;
    }

    public void setPaymentSituation(String paymentSituation) {
        this.paymentSituation = paymentSituation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOtherProfession() {
        return otherProfession;
    }

    public void setOtherProfession(String otherProfession) {
        this.otherProfession = otherProfession;
    }

    public String getPaddressProvince() {
        return paddressProvince;
    }

    public void setPaddressProvince(String paddressProvince) {
        this.paddressProvince = paddressProvince;
    }

    public String getPaddressCity() {
        return paddressCity;
    }

    public void setPaddressCity(String paddressCity) {
        this.paddressCity = paddressCity;
    }

    public String getPaddressCounty() {
        return paddressCounty;
    }

    public void setPaddressCounty(String paddressCounty) {
        this.paddressCounty = paddressCounty;
    }

    public String getPaddressTown() {
        return paddressTown;
    }

    public void setPaddressTown(String paddressTown) {
        this.paddressTown = paddressTown;
    }

    public String getPaddressCommittee() {
        return paddressCommittee;
    }

    public void setPaddressCommittee(String paddressCommittee) {
        this.paddressCommittee = paddressCommittee;
    }

    public String getPaddressDetail() {
        return paddressDetail;
    }

    public void setPaddressDetail(String paddressDetail) {
        this.paddressDetail = paddressDetail;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressCommittee() {
        return addressCommittee;
    }

    public void setAddressCommittee(String addressCommittee) {
        this.addressCommittee = addressCommittee;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getRegorg() {
        return regorg;
    }

    public void setRegorg(String regorg) {
        this.regorg = regorg;
    }

    public String getRegdoc() {
        return regdoc;
    }

    public void setRegdoc(String regdoc) {
        this.regdoc = regdoc;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getIschange() {
        return ischange;
    }

    public void setIschange(String ischange) {
        this.ischange = ischange;
    }

    public String getIdCrc() {
        return idCrc;
    }

    public void setIdCrc(String idCrc) {
        this.idCrc = idCrc;
    }

    public String getManageidCrc() {
        return manageidCrc;
    }

    public void setManageidCrc(String manageidCrc) {
        this.manageidCrc = manageidCrc;
    }

    public String getIdNumberCrc() {
        return idNumberCrc;
    }

    public void setIdNumberCrc(String idNumberCrc) {
        this.idNumberCrc = idNumberCrc;
    }

    public Integer getCheckYearCrc() {
        return checkYearCrc;
    }

    public void setCheckYearCrc(Integer checkYearCrc) {
        this.checkYearCrc = checkYearCrc;
    }

    public String getCheckResultCrc() {
        return checkResultCrc;
    }

    public void setCheckResultCrc(String checkResultCrc) {
        this.checkResultCrc = checkResultCrc;
    }

    public Date getCbqReturnDate1() {
        return cbqReturnDate1;
    }

    public void setCbqReturnDate1(Date cbqReturnDate1) {
        this.cbqReturnDate1 = cbqReturnDate1;
    }

    public Date getCbqReturnDate2() {
        return cbqReturnDate2;
    }

    public void setCbqReturnDate2(Date cbqReturnDate2) {
        this.cbqReturnDate2 = cbqReturnDate2;
    }

    public String getRegionCodeCrc() {
        return regionCodeCrc;
    }

    public void setRegionCodeCrc(String regionCodeCrc) {
        this.regionCodeCrc = regionCodeCrc;
    }

    public String getRegorgCrc() {
        return regorgCrc;
    }

    public void setRegorgCrc(String regorgCrc) {
        this.regorgCrc = regorgCrc;
    }

    public String getRegdocCrc() {
        return regdocCrc;
    }

    public void setRegdocCrc(String regdocCrc) {
        this.regdocCrc = regdocCrc;
    }

    public Date getRegdateCrc() {
        return regdateCrc;
    }

    public void setRegdateCrc(Date regdateCrc) {
        this.regdateCrc = regdateCrc;
    }

    public String getSubmitsStatusCrc() {
        return submitsStatusCrc;
    }

    public void setSubmitsStatusCrc(String submitsStatusCrc) {
        this.submitsStatusCrc = submitsStatusCrc;
    }

    public Date getSubmitDateCrc() {
        return submitDateCrc;
    }

    public void setSubmitDateCrc(Date submitDateCrc) {
        this.submitDateCrc = submitDateCrc;
    }

    public Date getTransDateCrc() {
        return transDateCrc;
    }

    public void setTransDateCrc(Date transDateCrc) {
        this.transDateCrc = transDateCrc;
    }

    public Date getPrintDateCrc() {
        return printDateCrc;
    }

    public void setPrintDateCrc(Date printDateCrc) {
        this.printDateCrc = printDateCrc;
    }

    public String getCloseStatusCrc() {
        return closeStatusCrc;
    }

    public void setCloseStatusCrc(String closeStatusCrc) {
        this.closeStatusCrc = closeStatusCrc;
    }

    public Date getCloseDateCrc() {
        return closeDateCrc;
    }

    public void setCloseDateCrc(Date closeDateCrc) {
        this.closeDateCrc = closeDateCrc;
    }

    public String getCloseResultCrc() {
        return closeResultCrc;
    }

    public void setCloseResultCrc(String closeResultCrc) {
        this.closeResultCrc = closeResultCrc;
    }

    public String getCheckQualityFlagCrc() {
        return checkQualityFlagCrc;
    }

    public void setCheckQualityFlagCrc(String checkQualityFlagCrc) {
        this.checkQualityFlagCrc = checkQualityFlagCrc;
    }

    public String getCheckresultQualityFlagCrc() {
        return checkresultQualityFlagCrc;
    }

    public void setCheckresultQualityFlagCrc(String checkresultQualityFlagCrc) {
        this.checkresultQualityFlagCrc = checkresultQualityFlagCrc;
    }

    public Date getUploadTimeCrc() {
        return uploadTimeCrc;
    }

    public void setUploadTimeCrc(Date uploadTimeCrc) {
        this.uploadTimeCrc = uploadTimeCrc;
    }

    public String getSourceIdCrc() {
        return sourceIdCrc;
    }

    public void setSourceIdCrc(String sourceIdCrc) {
        this.sourceIdCrc = sourceIdCrc;
    }

    public String getIschangeCrc() {
        return ischangeCrc;
    }

    public void setIschangeCrc(String ischangeCrc) {
        this.ischangeCrc = ischangeCrc;
    }

    public String getIdLic() {
        return idLic;
    }

    public void setIdLic(String idLic) {
        this.idLic = idLic;
    }

    public String getManageidLic() {
        return manageidLic;
    }

    public void setManageidLic(String manageidLic) {
        this.manageidLic = manageidLic;
    }

    public Integer getCheckYearLic() {
        return checkYearLic;
    }

    public void setCheckYearLic(Integer checkYearLic) {
        this.checkYearLic = checkYearLic;
    }

    public String getCheckResultLic() {
        return checkResultLic;
    }

    public void setCheckResultLic(String checkResultLic) {
        this.checkResultLic = checkResultLic;
    }

    public Date getFzjcDateLic() {
        return fzjcDateLic;
    }

    public void setFzjcDateLic(Date fzjcDateLic) {
        this.fzjcDateLic = fzjcDateLic;
    }

    public String getRegionCodeLic() {
        return regionCodeLic;
    }

    public void setRegionCodeLic(String regionCodeLic) {
        this.regionCodeLic = regionCodeLic;
    }

    public String getRegorgLic() {
        return regorgLic;
    }

    public void setRegorgLic(String regorgLic) {
        this.regorgLic = regorgLic;
    }

    public String getRegdocLic() {
        return regdocLic;
    }

    public void setRegdocLic(String regdocLic) {
        this.regdocLic = regdocLic;
    }

    public Date getRegdateLic() {
        return regdateLic;
    }

    public void setRegdateLic(Date regdateLic) {
        this.regdateLic = regdateLic;
    }

    public String getSubmitsStatusLic() {
        return submitsStatusLic;
    }

    public void setSubmitsStatusLic(String submitsStatusLic) {
        this.submitsStatusLic = submitsStatusLic;
    }

    public Date getSubmitDateLic() {
        return submitDateLic;
    }

    public void setSubmitDateLic(Date submitDateLic) {
        this.submitDateLic = submitDateLic;
    }

    public Date getTransDateLic() {
        return transDateLic;
    }

    public void setTransDateLic(Date transDateLic) {
        this.transDateLic = transDateLic;
    }

    public Date getPrintDateLic() {
        return printDateLic;
    }

    public void setPrintDateLic(Date printDateLic) {
        this.printDateLic = printDateLic;
    }

    public String getCloseStatusLic() {
        return closeStatusLic;
    }

    public void setCloseStatusLic(String closeStatusLic) {
        this.closeStatusLic = closeStatusLic;
    }

    public Date getCloseDateLic() {
        return closeDateLic;
    }

    public void setCloseDateLic(Date closeDateLic) {
        this.closeDateLic = closeDateLic;
    }

    public String getCloseResultLic() {
        return closeResultLic;
    }

    public void setCloseResultLic(String closeResultLic) {
        this.closeResultLic = closeResultLic;
    }

    public String getCheckQualityFlagLic() {
        return checkQualityFlagLic;
    }

    public void setCheckQualityFlagLic(String checkQualityFlagLic) {
        this.checkQualityFlagLic = checkQualityFlagLic;
    }

    public String getCheckresultQualityFlagLic() {
        return checkresultQualityFlagLic;
    }

    public void setCheckresultQualityFlagLic(String checkresultQualityFlagLic) {
        this.checkresultQualityFlagLic = checkresultQualityFlagLic;
    }

    public Date getUploadTimeLic() {
        return uploadTimeLic;
    }

    public void setUploadTimeLic(Date uploadTimeLic) {
        this.uploadTimeLic = uploadTimeLic;
    }

    public String getSourceIdLic() {
        return sourceIdLic;
    }

    public void setSourceIdLic(String sourceIdLic) {
        this.sourceIdLic = sourceIdLic;
    }

    public String getIdSc() {
        return idSc;
    }

    public void setIdSc(String idSc) {
        this.idSc = idSc;
    }

    public String getManageidSc() {
        return manageidSc;
    }

    public void setManageidSc(String manageidSc) {
        this.manageidSc = manageidSc;
    }

    public Integer getCheckYearSc() {
        return checkYearSc;
    }

    public void setCheckYearSc(Integer checkYearSc) {
        this.checkYearSc = checkYearSc;
    }

    public String getCheckResultSc() {
        return checkResultSc;
    }

    public void setCheckResultSc(String checkResultSc) {
        this.checkResultSc = checkResultSc;
    }

    public String getRegionCodeSc() {
        return regionCodeSc;
    }

    public void setRegionCodeSc(String regionCodeSc) {
        this.regionCodeSc = regionCodeSc;
    }

    public String getRegorgSc() {
        return regorgSc;
    }

    public void setRegorgSc(String regorgSc) {
        this.regorgSc = regorgSc;
    }

    public String getRegdocSc() {
        return regdocSc;
    }

    public void setRegdocSc(String regdocSc) {
        this.regdocSc = regdocSc;
    }

    public Date getRegdateSc() {
        return regdateSc;
    }

    public void setRegdateSc(Date regdateSc) {
        this.regdateSc = regdateSc;
    }

    public String getSubmitsStatusSc() {
        return submitsStatusSc;
    }

    public void setSubmitsStatusSc(String submitsStatusSc) {
        this.submitsStatusSc = submitsStatusSc;
    }

    public Date getSubmitDateSc() {
        return submitDateSc;
    }

    public void setSubmitDateSc(Date submitDateSc) {
        this.submitDateSc = submitDateSc;
    }

    public Date getTransDateSc() {
        return transDateSc;
    }

    public void setTransDateSc(Date transDateSc) {
        this.transDateSc = transDateSc;
    }

    public Date getPrintDateSc() {
        return printDateSc;
    }

    public void setPrintDateSc(Date printDateSc) {
        this.printDateSc = printDateSc;
    }

    public String getCloseStatusSc() {
        return closeStatusSc;
    }

    public void setCloseStatusSc(String closeStatusSc) {
        this.closeStatusSc = closeStatusSc;
    }

    public Date getCloseDateSc() {
        return closeDateSc;
    }

    public void setCloseDateSc(Date closeDateSc) {
        this.closeDateSc = closeDateSc;
    }

    public String getCloseResultSc() {
        return closeResultSc;
    }

    public void setCloseResultSc(String closeResultSc) {
        this.closeResultSc = closeResultSc;
    }

    public String getCheckQualityFlagSc() {
        return checkQualityFlagSc;
    }

    public void setCheckQualityFlagSc(String checkQualityFlagSc) {
        this.checkQualityFlagSc = checkQualityFlagSc;
    }

    public String getCheckresultQualityFlagSc() {
        return checkresultQualityFlagSc;
    }

    public void setCheckresultQualityFlagSc(String checkresultQualityFlagSc) {
        this.checkresultQualityFlagSc = checkresultQualityFlagSc;
    }

    public Date getUploadTimeSc() {
        return uploadTimeSc;
    }

    public void setUploadTimeSc(Date uploadTimeSc) {
        this.uploadTimeSc = uploadTimeSc;
    }

    public String getSourceIdSc() {
        return sourceIdSc;
    }

    public void setSourceIdSc(String sourceIdSc) {
        this.sourceIdSc = sourceIdSc;
    }

    public String getIdLuc() {
        return idLuc;
    }

    public void setIdLuc(String idLuc) {
        this.idLuc = idLuc;
    }

    public String getManageidLuc() {
        return manageidLuc;
    }

    public void setManageidLuc(String manageidLuc) {
        this.manageidLuc = manageidLuc;
    }

    public Integer getCheckYearLuc() {
        return checkYearLuc;
    }

    public void setCheckYearLuc(Integer checkYearLuc) {
        this.checkYearLuc = checkYearLuc;
    }

    public String getCheckResultLuc() {
        return checkResultLuc;
    }

    public void setCheckResultLuc(String checkResultLuc) {
        this.checkResultLuc = checkResultLuc;
    }

    public String getRegionCodeLuc() {
        return regionCodeLuc;
    }

    public void setRegionCodeLuc(String regionCodeLuc) {
        this.regionCodeLuc = regionCodeLuc;
    }

    public String getRegorgLuc() {
        return regorgLuc;
    }

    public void setRegorgLuc(String regorgLuc) {
        this.regorgLuc = regorgLuc;
    }

    public String getRegdocLuc() {
        return regdocLuc;
    }

    public void setRegdocLuc(String regdocLuc) {
        this.regdocLuc = regdocLuc;
    }

    public Date getRegdateLuc() {
        return regdateLuc;
    }

    public void setRegdateLuc(Date regdateLuc) {
        this.regdateLuc = regdateLuc;
    }

    public String getSubmitsStatusLuc() {
        return submitsStatusLuc;
    }

    public void setSubmitsStatusLuc(String submitsStatusLuc) {
        this.submitsStatusLuc = submitsStatusLuc;
    }

    public Date getSubmitDateLuc() {
        return submitDateLuc;
    }

    public void setSubmitDateLuc(Date submitDateLuc) {
        this.submitDateLuc = submitDateLuc;
    }

    public Date getTransDateLuc() {
        return transDateLuc;
    }

    public void setTransDateLuc(Date transDateLuc) {
        this.transDateLuc = transDateLuc;
    }

    public Date getPrintDateLuc() {
        return printDateLuc;
    }

    public void setPrintDateLuc(Date printDateLuc) {
        this.printDateLuc = printDateLuc;
    }

    public String getCloseStatusLuc() {
        return closeStatusLuc;
    }

    public void setCloseStatusLuc(String closeStatusLuc) {
        this.closeStatusLuc = closeStatusLuc;
    }

    public Date getCloseDateLuc() {
        return closeDateLuc;
    }

    public void setCloseDateLuc(Date closeDateLuc) {
        this.closeDateLuc = closeDateLuc;
    }

    public String getCloseResultLuc() {
        return closeResultLuc;
    }

    public void setCloseResultLuc(String closeResultLuc) {
        this.closeResultLuc = closeResultLuc;
    }

    public String getCheckQualityFlagLuc() {
        return checkQualityFlagLuc;
    }

    public void setCheckQualityFlagLuc(String checkQualityFlagLuc) {
        this.checkQualityFlagLuc = checkQualityFlagLuc;
    }

    public String getCheckresultQualityFlagLuc() {
        return checkresultQualityFlagLuc;
    }

    public void setCheckresultQualityFlagLuc(String checkresultQualityFlagLuc) {
        this.checkresultQualityFlagLuc = checkresultQualityFlagLuc;
    }

    public Date getUploadTimeLuc() {
        return uploadTimeLuc;
    }

    public void setUploadTimeLuc(Date uploadTimeLuc) {
        this.uploadTimeLuc = uploadTimeLuc;
    }

    public String getSourceIdLuc() {
        return sourceIdLuc;
    }

    public void setSourceIdLuc(String sourceIdLuc) {
        this.sourceIdLuc = sourceIdLuc;
    }

    public String getIdCrcR() {
        return idCrcR;
    }

    public void setIdCrcR(String idCrcR) {
        this.idCrcR = idCrcR;
    }

    public String getCrcCheckId() {
        return crcCheckId;
    }

    public void setCrcCheckId(String crcCheckId) {
        this.crcCheckId = crcCheckId;
    }

    public String getManxingfuxie() {
        return manxingfuxie;
    }

    public void setManxingfuxie(String manxingfuxie) {
        this.manxingfuxie = manxingfuxie;
    }

    public String getManxingbianmi() {
        return manxingbianmi;
    }

    public void setManxingbianmi(String manxingbianmi) {
        this.manxingbianmi = manxingbianmi;
    }

    public String getNianyexuebian() {
        return nianyexuebian;
    }

    public void setNianyexuebian(String nianyexuebian) {
        this.nianyexuebian = nianyexuebian;
    }

    public String getLanweiyan() {
        return lanweiyan;
    }

    public void setLanweiyan(String lanweiyan) {
        this.lanweiyan = lanweiyan;
    }

    public String getDannangyan() {
        return dannangyan;
    }

    public void setDannangyan(String dannangyan) {
        this.dannangyan = dannangyan;
    }

    public String getJingshenchuangshang() {
        return jingshenchuangshang;
    }

    public void setJingshenchuangshang(String jingshenchuangshang) {
        this.jingshenchuangshang = jingshenchuangshang;
    }

    public String getAizhengshi() {
        return aizhengshi;
    }

    public void setAizhengshi(String aizhengshi) {
        this.aizhengshi = aizhengshi;
    }

    public String getQinshuAizhengshi() {
        return qinshuAizhengshi;
    }

    public void setQinshuAizhengshi(String qinshuAizhengshi) {
        this.qinshuAizhengshi = qinshuAizhengshi;
    }

    public String getChangxirou() {
        return changxirou;
    }

    public void setChangxirou(String changxirou) {
        this.changxirou = changxirou;
    }

    public String getXiyan() {
        return xiyan;
    }

    public void setXiyan(String xiyan) {
        this.xiyan = xiyan;
    }

    public String getXuexichong() {
        return xuexichong;
    }

    public void setXuexichong(String xuexichong) {
        this.xuexichong = xuexichong;
    }

    public Integer getGanrannian() {
        return ganrannian;
    }

    public void setGanrannian(Integer ganrannian) {
        this.ganrannian = ganrannian;
    }

    public String getAssessmentResultCrcR() {
        return assessmentResultCrcR;
    }

    public void setAssessmentResultCrcR(String assessmentResultCrcR) {
        this.assessmentResultCrcR = assessmentResultCrcR;
    }

    public String getAssessmentDocCrcR() {
        return assessmentDocCrcR;
    }

    public void setAssessmentDocCrcR(String assessmentDocCrcR) {
        this.assessmentDocCrcR = assessmentDocCrcR;
    }

    public String getAssessmentDocNameCrcR() {
        return assessmentDocNameCrcR;
    }

    public void setAssessmentDocNameCrcR(String assessmentDocNameCrcR) {
        this.assessmentDocNameCrcR = assessmentDocNameCrcR;
    }

    public Date getAssessmentDateCrcR() {
        return assessmentDateCrcR;
    }

    public void setAssessmentDateCrcR(Date assessmentDateCrcR) {
        this.assessmentDateCrcR = assessmentDateCrcR;
    }

    public String getRiskQualityFlagCrcR() {
        return riskQualityFlagCrcR;
    }

    public void setRiskQualityFlagCrcR(String riskQualityFlagCrcR) {
        this.riskQualityFlagCrcR = riskQualityFlagCrcR;
    }

    public Date getUploadTimeCrcR() {
        return uploadTimeCrcR;
    }

    public void setUploadTimeCrcR(Date uploadTimeCrcR) {
        this.uploadTimeCrcR = uploadTimeCrcR;
    }

    public String getIschangeCrcR() {
        return ischangeCrcR;
    }

    public void setIschangeCrcR(String ischangeCrcR) {
        this.ischangeCrcR = ischangeCrcR;
    }

    public String getIdLicR() {
        return idLicR;
    }

    public void setIdLicR(String idLicR) {
        this.idLicR = idLicR;
    }

    public String getLicCheckId() {
        return licCheckId;
    }

    public void setLicCheckId(String licCheckId) {
        this.licCheckId = licCheckId;
    }

    public String getYigan() {
        return yigan;
    }

    public void setYigan(String yigan) {
        this.yigan = yigan;
    }

    public String getBinggan() {
        return binggan;
    }

    public void setBinggan(String binggan) {
        this.binggan = binggan;
    }

    public String getGanyinghua() {
        return ganyinghua;
    }

    public void setGanyinghua(String ganyinghua) {
        this.ganyinghua = ganyinghua;
    }

    public String getAizhengshiLicR() {
        return aizhengshiLicR;
    }

    public void setAizhengshiLicR(String aizhengshiLicR) {
        this.aizhengshiLicR = aizhengshiLicR;
    }

    public String getQinshuAizhengshiLicR() {
        return qinshuAizhengshiLicR;
    }

    public void setQinshuAizhengshiLicR(String qinshuAizhengshiLicR) {
        this.qinshuAizhengshiLicR = qinshuAizhengshiLicR;
    }

    public String getAssessmentResultLicR() {
        return assessmentResultLicR;
    }

    public void setAssessmentResultLicR(String assessmentResultLicR) {
        this.assessmentResultLicR = assessmentResultLicR;
    }

    public String getAssessmentDocLicR() {
        return assessmentDocLicR;
    }

    public void setAssessmentDocLicR(String assessmentDocLicR) {
        this.assessmentDocLicR = assessmentDocLicR;
    }

    public String getAssessmentDocNameLicR() {
        return assessmentDocNameLicR;
    }

    public void setAssessmentDocNameLicR(String assessmentDocNameLicR) {
        this.assessmentDocNameLicR = assessmentDocNameLicR;
    }

    public Date getAssessmentDateLicR() {
        return assessmentDateLicR;
    }

    public void setAssessmentDateLicR(Date assessmentDateLicR) {
        this.assessmentDateLicR = assessmentDateLicR;
    }

    public String getRiskQualityFlagLicR() {
        return riskQualityFlagLicR;
    }

    public void setRiskQualityFlagLicR(String riskQualityFlagLicR) {
        this.riskQualityFlagLicR = riskQualityFlagLicR;
    }

    public Date getUploadTimeLicR() {
        return uploadTimeLicR;
    }

    public void setUploadTimeLicR(Date uploadTimeLicR) {
        this.uploadTimeLicR = uploadTimeLicR;
    }

    public String getIdScR() {
        return idScR;
    }

    public void setIdScR(String idScR) {
        this.idScR = idScR;
    }

    public String getScCheckId() {
        return scCheckId;
    }

    public void setScCheckId(String scCheckId) {
        this.scCheckId = scCheckId;
    }

    public String getXinxianshucai() {
        return xinxianshucai;
    }

    public void setXinxianshucai(String xinxianshucai) {
        this.xinxianshucai = xinxianshucai;
    }

    public String getXinxianshuiguo() {
        return xinxianshuiguo;
    }

    public void setXinxianshuiguo(String xinxianshuiguo) {
        this.xinxianshuiguo = xinxianshuiguo;
    }

    public String getXinxianroulei() {
        return xinxianroulei;
    }

    public void setXinxianroulei(String xinxianroulei) {
        this.xinxianroulei = xinxianroulei;
    }

    public String getJiagongroulei() {
        return jiagongroulei;
    }

    public void setJiagongroulei(String jiagongroulei) {
        this.jiagongroulei = jiagongroulei;
    }

    public String getMeibianshiwu() {
        return meibianshiwu;
    }

    public void setMeibianshiwu(String meibianshiwu) {
        this.meibianshiwu = meibianshiwu;
    }

    public String getHejiu() {
        return hejiu;
    }

    public void setHejiu(String hejiu) {
        this.hejiu = hejiu;
    }

    public String getHejiupinlv() {
        return hejiupinlv;
    }

    public void setHejiupinlv(String hejiupinlv) {
        this.hejiupinlv = hejiupinlv;
    }

    public String getShifouzhidaohejiuliang() {
        return shifouzhidaohejiuliang;
    }

    public void setShifouzhidaohejiuliang(String shifouzhidaohejiuliang) {
        this.shifouzhidaohejiuliang = shifouzhidaohejiuliang;
    }

    public Integer getHejiuliang() {
        return hejiuliang;
    }

    public void setHejiuliang(Integer hejiuliang) {
        this.hejiuliang = hejiuliang;
    }

    public String getWeisuoxingweiyan() {
        return weisuoxingweiyan;
    }

    public void setWeisuoxingweiyan(String weisuoxingweiyan) {
        this.weisuoxingweiyan = weisuoxingweiyan;
    }

    public String getShierzhichangkuiyang() {
        return shierzhichangkuiyang;
    }

    public void setShierzhichangkuiyang(String shierzhichangkuiyang) {
        this.shierzhichangkuiyang = shierzhichangkuiyang;
    }

    public String getWeichuxue() {
        return weichuxue;
    }

    public void setWeichuxue(String weichuxue) {
        this.weichuxue = weichuxue;
    }

    public String getShiguanyan() {
        return shiguanyan;
    }

    public void setShiguanyan(String shiguanyan) {
        this.shiguanyan = shiguanyan;
    }

    public String getWeixirou() {
        return weixirou;
    }

    public void setWeixirou(String weixirou) {
        this.weixirou = weixirou;
    }

    public String getCanwei() {
        return canwei;
    }

    public void setCanwei(String canwei) {
        this.canwei = canwei;
    }

    public String getPinxue() {
        return pinxue;
    }

    public void setPinxue(String pinxue) {
        this.pinxue = pinxue;
    }

    public String getAizhengshiScR() {
        return aizhengshiScR;
    }

    public void setAizhengshiScR(String aizhengshiScR) {
        this.aizhengshiScR = aizhengshiScR;
    }

    public String getQinshuAizhengshiScR() {
        return qinshuAizhengshiScR;
    }

    public void setQinshuAizhengshiScR(String qinshuAizhengshiScR) {
        this.qinshuAizhengshiScR = qinshuAizhengshiScR;
    }

    public String getAssessmentResultScR() {
        return assessmentResultScR;
    }

    public void setAssessmentResultScR(String assessmentResultScR) {
        this.assessmentResultScR = assessmentResultScR;
    }

    public String getAssessmentDocScR() {
        return assessmentDocScR;
    }

    public void setAssessmentDocScR(String assessmentDocScR) {
        this.assessmentDocScR = assessmentDocScR;
    }

    public String getAssessmentDocNameScR() {
        return assessmentDocNameScR;
    }

    public void setAssessmentDocNameScR(String assessmentDocNameScR) {
        this.assessmentDocNameScR = assessmentDocNameScR;
    }

    public Date getAssessmentDateScR() {
        return assessmentDateScR;
    }

    public void setAssessmentDateScR(Date assessmentDateScR) {
        this.assessmentDateScR = assessmentDateScR;
    }

    public String getRiskQualityFlagScR() {
        return riskQualityFlagScR;
    }

    public void setRiskQualityFlagScR(String riskQualityFlagScR) {
        this.riskQualityFlagScR = riskQualityFlagScR;
    }

    public Date getUploadTimeScR() {
        return uploadTimeScR;
    }

    public void setUploadTimeScR(Date uploadTimeScR) {
        this.uploadTimeScR = uploadTimeScR;
    }

    public String getIdLucR() {
        return idLucR;
    }

    public void setIdLucR(String idLucR) {
        this.idLucR = idLucR;
    }

    public String getLucCheckId() {
        return lucCheckId;
    }

    public void setLucCheckId(String lucCheckId) {
        this.lucCheckId = lucCheckId;
    }

    public String getChufangyouyan() {
        return chufangyouyan;
    }

    public void setChufangyouyan(String chufangyouyan) {
        this.chufangyouyan = chufangyouyan;
    }

    public String getYouyanjiechupinlv() {
        return youyanjiechupinlv;
    }

    public void setYouyanjiechupinlv(String youyanjiechupinlv) {
        this.youyanjiechupinlv = youyanjiechupinlv;
    }

    public Integer getYouyanjiechunianshu() {
        return youyanjiechunianshu;
    }

    public void setYouyanjiechunianshu(Integer youyanjiechunianshu) {
        this.youyanjiechunianshu = youyanjiechunianshu;
    }

    public String getChouyouyanji() {
        return chouyouyanji;
    }

    public void setChouyouyanji(String chouyouyanji) {
        this.chouyouyanji = chouyouyanji;
    }

    public String getChouyouyanjinianshu() {
        return chouyouyanjinianshu;
    }

    public void setChouyouyanjinianshu(String chouyouyanjinianshu) {
        this.chouyouyanjinianshu = chouyouyanjinianshu;
    }

    public String getXiyanLucR() {
        return xiyanLucR;
    }

    public void setXiyanLucR(String xiyanLucR) {
        this.xiyanLucR = xiyanLucR;
    }

    public Integer getZuihouyicxiyan() {
        return zuihouyicxiyan;
    }

    public void setZuihouyicxiyan(Integer zuihouyicxiyan) {
        this.zuihouyicxiyan = zuihouyicxiyan;
    }

    public Integer getKaishixiyannianling() {
        return kaishixiyannianling;
    }

    public void setKaishixiyannianling(Integer kaishixiyannianling) {
        this.kaishixiyannianling = kaishixiyannianling;
    }

    public Integer getMeitianxiyanliang() {
        return meitianxiyanliang;
    }

    public void setMeitianxiyanliang(Integer meitianxiyanliang) {
        this.meitianxiyanliang = meitianxiyanliang;
    }

    public String getBeidongxiyan() {
        return beidongxiyan;
    }

    public void setBeidongxiyan(String beidongxiyan) {
        this.beidongxiyan = beidongxiyan;
    }

    public String getBeidongxiyanduixiang() {
        return beidongxiyanduixiang;
    }

    public void setBeidongxiyanduixiang(String beidongxiyanduixiang) {
        this.beidongxiyanduixiang = beidongxiyanduixiang;
    }

    public Integer getBeidongxiyannianshu() {
        return beidongxiyannianshu;
    }

    public void setBeidongxiyannianshu(Integer beidongxiyannianshu) {
        this.beidongxiyannianshu = beidongxiyannianshu;
    }

    public String getZhiyezhiaiwuzhi() {
        return zhiyezhiaiwuzhi;
    }

    public void setZhiyezhiaiwuzhi(String zhiyezhiaiwuzhi) {
        this.zhiyezhiaiwuzhi = zhiyezhiaiwuzhi;
    }

    public String getZhiaiwuzhileixing() {
        return zhiaiwuzhileixing;
    }

    public void setZhiaiwuzhileixing(String zhiaiwuzhileixing) {
        this.zhiaiwuzhileixing = zhiaiwuzhileixing;
    }

    public Integer getZhiaiwuzhiNianshu() {
        return zhiaiwuzhiNianshu;
    }

    public void setZhiaiwuzhiNianshu(Integer zhiaiwuzhiNianshu) {
        this.zhiaiwuzhiNianshu = zhiaiwuzhiNianshu;
    }

    public String getXiaochuan() {
        return xiaochuan;
    }

    public void setXiaochuan(String xiaochuan) {
        this.xiaochuan = xiaochuan;
    }

    public Integer getXiaochuanNianshu() {
        return xiaochuanNianshu;
    }

    public void setXiaochuanNianshu(Integer xiaochuanNianshu) {
        this.xiaochuanNianshu = xiaochuanNianshu;
    }

    public String getZhiqiguanyan() {
        return zhiqiguanyan;
    }

    public void setZhiqiguanyan(String zhiqiguanyan) {
        this.zhiqiguanyan = zhiqiguanyan;
    }

    public Integer getZhiqiguanyanNianshu() {
        return zhiqiguanyanNianshu;
    }

    public void setZhiqiguanyanNianshu(Integer zhiqiguanyanNianshu) {
        this.zhiqiguanyanNianshu = zhiqiguanyanNianshu;
    }

    public String getFeiqizhong() {
        return feiqizhong;
    }

    public void setFeiqizhong(String feiqizhong) {
        this.feiqizhong = feiqizhong;
    }

    public Integer getFeiqizhongNianshu() {
        return feiqizhongNianshu;
    }

    public void setFeiqizhongNianshu(Integer feiqizhongNianshu) {
        this.feiqizhongNianshu = feiqizhongNianshu;
    }

    public String getFeijiehe() {
        return feijiehe;
    }

    public void setFeijiehe(String feijiehe) {
        this.feijiehe = feijiehe;
    }

    public Integer getFeijieheNianshu() {
        return feijieheNianshu;
    }

    public void setFeijieheNianshu(Integer feijieheNianshu) {
        this.feijieheNianshu = feijieheNianshu;
    }

    public String getGuichenzhuo() {
        return guichenzhuo;
    }

    public void setGuichenzhuo(String guichenzhuo) {
        this.guichenzhuo = guichenzhuo;
    }

    public Integer getGuichenzhuoNianshu() {
        return guichenzhuoNianshu;
    }

    public void setGuichenzhuoNianshu(Integer guichenzhuoNianshu) {
        this.guichenzhuoNianshu = guichenzhuoNianshu;
    }

    public String getXianweihua() {
        return xianweihua;
    }

    public void setXianweihua(String xianweihua) {
        this.xianweihua = xianweihua;
    }

    public Integer getXianweihuaNianshu() {
        return xianweihuaNianshu;
    }

    public void setXianweihuaNianshu(Integer xianweihuaNianshu) {
        this.xianweihuaNianshu = xianweihuaNianshu;
    }

    public String getManzufei() {
        return manzufei;
    }

    public void setManzufei(String manzufei) {
        this.manzufei = manzufei;
    }

    public Integer getManzufeiNianshu() {
        return manzufeiNianshu;
    }

    public void setManzufeiNianshu(Integer manzufeiNianshu) {
        this.manzufeiNianshu = manzufeiNianshu;
    }

    public String getQitafeibing() {
        return qitafeibing;
    }

    public void setQitafeibing(String qitafeibing) {
        this.qitafeibing = qitafeibing;
    }

    public Integer getQitafeibingNianshu() {
        return qitafeibingNianshu;
    }

    public void setQitafeibingNianshu(Integer qitafeibingNianshu) {
        this.qitafeibingNianshu = qitafeibingNianshu;
    }

    public String getGanke() {
        return ganke;
    }

    public void setGanke(String ganke) {
        this.ganke = ganke;
    }

    public Integer getGankeTianshu() {
        return gankeTianshu;
    }

    public void setGankeTianshu(Integer gankeTianshu) {
        this.gankeTianshu = gankeTianshu;
    }

    public String getTanzhongdaixue() {
        return tanzhongdaixue;
    }

    public void setTanzhongdaixue(String tanzhongdaixue) {
        this.tanzhongdaixue = tanzhongdaixue;
    }

    public Integer getTanzhongdaixTianshu() {
        return tanzhongdaixTianshu;
    }

    public void setTanzhongdaixTianshu(Integer tanzhongdaixTianshu) {
        this.tanzhongdaixTianshu = tanzhongdaixTianshu;
    }

    public String getQiji() {
        return qiji;
    }

    public void setQiji(String qiji) {
        this.qiji = qiji;
    }

    public Integer getQijiTianshu() {
        return qijiTianshu;
    }

    public void setQijiTianshu(Integer qijiTianshu) {
        this.qijiTianshu = qijiTianshu;
    }

    public String getXiongmen() {
        return xiongmen;
    }

    public void setXiongmen(String xiongmen) {
        this.xiongmen = xiongmen;
    }

    public Integer getXiongmenTianshu() {
        return xiongmenTianshu;
    }

    public void setXiongmenTianshu(Integer xiongmenTianshu) {
        this.xiongmenTianshu = xiongmenTianshu;
    }

    public String getXiongtong() {
        return xiongtong;
    }

    public void setXiongtong(String xiongtong) {
        this.xiongtong = xiongtong;
    }

    public Integer getXiongtongTianshu() {
        return xiongtongTianshu;
    }

    public void setXiongtongTianshu(Integer xiongtongTianshu) {
        this.xiongtongTianshu = xiongtongTianshu;
    }

    public String getShengyinsiya() {
        return shengyinsiya;
    }

    public void setShengyinsiya(String shengyinsiya) {
        this.shengyinsiya = shengyinsiya;
    }

    public String getTongbuweifeiyan() {
        return tongbuweifeiyan;
    }

    public void setTongbuweifeiyan(String tongbuweifeiyan) {
        this.tongbuweifeiyan = tongbuweifeiyan;
    }

    public String getFeijiejie() {
        return feijiejie;
    }

    public void setFeijiejie(String feijiejie) {
        this.feijiejie = feijiejie;
    }

    public String getJiejiedaxiao() {
        return jiejiedaxiao;
    }

    public void setJiejiedaxiao(String jiejiedaxiao) {
        this.jiejiedaxiao = jiejiedaxiao;
    }

    public String getJiejiexingtai() {
        return jiejiexingtai;
    }

    public void setJiejiexingtai(String jiejiexingtai) {
        this.jiejiexingtai = jiejiexingtai;
    }

    public String getMaoboli() {
        return maoboli;
    }

    public void setMaoboli(String maoboli) {
        this.maoboli = maoboli;
    }

    public String getShixingjiejie() {
        return shixingjiejie;
    }

    public void setShixingjiejie(String shixingjiejie) {
        this.shixingjiejie = shixingjiejie;
    }

    public String getAizhengshiLucR() {
        return aizhengshiLucR;
    }

    public void setAizhengshiLucR(String aizhengshiLucR) {
        this.aizhengshiLucR = aizhengshiLucR;
    }

    public String getQinshuAizhengshiLucR() {
        return qinshuAizhengshiLucR;
    }

    public void setQinshuAizhengshiLucR(String qinshuAizhengshiLucR) {
        this.qinshuAizhengshiLucR = qinshuAizhengshiLucR;
    }

    public String getAssessmentResultLucR() {
        return assessmentResultLucR;
    }

    public void setAssessmentResultLucR(String assessmentResultLucR) {
        this.assessmentResultLucR = assessmentResultLucR;
    }

    public String getAssessmentDocLucR() {
        return assessmentDocLucR;
    }

    public void setAssessmentDocLucR(String assessmentDocLucR) {
        this.assessmentDocLucR = assessmentDocLucR;
    }

    public String getAssessmentDocNameLucR() {
        return assessmentDocNameLucR;
    }

    public void setAssessmentDocNameLucR(String assessmentDocNameLucR) {
        this.assessmentDocNameLucR = assessmentDocNameLucR;
    }

    public Date getAssessmentDateLucR() {
        return assessmentDateLucR;
    }

    public void setAssessmentDateLucR(Date assessmentDateLucR) {
        this.assessmentDateLucR = assessmentDateLucR;
    }

    public String getRiskQualityFlagLucR() {
        return riskQualityFlagLucR;
    }

    public void setRiskQualityFlagLucR(String riskQualityFlagLucR) {
        this.riskQualityFlagLucR = riskQualityFlagLucR;
    }

    public Date getUploadTimeLucR() {
        return uploadTimeLucR;
    }

    public void setUploadTimeLucR(Date uploadTimeLucR) {
        this.uploadTimeLucR = uploadTimeLucR;
    }

    public String getIdCrcF() {
        return idCrcF;
    }

    public void setIdCrcF(String idCrcF) {
        this.idCrcF = idCrcF;
    }

    public String getCrcCheckIdCrcF() {
        return crcCheckIdCrcF;
    }

    public void setCrcCheckIdCrcF(String crcCheckIdCrcF) {
        this.crcCheckIdCrcF = crcCheckIdCrcF;
    }

    public String getFirstFobtResult() {
        return firstFobtResult;
    }

    public void setFirstFobtResult(String firstFobtResult) {
        this.firstFobtResult = firstFobtResult;
    }

    public String getSecondFobtResult() {
        return secondFobtResult;
    }

    public void setSecondFobtResult(String secondFobtResult) {
        this.secondFobtResult = secondFobtResult;
    }

    public Date getFirstFobtDate() {
        return firstFobtDate;
    }

    public void setFirstFobtDate(Date firstFobtDate) {
        this.firstFobtDate = firstFobtDate;
    }

    public Date getSecondFobtDate() {
        return secondFobtDate;
    }

    public void setSecondFobtDate(Date secondFobtDate) {
        this.secondFobtDate = secondFobtDate;
    }

    public String getFobtResult() {
        return fobtResult;
    }

    public void setFobtResult(String fobtResult) {
        this.fobtResult = fobtResult;
    }

    public String getFobtDoc() {
        return fobtDoc;
    }

    public void setFobtDoc(String fobtDoc) {
        this.fobtDoc = fobtDoc;
    }

    public String getFobtDocName() {
        return fobtDocName;
    }

    public void setFobtDocName(String fobtDocName) {
        this.fobtDocName = fobtDocName;
    }

    public Date getUploadTimeCrcF() {
        return uploadTimeCrcF;
    }

    public void setUploadTimeCrcF(Date uploadTimeCrcF) {
        this.uploadTimeCrcF = uploadTimeCrcF;
    }

    public String getIschangeCrcF() {
        return ischangeCrcF;
    }

    public void setIschangeCrcF(String ischangeCrcF) {
        this.ischangeCrcF = ischangeCrcF;
    }

    public String getIdLica() {
        return idLica;
    }

    public void setIdLica(String idLica) {
        this.idLica = idLica;
    }

    public String getLicCheckIdLica() {
        return licCheckIdLica;
    }

    public void setLicCheckIdLica(String licCheckIdLica) {
        this.licCheckIdLica = licCheckIdLica;
    }

    public Integer getGtp() {
        return gtp;
    }

    public void setGtp(Integer gtp) {
        this.gtp = gtp;
    }

    public String getHbeg() {
        return hbeg;
    }

    public void setHbeg(String hbeg) {
        this.hbeg = hbeg;
    }

    public Integer getAfp() {
        return afp;
    }

    public void setAfp(Integer afp) {
        this.afp = afp;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getLicAssistResult() {
        return licAssistResult;
    }

    public void setLicAssistResult(String licAssistResult) {
        this.licAssistResult = licAssistResult;
    }

    public String getLicAssistDoc() {
        return licAssistDoc;
    }

    public void setLicAssistDoc(String licAssistDoc) {
        this.licAssistDoc = licAssistDoc;
    }

    public String getLicAssistDocName() {
        return licAssistDocName;
    }

    public void setLicAssistDocName(String licAssistDocName) {
        this.licAssistDocName = licAssistDocName;
    }

    public Date getLicAssistDate() {
        return licAssistDate;
    }

    public void setLicAssistDate(Date licAssistDate) {
        this.licAssistDate = licAssistDate;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<CancerHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<CancerHistory> historyList) {
        this.historyList = historyList;
    }

    public List<FamilyCancerHistory> getFamilyCancerHistoryList() {
        return familyCancerHistoryList;
    }

    public void setFamilyCancerHistoryList(List<FamilyCancerHistory> familyCancerHistoryList) {
        this.familyCancerHistoryList = familyCancerHistoryList;
    }

    public List<LucFamilyCancerHistoryXH> getLucFamilyCancerHistoryXHList() {
        return lucFamilyCancerHistoryXHList;
    }

    public void setLucFamilyCancerHistoryXHList(List<LucFamilyCancerHistoryXH> lucFamilyCancerHistoryXHList) {
        this.lucFamilyCancerHistoryXHList = lucFamilyCancerHistoryXHList;
    }
}
