/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;

import com.wonders.health.auth.client.vo.User;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 胃癌危险度评估表实体
 * @author menglianghai
 */
public class ScRiskAssessment extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

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
	private String aizhengshi;	// 有无癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在癌症史表中有对应记录)

	@Length(max=1)
	private String qinshuAizhengshi;	// 一级亲属癌症史 cdc_dic_personinfo id=60007code=1：有；code=2：无(若为1时,则在亲属癌症史中有对应记录)

	@Length(max=1)
	@NotNull
	private String assessmentResult;	// 危险度评估结果 cdc_dic_personinfo id=60001code=1：阴性；code=2：阳性

	@Length(max=32)
	@NotNull
	private String assessmentDoc;	// 评估医生代码

	@Length(max=64)
	@NotNull
	private String assessmentDocName;	// 评估医生签名

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date assessmentDate;	// 评估日期

	@Length(max=1)
	@NotNull
	private String riskQualityFlag;	// 危险度评估质控标志 cdc_dic_personinfo id=60005code=1：未参与；code=2：已参与

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间


	public ScRiskAssessment() {
		super();
	}
	public ScRiskAssessment(User user) {
		super();
		this.assessmentDocName=user.getName();
		this.assessmentDoc=user.getId();
		this.assessmentDate=new Date();
	}
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("scCheckId")
	public String getScCheckId() {
		return scCheckId;
	}

	public void setScCheckId(String scCheckId) {
		this.scCheckId = scCheckId;
	}

	@JsonProperty("xinxianshucai")
	public String getXinxianshucai() {
		return xinxianshucai;
	}

	public void setXinxianshucai(String xinxianshucai) {
		this.xinxianshucai = xinxianshucai;
	}

	@JsonProperty("xinxianshuiguo")
	public String getXinxianshuiguo() {
		return xinxianshuiguo;
	}

	public void setXinxianshuiguo(String xinxianshuiguo) {
		this.xinxianshuiguo = xinxianshuiguo;
	}

	@JsonProperty("xinxianroulei")
	public String getXinxianroulei() {
		return xinxianroulei;
	}

	public void setXinxianroulei(String xinxianroulei) {
		this.xinxianroulei = xinxianroulei;
	}

	@JsonProperty("jiagongroulei")
	public String getJiagongroulei() {
		return jiagongroulei;
	}

	public void setJiagongroulei(String jiagongroulei) {
		this.jiagongroulei = jiagongroulei;
	}

	@JsonProperty("meibianshiwu")
	public String getMeibianshiwu() {
		return meibianshiwu;
	}

	public void setMeibianshiwu(String meibianshiwu) {
		this.meibianshiwu = meibianshiwu;
	}

	@JsonProperty("hejiu")
	public String getHejiu() {
		return hejiu;
	}

	public void setHejiu(String hejiu) {
		this.hejiu = hejiu;
	}

	@JsonProperty("hejiupinlv")
	public String getHejiupinlv() {
		return hejiupinlv;
	}

	public void setHejiupinlv(String hejiupinlv) {
		this.hejiupinlv = hejiupinlv;
	}

	@JsonProperty("shifouzhidaohejiuliang")
	public String getShifouzhidaohejiuliang() {
		return shifouzhidaohejiuliang;
	}

	public void setShifouzhidaohejiuliang(String shifouzhidaohejiuliang) {
		this.shifouzhidaohejiuliang = shifouzhidaohejiuliang;
	}

	@JsonProperty("hejiuliang")
	public Integer getHejiuliang() {
		return hejiuliang;
	}

	public void setHejiuliang(Integer hejiuliang) {
		this.hejiuliang = hejiuliang;
	}

	@JsonProperty("weisuoxingweiyan")
	public String getWeisuoxingweiyan() {
		return weisuoxingweiyan;
	}

	public void setWeisuoxingweiyan(String weisuoxingweiyan) {
		this.weisuoxingweiyan = weisuoxingweiyan;
	}

	@JsonProperty("shierzhichangkuiyang")
	public String getShierzhichangkuiyang() {
		return shierzhichangkuiyang;
	}

	public void setShierzhichangkuiyang(String shierzhichangkuiyang) {
		this.shierzhichangkuiyang = shierzhichangkuiyang;
	}

	@JsonProperty("weichuxue")
	public String getWeichuxue() {
		return weichuxue;
	}

	public void setWeichuxue(String weichuxue) {
		this.weichuxue = weichuxue;
	}

	@JsonProperty("shiguanyan")
	public String getShiguanyan() {
		return shiguanyan;
	}

	public void setShiguanyan(String shiguanyan) {
		this.shiguanyan = shiguanyan;
	}

	@JsonProperty("weixirou")
	public String getWeixirou() {
		return weixirou;
	}

	public void setWeixirou(String weixirou) {
		this.weixirou = weixirou;
	}

	@JsonProperty("canwei")
	public String getCanwei() {
		return canwei;
	}

	public void setCanwei(String canwei) {
		this.canwei = canwei;
	}

	@JsonProperty("pinxue")
	public String getPinxue() {
		return pinxue;
	}

	public void setPinxue(String pinxue) {
		this.pinxue = pinxue;
	}

	@JsonProperty("aizhengshi")
	public String getAizhengshi() {
		return aizhengshi;
	}

	public void setAizhengshi(String aizhengshi) {
		this.aizhengshi = aizhengshi;
	}

	@JsonProperty("qinshuAizhengshi")
	public String getQinshuAizhengshi() {
		return qinshuAizhengshi;
	}

	public void setQinshuAizhengshi(String qinshuAizhengshi) {
		this.qinshuAizhengshi = qinshuAizhengshi;
	}

	@JsonProperty("assessmentResult")
	public String getAssessmentResult() {
		return assessmentResult;
	}

	public void setAssessmentResult(String assessmentResult) {
		this.assessmentResult = assessmentResult;
	}

	@JsonProperty("assessmentDoc")
	public String getAssessmentDoc() {
		return assessmentDoc;
	}

	public void setAssessmentDoc(String assessmentDoc) {
		this.assessmentDoc = assessmentDoc;
	}

	@JsonProperty("assessmentDocName")
	public String getAssessmentDocName() {
		return assessmentDocName;
	}

	public void setAssessmentDocName(String assessmentDocName) {
		this.assessmentDocName = assessmentDocName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("assessmentDate")
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	@JsonProperty("riskQualityFlag")
	public String getRiskQualityFlag() {
		return riskQualityFlag;
	}

	public void setRiskQualityFlag(String riskQualityFlag) {
		this.riskQualityFlag = riskQualityFlag;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("uploadTime")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}


}