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
 * 肺癌危险度评估表实体
 * @author menglianghai
 */
public class LucRiskAssessment extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

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
	private String xiyan;	// 是否吸烟 cdc_dic_personinfo id=60008code=1：不吸；code=2：过去吸，现在不吸；code=3：现在吸

	
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

//	@Length(max=2)
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
	private String aizhengshi;	// 有无癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在癌症史表中有对应记录)

	@Length(max=1)
	private String qinshuAizhengshi;	// 一级亲属癌症史 cdc_dic_personinfo id=60007 code=1：有；code=2：无 (若为1时,则在亲属大肠癌史中有对应记录)

	@Length(max=1)
	@NotNull
	private String assessmentResult;	// 危险度评估结果 cdc_dic_personinfo id=60001 code=1：阴性；code=2：阳性

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
	private String riskQualityFlag;	// 危险度评估质控标志 cdc_dic_personinfo id=60005 code=1：未参与；code=2：已参与

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadTime;	// 上传时间


	public LucRiskAssessment() {
		super();
	}
	public LucRiskAssessment(User user) {
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

	@JsonProperty("lucCheckId")
	public String getLucCheckId() {
		return lucCheckId;
	}

	public void setLucCheckId(String lucCheckId) {
		this.lucCheckId = lucCheckId;
	}

	@JsonProperty("chufangyouyan")
	public String getChufangyouyan() {
		return chufangyouyan;
	}

	public void setChufangyouyan(String chufangyouyan) {
		this.chufangyouyan = chufangyouyan;
	}

	@JsonProperty("youyanjiechupinlv")
	public String getYouyanjiechupinlv() {
		return youyanjiechupinlv;
	}

	public void setYouyanjiechupinlv(String youyanjiechupinlv) {
		this.youyanjiechupinlv = youyanjiechupinlv;
	}

	@JsonProperty("youyanjiechunianshu")
	public Integer getYouyanjiechunianshu() {
		return youyanjiechunianshu;
	}

	public void setYouyanjiechunianshu(Integer youyanjiechunianshu) {
		this.youyanjiechunianshu = youyanjiechunianshu;
	}

	@JsonProperty("chouyouyanji")
	public String getChouyouyanji() {
		return chouyouyanji;
	}

	public void setChouyouyanji(String chouyouyanji) {
		this.chouyouyanji = chouyouyanji;
	}

	@JsonProperty("chouyouyanjinianshu")
	public String getChouyouyanjinianshu() {
		return chouyouyanjinianshu;
	}

	public void setChouyouyanjinianshu(String chouyouyanjinianshu) {
		this.chouyouyanjinianshu = chouyouyanjinianshu;
	}

	@JsonProperty("xiyan")
	public String getXiyan() {
		return xiyan;
	}

	public void setXiyan(String xiyan) {
		this.xiyan = xiyan;
	}

	@JsonProperty("zuihouyicxiyan")
	public Integer getZuihouyicxiyan() {
		return zuihouyicxiyan;
	}

	public void setZuihouyicxiyan(Integer zuihouyicxiyan) {
		this.zuihouyicxiyan = zuihouyicxiyan;
	}

	@JsonProperty("kaishixiyannianling")
	public Integer getKaishixiyannianling() {
		return kaishixiyannianling;
	}

	public void setKaishixiyannianling(Integer kaishixiyannianling) {
		this.kaishixiyannianling = kaishixiyannianling;
	}

	@JsonProperty("meitianxiyanliang")
	public Integer getMeitianxiyanliang() {
		return meitianxiyanliang;
	}

	public void setMeitianxiyanliang(Integer meitianxiyanliang) {
		this.meitianxiyanliang = meitianxiyanliang;
	}

	@JsonProperty("beidongxiyan")
	public String getBeidongxiyan() {
		return beidongxiyan;
	}

	public void setBeidongxiyan(String beidongxiyan) {
		this.beidongxiyan = beidongxiyan;
	}

	@JsonProperty("beidongxiyanduixiang")
	public String getBeidongxiyanduixiang() {
		return beidongxiyanduixiang;
	}

	public void setBeidongxiyanduixiang(String beidongxiyanduixiang) {
		this.beidongxiyanduixiang = beidongxiyanduixiang;
	}

	@JsonProperty("beidongxiyannianshu")
	public Integer getBeidongxiyannianshu() {
		return beidongxiyannianshu;
	}

	public void setBeidongxiyannianshu(Integer beidongxiyannianshu) {
		this.beidongxiyannianshu = beidongxiyannianshu;
	}

	@JsonProperty("zhiyezhiaiwuzhi")
	public String getZhiyezhiaiwuzhi() {
		return zhiyezhiaiwuzhi;
	}

	public void setZhiyezhiaiwuzhi(String zhiyezhiaiwuzhi) {
		this.zhiyezhiaiwuzhi = zhiyezhiaiwuzhi;
	}

	@JsonProperty("zhiaiwuzhileixing")
	public String getZhiaiwuzhileixing() {
		return zhiaiwuzhileixing;
	}

	public void setZhiaiwuzhileixing(String zhiaiwuzhileixing) {
		this.zhiaiwuzhileixing = zhiaiwuzhileixing;
	}

	@JsonProperty("zhiaiwuzhiNianshu")
	public Integer getZhiaiwuzhiNianshu() {
		return zhiaiwuzhiNianshu;
	}

	public void setZhiaiwuzhiNianshu(Integer zhiaiwuzhiNianshu) {
		this.zhiaiwuzhiNianshu = zhiaiwuzhiNianshu;
	}

	@JsonProperty("xiaochuan")
	public String getXiaochuan() {
		return xiaochuan;
	}

	public void setXiaochuan(String xiaochuan) {
		this.xiaochuan = xiaochuan;
	}

	@JsonProperty("xiaochuanNianshu")
	public Integer getXiaochuanNianshu() {
		return xiaochuanNianshu;
	}

	public void setXiaochuanNianshu(Integer xiaochuanNianshu) {
		this.xiaochuanNianshu = xiaochuanNianshu;
	}

	@JsonProperty("zhiqiguanyan")
	public String getZhiqiguanyan() {
		return zhiqiguanyan;
	}

	public void setZhiqiguanyan(String zhiqiguanyan) {
		this.zhiqiguanyan = zhiqiguanyan;
	}

	@JsonProperty("zhiqiguanyanNianshu")
	public Integer getZhiqiguanyanNianshu() {
		return zhiqiguanyanNianshu;
	}

	public void setZhiqiguanyanNianshu(Integer zhiqiguanyanNianshu) {
		this.zhiqiguanyanNianshu = zhiqiguanyanNianshu;
	}

	@JsonProperty("feiqizhong")
	public String getFeiqizhong() {
		return feiqizhong;
	}

	public void setFeiqizhong(String feiqizhong) {
		this.feiqizhong = feiqizhong;
	}

	@JsonProperty("feiqizhongNianshu")
	public Integer getFeiqizhongNianshu() {
		return feiqizhongNianshu;
	}

	public void setFeiqizhongNianshu(Integer feiqizhongNianshu) {
		this.feiqizhongNianshu = feiqizhongNianshu;
	}

	@JsonProperty("feijiehe")
	public String getFeijiehe() {
		return feijiehe;
	}

	public void setFeijiehe(String feijiehe) {
		this.feijiehe = feijiehe;
	}

	@JsonProperty("feijieheNianshu")
	public Integer getFeijieheNianshu() {
		return feijieheNianshu;
	}

	public void setFeijieheNianshu(Integer feijieheNianshu) {
		this.feijieheNianshu = feijieheNianshu;
	}

	@JsonProperty("guichenzhuo")
	public String getGuichenzhuo() {
		return guichenzhuo;
	}

	public void setGuichenzhuo(String guichenzhuo) {
		this.guichenzhuo = guichenzhuo;
	}

	@JsonProperty("guichenzhuoNianshu")
	public Integer getGuichenzhuoNianshu() {
		return guichenzhuoNianshu;
	}

	public void setGuichenzhuoNianshu(Integer guichenzhuoNianshu) {
		this.guichenzhuoNianshu = guichenzhuoNianshu;
	}

	@JsonProperty("xianweihua")
	public String getXianweihua() {
		return xianweihua;
	}

	public void setXianweihua(String xianweihua) {
		this.xianweihua = xianweihua;
	}

	@JsonProperty("xianweihuaNianshu")
	public Integer getXianweihuaNianshu() {
		return xianweihuaNianshu;
	}

	public void setXianweihuaNianshu(Integer xianweihuaNianshu) {
		this.xianweihuaNianshu = xianweihuaNianshu;
	}

	@JsonProperty("manzufei")
	public String getManzufei() {
		return manzufei;
	}

	public void setManzufei(String manzufei) {
		this.manzufei = manzufei;
	}

	@JsonProperty("manzufeiNianshu")
	public Integer getManzufeiNianshu() {
		return manzufeiNianshu;
	}

	public void setManzufeiNianshu(Integer manzufeiNianshu) {
		this.manzufeiNianshu = manzufeiNianshu;
	}

	@JsonProperty("qitafeibing")
	public String getQitafeibing() {
		return qitafeibing;
	}

	public void setQitafeibing(String qitafeibing) {
		this.qitafeibing = qitafeibing;
	}

	@JsonProperty("qitafeibingNianshu")
	public Integer getQitafeibingNianshu() {
		return qitafeibingNianshu;
	}

	public void setQitafeibingNianshu(Integer qitafeibingNianshu) {
		this.qitafeibingNianshu = qitafeibingNianshu;
	}

	@JsonProperty("ganke")
	public String getGanke() {
		return ganke;
	}

	public void setGanke(String ganke) {
		this.ganke = ganke;
	}

	@JsonProperty("gankeTianshu")
	public Integer getGankeTianshu() {
		return gankeTianshu;
	}

	public void setGankeTianshu(Integer gankeTianshu) {
		this.gankeTianshu = gankeTianshu;
	}

	@JsonProperty("tanzhongdaixue")
	public String getTanzhongdaixue() {
		return tanzhongdaixue;
	}

	public void setTanzhongdaixue(String tanzhongdaixue) {
		this.tanzhongdaixue = tanzhongdaixue;
	}

	@JsonProperty("tanzhongdaixTianshu")
	public Integer getTanzhongdaixTianshu() {
		return tanzhongdaixTianshu;
	}

	public void setTanzhongdaixTianshu(Integer tanzhongdaixTianshu) {
		this.tanzhongdaixTianshu = tanzhongdaixTianshu;
	}

	@JsonProperty("qiji")
	public String getQiji() {
		return qiji;
	}

	public void setQiji(String qiji) {
		this.qiji = qiji;
	}

	@JsonProperty("qijiTianshu")
	public Integer getQijiTianshu() {
		return qijiTianshu;
	}

	public void setQijiTianshu(Integer qijiTianshu) {
		this.qijiTianshu = qijiTianshu;
	}

	@JsonProperty("xiongmen")
	public String getXiongmen() {
		return xiongmen;
	}

	public void setXiongmen(String xiongmen) {
		this.xiongmen = xiongmen;
	}

	@JsonProperty("xiongmenTianshu")
	public Integer getXiongmenTianshu() {
		return xiongmenTianshu;
	}

	public void setXiongmenTianshu(Integer xiongmenTianshu) {
		this.xiongmenTianshu = xiongmenTianshu;
	}

	@JsonProperty("xiongtong")
	public String getXiongtong() {
		return xiongtong;
	}

	public void setXiongtong(String xiongtong) {
		this.xiongtong = xiongtong;
	}

	@JsonProperty("xiongtongTianshu")
	public Integer getXiongtongTianshu() {
		return xiongtongTianshu;
	}

	public void setXiongtongTianshu(Integer xiongtongTianshu) {
		this.xiongtongTianshu = xiongtongTianshu;
	}

	@JsonProperty("shengyinsiya")
	public String getShengyinsiya() {
		return shengyinsiya;
	}

	public void setShengyinsiya(String shengyinsiya) {
		this.shengyinsiya = shengyinsiya;
	}

	@JsonProperty("tongbuweifeiyan")
	public String getTongbuweifeiyan() {
		return tongbuweifeiyan;
	}

	public void setTongbuweifeiyan(String tongbuweifeiyan) {
		this.tongbuweifeiyan = tongbuweifeiyan;
	}

	@JsonProperty("feijiejie")
	public String getFeijiejie() {
		return feijiejie;
	}

	public void setFeijiejie(String feijiejie) {
		this.feijiejie = feijiejie;
	}

	@JsonProperty("jiejiedaxiao")
	public String getJiejiedaxiao() {
		return jiejiedaxiao;
	}

	public void setJiejiedaxiao(String jiejiedaxiao) {
		this.jiejiedaxiao = jiejiedaxiao;
	}

	@JsonProperty("jiejiexingtai")
	public String getJiejiexingtai() {
		return jiejiexingtai;
	}

	public void setJiejiexingtai(String jiejiexingtai) {
		this.jiejiexingtai = jiejiexingtai;
	}

	@JsonProperty("maoboli")
	public String getMaoboli() {
		return maoboli;
	}

	public void setMaoboli(String maoboli) {
		this.maoboli = maoboli;
	}

	@JsonProperty("shixingjiejie")
	public String getShixingjiejie() {
		return shixingjiejie;
	}

	public void setShixingjiejie(String shixingjiejie) {
		this.shixingjiejie = shixingjiejie;
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