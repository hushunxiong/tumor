/**
 * 
 */
package com.wonders.health.tumor.follow.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 大肠癌随访实体
 * @author sunyang
 */
public class CrcFollow extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=30)
	@NotNull
	private String primaryMark;	// 随访编号（医疗机构代码+自定义id）

	@Length(max=32)
	@NotNull
	private String crcCheckId;	// 大肠癌初筛信息登记id

	@Length(max=1)
	private String shifouquezhen;	// 是否确诊大肠癌

	@Length(max=2)
	private String yingxiangxueFangshi;	// 影像学检查方式

	@Length(max=1)
	private String changjing;	// 是否肠镜

	@Length(max=2)
	private String buzuochangjingYuanyin;	// 不进行肠镜的原因

	@Length(max=500)
	private String buzuochangjingYuanyinQita;	// 不进行肠镜的原因-其他

	@Length(max=1)
	private String wutongchangjing;	// 是否无痛肠镜

	@Length(max=2)
	private String changjingbuwei;	// 肠镜检查到达部位

	@Length(max=2)
	private String bingbianbuw;	// 病变部位

	@Length(max=2)
	private String bingbianshu;	// 发现病变数

	
	private Integer gangjuli;	// 最大病变距肛门距离

	
	private Integer bingbiandaxiao;	// 病变大小

	@Length(max=1)
	private String huojian;	// 是否活检

	@Length(max=2)
	private String huojianjieguo;	// 活检结果

	@Length(max=2)
	private String liubian;	// 是否瘤变

	@Length(max=2)
	private String xirouxingzhuang;	// 息肉形状

	@Length(max=1)
	private String youwudi;	// 有无蒂

	@Length(max=2)
	private String chuxueqingkuang;	// 出血情况

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date huojianriq;	// 活检报告日期

	@Length(max=1)
	private String bingli;	// 是否病理

	@Length(max=1000)
	private String binglileixing;	// 病理类型

	@Length(max=1)
	private String shifouzhiliao;	// 是否治疗

	@Length(max=2)
	private String bingbianchuli;	// 病变处理

	@Length(max=2)
	private String shoushufangshi;	// 手术方式

	@Length(max=2)
	private String fuzhuzhiliao;	// 辅助治疗方式

	@Length(max=2)
	private String zhongliuleix;	// 恶性肿瘤类型

	@Length(max=2)
	private String fenhuachengdu;	// 分化程度

	@Length(max=2)
	private String fenqiyiju;	// 分期依据

	@Length(max=2)
	private String zhongliutnmT;	// 肿瘤TNM分期—T

	@Length(max=2)
	private String zhongliutnmN;	// 肿瘤TNM分期—N

	@Length(max=2)
	private String zhongliutnmM;	// 肿瘤TNM分期—M

	@Length(max=2)
	private String linchuangfenqi;	// 大肠癌临床分期

	@Length(max=2)
	private String linchuangfenqiPutuo;	// 大肠癌临床分期(普陀)

	@Length(max=2)
	private String tnmfenqilaiyuan;	// TNM分期来源

	@Length(max=30)
	private String zhuyuanhao;	// 住院号

	@Length(max=25)
	private String shoushujigouId;	// 手术医疗机构编码

	@Length(max=256)
	private String shoushujigouName;	// 手术医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date shoushuDate;	// 手术日期

	@Length(max=25)
	private String fangliaojigouId;	// 放疗医疗机构编码

	@Length(max=256)
	private String fangliaojigouName;	// 放疗医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date fangliaoDate;	// 放疗日期

	@Length(max=25)
	private String hualiaojigouId;	// 化疗医疗机构编码

	@Length(max=256)
	private String hualiaojigouName;	// 化疗医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date hualiaoDate;	// 化疗日期

	@Length(max=25)
	private String changjingjigouId;	// 肠镜医疗机构编码

	@Length(max=256)
	private String changjingjigouName;	// 肠镜医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date changjingDate;	// 肠镜日期

	@Length(max=64)
	private String changjingYisheng;	// 肠镜医生姓名

	@Length(max=25)
	private String suifangjigouId;	// 随访医疗机构编码

	@Length(max=256)
	private String suifangjigouName;	// 随访医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date suifangDate;	// 随访日期

	@Length(max=32)
	private String suifangyishengId;	// 随访医生ID

	@Length(max=25)
	private String zhenduanjigouId;	// 诊断医疗机构编码

	@Length(max=256)
	private String zhenduanjigouName;	// 诊断医疗机构名称

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date zhenduanDate;	// 诊断日期

	@Length(max=25)
	private String dengjijigouId;	// 登记医疗机构编码

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dengjiDate;	// 登记日期

	@Length(max=32)
	private String dengjiId;	// 登记人ID


	public CrcFollow() {
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

	@JsonProperty("crcCheckId")
	public String getCrcCheckId() {
		return crcCheckId;
	}

	public void setCrcCheckId(String crcCheckId) {
		this.crcCheckId = crcCheckId;
	}

	@JsonProperty("shifouquezhen")
	public String getShifouquezhen() {
		return shifouquezhen;
	}

	public void setShifouquezhen(String shifouquezhen) {
		this.shifouquezhen = shifouquezhen;
	}

	@JsonProperty("yingxiangxueFangshi")
	public String getYingxiangxueFangshi() {
		return yingxiangxueFangshi;
	}

	public void setYingxiangxueFangshi(String yingxiangxueFangshi) {
		this.yingxiangxueFangshi = yingxiangxueFangshi;
	}

	@JsonProperty("changjing")
	public String getChangjing() {
		return changjing;
	}

	public void setChangjing(String changjing) {
		this.changjing = changjing;
	}

	@JsonProperty("buzuochangjingYuanyin")
	public String getBuzuochangjingYuanyin() {
		return buzuochangjingYuanyin;
	}

	public void setBuzuochangjingYuanyin(String buzuochangjingYuanyin) {
		this.buzuochangjingYuanyin = buzuochangjingYuanyin;
	}

	@JsonProperty("buzuochangjingYuanyinQita")
	public String getBuzuochangjingYuanyinQita() {
		return buzuochangjingYuanyinQita;
	}

	public void setBuzuochangjingYuanyinQita(String buzuochangjingYuanyinQita) {
		this.buzuochangjingYuanyinQita = buzuochangjingYuanyinQita;
	}

	@JsonProperty("wutongchangjing")
	public String getWutongchangjing() {
		return wutongchangjing;
	}

	public void setWutongchangjing(String wutongchangjing) {
		this.wutongchangjing = wutongchangjing;
	}

	@JsonProperty("changjingbuwei")
	public String getChangjingbuwei() {
		return changjingbuwei;
	}

	public void setChangjingbuwei(String changjingbuwei) {
		this.changjingbuwei = changjingbuwei;
	}

	@JsonProperty("bingbianbuw")
	public String getBingbianbuw() {
		return bingbianbuw;
	}

	public void setBingbianbuw(String bingbianbuw) {
		this.bingbianbuw = bingbianbuw;
	}

	@JsonProperty("bingbianshu")
	public String getBingbianshu() {
		return bingbianshu;
	}

	public void setBingbianshu(String bingbianshu) {
		this.bingbianshu = bingbianshu;
	}

	@JsonProperty("gangjuli")
	public Integer getGangjuli() {
		return gangjuli;
	}

	public void setGangjuli(Integer gangjuli) {
		this.gangjuli = gangjuli;
	}

	@JsonProperty("bingbiandaxiao")
	public Integer getBingbiandaxiao() {
		return bingbiandaxiao;
	}

	public void setBingbiandaxiao(Integer bingbiandaxiao) {
		this.bingbiandaxiao = bingbiandaxiao;
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

	@JsonProperty("liubian")
	public String getLiubian() {
		return liubian;
	}

	public void setLiubian(String liubian) {
		this.liubian = liubian;
	}

	@JsonProperty("xirouxingzhuang")
	public String getXirouxingzhuang() {
		return xirouxingzhuang;
	}

	public void setXirouxingzhuang(String xirouxingzhuang) {
		this.xirouxingzhuang = xirouxingzhuang;
	}

	@JsonProperty("youwudi")
	public String getYouwudi() {
		return youwudi;
	}

	public void setYouwudi(String youwudi) {
		this.youwudi = youwudi;
	}

	@JsonProperty("chuxueqingkuang")
	public String getChuxueqingkuang() {
		return chuxueqingkuang;
	}

	public void setChuxueqingkuang(String chuxueqingkuang) {
		this.chuxueqingkuang = chuxueqingkuang;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("huojianriq")
	public Date getHuojianriq() {
		return huojianriq;
	}

	public void setHuojianriq(Date huojianriq) {
		this.huojianriq = huojianriq;
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

	@JsonProperty("shifouzhiliao")
	public String getShifouzhiliao() {
		return shifouzhiliao;
	}

	public void setShifouzhiliao(String shifouzhiliao) {
		this.shifouzhiliao = shifouzhiliao;
	}

	@JsonProperty("bingbianchuli")
	public String getBingbianchuli() {
		return bingbianchuli;
	}

	public void setBingbianchuli(String bingbianchuli) {
		this.bingbianchuli = bingbianchuli;
	}

	@JsonProperty("shoushufangshi")
	public String getShoushufangshi() {
		return shoushufangshi;
	}

	public void setShoushufangshi(String shoushufangshi) {
		this.shoushufangshi = shoushufangshi;
	}

	@JsonProperty("fuzhuzhiliao")
	public String getFuzhuzhiliao() {
		return fuzhuzhiliao;
	}

	public void setFuzhuzhiliao(String fuzhuzhiliao) {
		this.fuzhuzhiliao = fuzhuzhiliao;
	}

	@JsonProperty("zhongliuleix")
	public String getZhongliuleix() {
		return zhongliuleix;
	}

	public void setZhongliuleix(String zhongliuleix) {
		this.zhongliuleix = zhongliuleix;
	}

	@JsonProperty("fenhuachengdu")
	public String getFenhuachengdu() {
		return fenhuachengdu;
	}

	public void setFenhuachengdu(String fenhuachengdu) {
		this.fenhuachengdu = fenhuachengdu;
	}

	@JsonProperty("fenqiyiju")
	public String getFenqiyiju() {
		return fenqiyiju;
	}

	public void setFenqiyiju(String fenqiyiju) {
		this.fenqiyiju = fenqiyiju;
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

	@JsonProperty("linchuangfenqi")
	public String getLinchuangfenqi() {
		return linchuangfenqi;
	}

	public void setLinchuangfenqi(String linchuangfenqi) {
		this.linchuangfenqi = linchuangfenqi;
	}

	@JsonProperty("linchuangfenqiPutuo")
	public String getLinchuangfenqiPutuo() {
		return linchuangfenqiPutuo;
	}

	public void setLinchuangfenqiPutuo(String linchuangfenqiPutuo) {
		this.linchuangfenqiPutuo = linchuangfenqiPutuo;
	}

	@JsonProperty("tnmfenqilaiyuan")
	public String getTnmfenqilaiyuan() {
		return tnmfenqilaiyuan;
	}

	public void setTnmfenqilaiyuan(String tnmfenqilaiyuan) {
		this.tnmfenqilaiyuan = tnmfenqilaiyuan;
	}

	@JsonProperty("zhuyuanhao")
	public String getZhuyuanhao() {
		return zhuyuanhao;
	}

	public void setZhuyuanhao(String zhuyuanhao) {
		this.zhuyuanhao = zhuyuanhao;
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

	@JsonProperty("changjingjigouId")
	public String getChangjingjigouId() {
		return changjingjigouId;
	}

	public void setChangjingjigouId(String changjingjigouId) {
		this.changjingjigouId = changjingjigouId;
	}

	@JsonProperty("changjingjigouName")
	public String getChangjingjigouName() {
		return changjingjigouName;
	}

	public void setChangjingjigouName(String changjingjigouName) {
		this.changjingjigouName = changjingjigouName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("changjingDate")
	public Date getChangjingDate() {
		return changjingDate;
	}

	public void setChangjingDate(Date changjingDate) {
		this.changjingDate = changjingDate;
	}

	@JsonProperty("changjingYisheng")
	public String getChangjingYisheng() {
		return changjingYisheng;
	}

	public void setChangjingYisheng(String changjingYisheng) {
		this.changjingYisheng = changjingYisheng;
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("zhenduanDate")
	public Date getZhenduanDate() {
		return zhenduanDate;
	}

	public void setZhenduanDate(Date zhenduanDate) {
		this.zhenduanDate = zhenduanDate;
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


}