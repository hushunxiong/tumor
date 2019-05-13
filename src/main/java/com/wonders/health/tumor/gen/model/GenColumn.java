package com.wonders.health.tumor.gen.model;

import com.wonders.health.tumor.gen.utils.GenUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by xuguobing on 2016/11/1 0001.
 */
public class GenColumn implements Serializable {

    public static final String QUERY_TYPE_EQ = "=";
    public static final String QUERY_TYPE_NE = "!=";
    public static final String QUERY_TYPE_GT = "&gt;";
    public static final String QUERY_TYPE_GE = "&gt;=";
    public static final String QUERY_TYPE_LT = "&lt;";
    public static final String QUERY_TYPE_LE = "&lt;=";
    public static final String QUERY_TYPE_BW = "between";
    public static final String QUERY_TYPE_LK = "like";
    public static final String QUERY_TYPE_LLK = "left_like";
    public static final String QUERY_TYPE_RLK = "right_like";

    public static final String SHOW_TYPE_INPUT = "input";
    public static final String SHOW_TYPE_TEXT = "textarea";
    public static final String SHOW_TYPE_SELECT = "select";
    public static final String SHOW_TYPE_RADIO = "radio";
    public static final String SHOW_TYPE_CHECKBOX = "checkbox";
    public static final String SHOW_TYPE_DATE = "date";
    public static final String SHOW_TYPE_DATE_TIME = "datetime";
    public static final String SHOW_TYPE_USER = "user";
    public static final String SHOW_TYPE_ORGAN = "organ";
    public static final String SHOW_TYPE_BUTTON = "button";
    public static final String SHOW_TYPE_PID = "pid";

    private String name;        // 列名
    private String comments;    // 描述
    private String jdbcType;    // JDBC类型
    private String javaType;    // JAVA类型
    private String javaField;    // JAVA字段名
    private String pk;        // 是否主键（1：主键）
    private String edit;        // 是否编辑字段（1：编辑字段）
    private String list;        // 是否列表字段（1：列表字段）
    private String query;        // 是否查询字段（1：查询字段）
    private String queryType;    // 查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）
    private String showType;    // 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
    private String dictType;    // 字典类型
    private Integer cols; //显示占用几列
    private Integer mdCols;

    private String validators; //校验器列表

    private String[] validatorArr;

    private String length;

//    private String jsValidator;

    private String required;

    private String vtype;

    public String methodGet() {
        if (javaField == null || javaField.trim().length() == 0) {
            return "";
        } else if (javaField.length() > 1 && Character.isUpperCase(javaField.charAt(1))) {
            return "get" + javaField;
        } else {
            return "get" + StringUtils.capitalize(javaField);
        }
    }

    public String methodSet() {
        if (javaField == null || javaField.trim().length() == 0) {
            return "";
        } else if (javaField.length() > 1 && Character.isUpperCase(javaField.charAt(1))) {
            return "set" + javaField;
        } else {
            return "set" + StringUtils.capitalize(javaField);
        }
    }

    /**
     * 是否是基类字段
     *
     * @return
     */
    public Boolean getIsNotBaseField() {
//        return !StringUtils.equals(javaField, "remarks")
//                && !StringUtils.equals(javaField, "createBy")
//                && !StringUtils.equals(javaField, "createDate")
//                && !StringUtils.equals(javaField, "updateBy")
//                && !StringUtils.equals(javaField, "updateDate")
//                && !StringUtils.equals(javaField, "delFlag");
        return !GenUtils.ignores.contains(javaField);
    }

    public GenColumn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getValidators() {
        return validators;
    }

    public void setValidators(String validators) {
        this.validators = validators;
    }

    public String[] getValidatorArr() {
        return validatorArr;
    }

    public void setValidatorArr(String[] validatorArr) {
        this.validatorArr = validatorArr;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
        this.mdCols = 3 * cols - 1;
    }

    public Integer getMdCols() {
        return mdCols;
    }

    public void setMdCols(Integer mdCols) {
        this.mdCols = mdCols;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

}
