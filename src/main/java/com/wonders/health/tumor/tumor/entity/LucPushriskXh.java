/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 徐汇肺癌危险因素推送表实体
 * @author sunyang
 */
public class LucPushriskXh extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 主键

	@Length(max=32)
	private String personcard;	// 身份证

	@Length(max=32)
	@NotNull
	private String riskAccount;	// 危险因素科目代码

	@Length(max=50)
	private String riskComment1;	// 其它类备注1

	@Length(max=50)
	private String riskComment2;	// 其它类备注2

	@Length(max=4)
	private String sourceId;	// 来源代码

	private String riskFlag;


	public LucPushriskXh() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("personcard")
	public String getPersoncard() {
		return personcard;
	}

	public void setPersoncard(String personcard) {
		this.personcard = personcard;
	}

	@JsonProperty("riskAccount")
	public String getRiskAccount() {
		return riskAccount;
	}

	public void setRiskAccount(String riskAccount) {
		this.riskAccount = riskAccount;
	}

	@JsonProperty("riskComment1")
	public String getRiskComment1() {
		return riskComment1;
	}

	public void setRiskComment1(String riskComment1) {
		this.riskComment1 = riskComment1;
	}

	@JsonProperty("riskComment2")
	public String getRiskComment2() {
		return riskComment2;
	}

	public void setRiskComment2(String riskComment2) {
		this.riskComment2 = riskComment2;
	}

	@JsonProperty("sourceId")
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}
}