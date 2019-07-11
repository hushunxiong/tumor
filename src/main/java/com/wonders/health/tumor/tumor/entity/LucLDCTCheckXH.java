/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.util.Date;
import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 徐汇肺癌LDCT检查结果表实体
 * @author wangyixian
 */
public class LucLDCTCheckXH extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=64)
	@NotNull
	private String ldctOrderId;	// ldct预约id

	@NotNull
	private Date ldctCheckDate;	// ldct检查日期

	@Length(max=1)
	private String ldctResultType;	// ldct检查结果类型字典表 id=600011=阳性；2=阴性

	@Length(max=1)
	private String ldctFollow;	// ldct随访建议字典表 id=60061

	@Length(max=2000)
	private String ldctMiaoshu;	// ldct检查描述

	@Length(max=2000)
	private String ldctResult;	// ldct检查结论

	@Length(max=64)
	private String ldctCheckDoctor;	// 检查医生姓名

	@Length(max=64)
	private String ldctLink;	// ldct影像链接

	@Length(max=4)
	private String sourceId;	

    // ldct预约，一个检查结果对应一个LDCT预约
	private LucAppLdctXh lucAppLdctXh;

	public LucAppLdctXh getLucAppLdctXh() {
		return lucAppLdctXh;
	}

	public void setLucAppLdctXh(LucAppLdctXh lucAppLdctXh) {
		this.lucAppLdctXh = lucAppLdctXh;
	}

	public LucLDCTCheckXH() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("ldctOrderId")
	public String getLdctOrderId() {
		return ldctOrderId;
	}

	public void setLdctOrderId(String ldctOrderId) {
		this.ldctOrderId = ldctOrderId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JsonProperty("ldctCheckDate")
	public Date getLdctCheckDate() {
		return ldctCheckDate;
	}

	public void setLdctCheckDate(Date ldctCheckDate) {
		this.ldctCheckDate = ldctCheckDate;
	}

	@JsonProperty("ldctResultType")
	public String getLdctResultType() {
		return ldctResultType;
	}

	public void setLdctResultType(String ldctResultType) {
		this.ldctResultType = ldctResultType;
	}

	@JsonProperty("ldctFollow")
	public String getLdctFollow() {
		return ldctFollow;
	}

	public void setLdctFollow(String ldctFollow) {
		this.ldctFollow = ldctFollow;
	}

	@JsonProperty("ldctMiaoshu")
	public String getLdctMiaoshu() {
		return ldctMiaoshu;
	}

	public void setLdctMiaoshu(String ldctMiaoshu) {
		this.ldctMiaoshu = ldctMiaoshu;
	}

	@JsonProperty("ldctResult")
	public String getLdctResult() {
		return ldctResult;
	}

	public void setLdctResult(String ldctResult) {
		this.ldctResult = ldctResult;
	}

	@JsonProperty("ldctCheckDoctor")
	public String getLdctCheckDoctor() {
		return ldctCheckDoctor;
	}

	public void setLdctCheckDoctor(String ldctCheckDoctor) {
		this.ldctCheckDoctor = ldctCheckDoctor;
	}

	@JsonProperty("ldctLink")
	public String getLdctLink() {
		return ldctLink;
	}

	public void setLdctLink(String ldctLink) {
		this.ldctLink = ldctLink;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


}