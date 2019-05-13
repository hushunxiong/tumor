/**
 * 
 */
package com.wonders.health.tumor.tumor.entity;

import java.lang.String;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.lang.Integer;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.wonders.health.tumor.common.model.BaseEntity;

/**
 * 危险度评估-一级亲属癌症史表实体
 * @author menglianghai
 */
public class FamilyCancerHistory extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Length(max=32)
	private String id;	// 癌症史id

	@Length(max=32)
	@NotNull
	private String checkId;	// 癌症初筛信息登记id

	@Length(max=2)
	private String relation;	// 亲属关系 cdc_dic_personinfo id=60018code=20：子；code=30：女；code=51：父亲；code=52：母亲；code=70：兄弟姐妹

	@Length(max=2)
	private String cancerType;	// 癌症种类 cdc_dic_personinfo id=60020code=1：大肠癌；code=2：肝癌；code=3：胃癌；code=4：肺癌

	private Integer age;	// 发病年龄

	@Length(max=1)
	@NotNull
	private String lived;	// 是否在世 cdc_dic_personinfo id=60019 code=1：在世；code=2：过世

	@Length(max=1)
	private String ischange;	// 更新标志 cdc_dic_personinfo id=60013 code=1：是；code=2：否


	public FamilyCancerHistory() {
		super();
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("checkId")
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	@JsonProperty("relation")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@JsonProperty("cancerType")
	public String getCancerType() {
		return cancerType;
	}

	public void setCancerType(String cancerType) {
		this.cancerType = cancerType;
	}

	@JsonProperty("age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonProperty("lived")
	public String getLived() {
		return lived;
	}

	public void setLived(String lived) {
		this.lived = lived;
	}

	@JsonProperty("ischange")
	public String getIschange() {
		return ischange;
	}

	public void setIschange(String ischange) {
		this.ischange = ischange;
	}


}