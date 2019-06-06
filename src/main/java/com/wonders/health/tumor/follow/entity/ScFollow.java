package com.wonders.health.tumor.follow.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 胃癌随访管理表实体
 * @author zhangyi
 */
public class ScFollow extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=30)
	@NotNull
	private String primaryMark;	// 随访编号（医疗机构代码+自定义id）

	@Length(max=32)
	@NotNull
	private String scCheckId;	// 胃癌初筛信息登记id

	@Length(max=1)
	private String shifouweijing;	// 是否进行胃镜检查 字典表 id=60013 code=1：是；code=2：否

	@Length(max=2)
	private String buzuoweujingYuanyin;	// 不进行胃镜的原因 字典表 id=60029

	@Length(max=500)
	private String buzuoweijingYuanyinQt;	// 不进行胃镜的原因其他

	@Length(max=1)
	private String shifouquezhen;	// 是否确诊胃癌 字典表 id=60013 code=1：是；code=2：否

	@Length(max=1)
	private String bingli;	// 是否病理 字典表 id=60013 code=1：是；code=2：否

	@Length(max=1000)
	private String binglileixing;	// 病理类型

	@Length(max=500)
	private String zhongliuweizh;	// 肿瘤位置

	@Length(max=2)
	private String zhongliutnmT;	// 肿瘤TNM分期—T 字典表 id=60044

	@Length(max=2)
	private String zhongliutnmN;	// 肿瘤TNM分期—N 字典表 id=60045

	@Length(max=2)
	private String zhongliutnmM;	// 肿瘤TNM分期—M 字典表 id=60046

	@Length(max=1)
	private String shifouzhiliao;	// 是否治疗 字典表 id=60013 code=1：是；code=2：否

	@Length(max=25)
	private String shoushujigouId;	// 手术医疗机构编码

	@Length(max=256)
	private String shoushujigouName;	// 手术医疗机构名称

	
	private Date shoushuDate;	// 手术日期

	@Length(max=25)
	private String fangliaojigouId;	// 放疗医疗机构编码

	@Length(max=256)
	private String fangliaojigouName;	// 放疗医疗机构名称

	
	private Date fangliaoDate;	// 放疗日期

	@Length(max=25)
	private String hualiaojigouId;	// 化疗医疗机构编码

	@Length(max=256)
	private String hualiaojigouName;	// 化疗医疗机构名称

	
	private Date hualiaoDate;	// 化疗日期

	@Length(max=25)
	private String zhenduanjigouId;	// 诊断医疗机构编码

	@Length(max=256)
	private String zhenduanjigouName;	// 诊断医疗机构名称

	@Length(max=25)
	private String suifangjigouId;	// 随访医疗机构编码

	@Length(max=256)
	private String suifangjigouName;	// 随访医疗机构名称

	
	private Date suifangDate;	// 随访日期

	@Length(max=32)
	private String suifangyishengId;	// 随访医生ID

	@Length(max=25)
	private String dengjijigouId;	// 登记医疗机构编码

	
	private Date dengjiDate;	// 登记日期

	@Length(max=32)
	private String dengjiId;	// 登记人ID

	@Length(max=2)
	private String linchuangfenqi;	// 胃癌临床分期 字典表 id=60051

	@Length(max=1000)
	private String weijingJieguo;	// 胃镜检查结果

	@Length(max=1)
	private String huojian;	// 是否活检 字典表 id=60013 code=1：是；code=2：否

	@Length(max=500)
	private String huojianjieguo;	// 活检结果

	
	private Integer zhongliudaxiao;	// 肿瘤大小

	
	private Date zhenduanDate;	// 诊断日期


	public ScFollow() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("primaryMark")
	public String getPrimaryMark() {
		return primaryMark;
	}

	public void setPrimaryMark(String primaryMark) {
		this.primaryMark = primaryMark;
	}

	@JsonProperty("scCheckId")
	public String getScCheckId() {
		return scCheckId;
	}

	public void setScCheckId(String scCheckId) {
		this.scCheckId = scCheckId;
	}

	@JsonProperty("shifouweijing")
	public String getShifouweijing() {
		return shifouweijing;
	}

	public void setShifouweijing(String shifouweijing) {
		this.shifouweijing = shifouweijing;
	}

	@JsonProperty("buzuoweujingYuanyin")
	public String getBuzuoweujingYuanyin() {
		return buzuoweujingYuanyin;
	}

	public void setBuzuoweujingYuanyin(String buzuoweujingYuanyin) {
		this.buzuoweujingYuanyin = buzuoweujingYuanyin;
	}

	@JsonProperty("buzuoweijingYuanyinQt")
	public String getBuzuoweijingYuanyinQt() {
		return buzuoweijingYuanyinQt;
	}

	public void setBuzuoweijingYuanyinQt(String buzuoweijingYuanyinQt) {
		this.buzuoweijingYuanyinQt = buzuoweijingYuanyinQt;
	}

	@JsonProperty("shifouquezhen")
	public String getShifouquezhen() {
		return shifouquezhen;
	}

	public void setShifouquezhen(String shifouquezhen) {
		this.shifouquezhen = shifouquezhen;
	}

	@JsonProperty("bingli")
	public String getBingli() {
		return bingli;
	}

	public void setBingli(String bingli) {
		this.bingli = bingli;
	}

	@JsonProperty("binglileixing")
	public String getBinglileixing() {
		return binglileixing;
	}

	public void setBinglileixing(String binglileixing) {
		this.binglileixing = binglileixing;
	}

	@JsonProperty("zhongliuweizh")
	public String getZhongliuweizh() {
		return zhongliuweizh;
	}

	public void setZhongliuweizh(String zhongliuweizh) {
		this.zhongliuweizh = zhongliuweizh;
	}

	@JsonProperty("zhongliutnmT")
	public String getZhongliutnmT() {
		return zhongliutnmT;
	}

	public void setZhongliutnmT(String zhongliutnmT) {
		this.zhongliutnmT = zhongliutnmT;
	}

	@JsonProperty("zhongliutnmN")
	public String getZhongliutnmN() {
		return zhongliutnmN;
	}

	public void setZhongliutnmN(String zhongliutnmN) {
		this.zhongliutnmN = zhongliutnmN;
	}

	@JsonProperty("zhongliutnmM")
	public String getZhongliutnmM() {
		return zhongliutnmM;
	}

	public void setZhongliutnmM(String zhongliutnmM) {
		this.zhongliutnmM = zhongliutnmM;
	}

	@JsonProperty("shifouzhiliao")
	public String getShifouzhiliao() {
		return shifouzhiliao;
	}

	public void setShifouzhiliao(String shifouzhiliao) {
		this.shifouzhiliao = shifouzhiliao;
	}

	@JsonProperty("shoushujigouId")
	public String getShoushujigouId() {
		return shoushujigouId;
	}

	public void setShoushujigouId(String shoushujigouId) {
		this.shoushujigouId = shoushujigouId;
	}

	@JsonProperty("shoushujigouName")
	public String getShoushujigouName() {
		return shoushujigouName;
	}

	public void setShoushujigouName(String shoushujigouName) {
		this.shoushujigouName = shoushujigouName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("shoushuDate")
	public Date getShoushuDate() {
		return shoushuDate;
	}

	public void setShoushuDate(Date shoushuDate) {
		this.shoushuDate = shoushuDate;
	}

	@JsonProperty("fangliaojigouId")
	public String getFangliaojigouId() {
		return fangliaojigouId;
	}

	public void setFangliaojigouId(String fangliaojigouId) {
		this.fangliaojigouId = fangliaojigouId;
	}

	@JsonProperty("fangliaojigouName")
	public String getFangliaojigouName() {
		return fangliaojigouName;
	}

	public void setFangliaojigouName(String fangliaojigouName) {
		this.fangliaojigouName = fangliaojigouName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("fangliaoDate")
	public Date getFangliaoDate() {
		return fangliaoDate;
	}

	public void setFangliaoDate(Date fangliaoDate) {
		this.fangliaoDate = fangliaoDate;
	}

	@JsonProperty("hualiaojigouId")
	public String getHualiaojigouId() {
		return hualiaojigouId;
	}

	public void setHualiaojigouId(String hualiaojigouId) {
		this.hualiaojigouId = hualiaojigouId;
	}

	@JsonProperty("hualiaojigouName")
	public String getHualiaojigouName() {
		return hualiaojigouName;
	}

	public void setHualiaojigouName(String hualiaojigouName) {
		this.hualiaojigouName = hualiaojigouName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("hualiaoDate")
	public Date getHualiaoDate() {
		return hualiaoDate;
	}

	public void setHualiaoDate(Date hualiaoDate) {
		this.hualiaoDate = hualiaoDate;
	}

	@JsonProperty("zhenduanjigouId")
	public String getZhenduanjigouId() {
		return zhenduanjigouId;
	}

	public void setZhenduanjigouId(String zhenduanjigouId) {
		this.zhenduanjigouId = zhenduanjigouId;
	}

	@JsonProperty("zhenduanjigouName")
	public String getZhenduanjigouName() {
		return zhenduanjigouName;
	}

	public void setZhenduanjigouName(String zhenduanjigouName) {
		this.zhenduanjigouName = zhenduanjigouName;
	}

	@JsonProperty("suifangjigouId")
	public String getSuifangjigouId() {
		return suifangjigouId;
	}

	public void setSuifangjigouId(String suifangjigouId) {
		this.suifangjigouId = suifangjigouId;
	}

	@JsonProperty("suifangjigouName")
	public String getSuifangjigouName() {
		return suifangjigouName;
	}

	public void setSuifangjigouName(String suifangjigouName) {
		this.suifangjigouName = suifangjigouName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("suifangDate")
	public Date getSuifangDate() {
		return suifangDate;
	}

	public void setSuifangDate(Date suifangDate) {
		this.suifangDate = suifangDate;
	}

	@JsonProperty("suifangyishengId")
	public String getSuifangyishengId() {
		return suifangyishengId;
	}

	public void setSuifangyishengId(String suifangyishengId) {
		this.suifangyishengId = suifangyishengId;
	}

	@JsonProperty("dengjijigouId")
	public String getDengjijigouId() {
		return dengjijigouId;
	}

	public void setDengjijigouId(String dengjijigouId) {
		this.dengjijigouId = dengjijigouId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("dengjiDate")
	public Date getDengjiDate() {
		return dengjiDate;
	}

	public void setDengjiDate(Date dengjiDate) {
		this.dengjiDate = dengjiDate;
	}

	@JsonProperty("dengjiId")
	public String getDengjiId() {
		return dengjiId;
	}

	public void setDengjiId(String dengjiId) {
		this.dengjiId = dengjiId;
	}

	@JsonProperty("linchuangfenqi")
	public String getLinchuangfenqi() {
		return linchuangfenqi;
	}

	public void setLinchuangfenqi(String linchuangfenqi) {
		this.linchuangfenqi = linchuangfenqi;
	}

	@JsonProperty("weijingJieguo")
	public String getWeijingJieguo() {
		return weijingJieguo;
	}

	public void setWeijingJieguo(String weijingJieguo) {
		this.weijingJieguo = weijingJieguo;
	}

	@JsonProperty("huojian")
	public String getHuojian() {
		return huojian;
	}

	public void setHuojian(String huojian) {
		this.huojian = huojian;
	}

	@JsonProperty("huojianjieguo")
	public String getHuojianjieguo() {
		return huojianjieguo;
	}

	public void setHuojianjieguo(String huojianjieguo) {
		this.huojianjieguo = huojianjieguo;
	}

	@JsonProperty("zhongliudaxiao")
	public Integer getZhongliudaxiao() {
		return zhongliudaxiao;
	}

	public void setZhongliudaxiao(Integer zhongliudaxiao) {
		this.zhongliudaxiao = zhongliudaxiao;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("zhenduanDate")
	public Date getZhenduanDate() {
		return zhenduanDate;
	}

	public void setZhenduanDate(Date zhenduanDate) {
		this.zhenduanDate = zhenduanDate;
	}


}