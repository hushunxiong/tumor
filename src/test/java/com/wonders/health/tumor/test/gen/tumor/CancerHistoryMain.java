/**
 * 
 */
package com.wonders.health.tumor.test.gen.tumor;

import com.google.common.collect.Maps;
import com.wonders.health.tumor.gen.model.GenTable;
import com.wonders.health.tumor.gen.model.GenColumn;
import com.wonders.health.tumor.gen.utils.ConvertUtils;
import com.wonders.health.tumor.gen.utils.GenUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
/**
 * 历史
 * @author xuguobing
 */
public class CancerHistoryMain {

	private static String[][] columns = new String[][]{
            {
                    "id",/*列名*/
                    "癌症史id",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "id",/*JAVA字段名:*/
                    "1",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=32)"/*校验器:(多个#号隔开)*/,
                    "32"/*字段长度:*/
            },
            {
                    "manageid",/*列名*/
                    "个人管理编号",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "manageid",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=32)#@NotNull"/*校验器:(多个#号隔开)*/,
                    "32"/*字段长度:*/
            },
            {
                    "check_year",/*列名*/
                    "初筛年度",/*描述*/
                    "NUMBER",/*JDBC类型*/
                    "java.lang.Integer",/*JAVA类型*/
                    "checkYear",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@NotNull"/*校验器:(多个#号隔开)*/,
                    "22"/*字段长度:*/
            },
            {
                    "icd10",/*列名*/
                    "癌症种类ICD10编码",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "icd10",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=12)"/*校验器:(多个#号隔开)*/,
                    "12"/*字段长度:*/
            },
            {
                    "cancer_name",/*列名*/
                    "癌症名称",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "cancerName",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=200)"/*校验器:(多个#号隔开)*/,
                    "200"/*字段长度:*/
            },
            {
                    "age",/*列名*/
                    "年龄",/*描述*/
                    "NUMBER",/*JDBC类型*/
                    "java.lang.Integer",/*JAVA类型*/
                    "age",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "22"/*字段长度:*/
            },
            {
                    "hospital_code",/*列名*/
                    "诊断医疗机构代码",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalCode",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=32)"/*校验器:(多个#号隔开)*/,
                    "32"/*字段长度:*/
            },
            {
                    "hospital_name",/*列名*/
                    "诊断医疗机构名称",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalName",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=100)"/*校验器:(多个#号隔开)*/,
                    "100"/*字段长度:*/
            },
            {
                    "ischange",/*列名*/
                    "更新标志 cdc_dic_personinfo id=60013 code=1：是；code=2：否",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "ischange",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=1)"/*校验器:(多个#号隔开)*/,
                    "1"/*字段长度:*/
            },
            {
                    "del_flag",/*列名*/
                    "删除标记0：正常 1：删除",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "delFlag",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=1)#@NotNull"/*校验器:(多个#号隔开)*/,
                    "1"/*字段长度:*/
            },
            {
                    "create_by",/*列名*/
                    "创建者",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "createBy",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=32)"/*校验器:(多个#号隔开)*/,
                    "32"/*字段长度:*/
            },
            {
                    "create_date",/*列名*/
                    "创建时间",/*描述*/
                    "DATE",/*JDBC类型*/
                    "java.util.Date",/*JAVA类型*/
                    "createDate",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_BW,/*查询方式:*/
                    GenColumn.SHOW_TYPE_DATE,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            },
            {
                    "update_by",/*列名*/
                    "更新者",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "updateBy",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=32)"/*校验器:(多个#号隔开)*/,
                    "32"/*字段长度:*/
            },
            {
                    "update_date",/*列名*/
                    "更新时间",/*描述*/
                    "DATE",/*JDBC类型*/
                    "java.util.Date",/*JAVA类型*/
                    "updateDate",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "0",/*是否编辑:1,是，0,非*/
                    "0",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_BW,/*查询方式:*/
                    GenColumn.SHOW_TYPE_DATE,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            }
    };

    public static void main(String[] args) {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put("packageName", "com.wonders.health.tumor"); //基础包路径
        configs.put("moduleName", "tumor");  //模块名
        configs.put("entityType","grid"); //tree为树机构，grid为表格格式
        configs.put("schema", "ZLZFX");     //库名称
        configs.put("tableName", "CANCER_HISTORY"); //表名称
        configs.put("ClassName", "CancerHistory"); //java实体类名
        configs.put("functionName", "历史"); //表功能
        configs.put("functionAuthor", "xuguobing");//作者

        GenTable table = new GenTable();
        table.setSchema("ZLZFX");
        table.setTable("CANCER_HISTORY");
        GenUtils.parseTableColumn(table, columns);
        configs.put("table", table);
        configs.put("dbName", "oracle");

        //生成实体
        GenUtils.genJavaFile(configs, "entity", "entity");
        //生成DAO对象
        GenUtils.genJavaFile(configs, "dao", "dao");
        //生成DAO的xml
        GenUtils.genMybatisXml(configs);
        //生成搜索VO的xml
        GenUtils.genJavaFile(configs, "search", "vo");
        //生成Service
        GenUtils.genJavaFile(configs, "service", "service");
        //生成Controller
        GenUtils.genJavaFile(configs, "controller", "web");
        //生成List页面
        //GenUtils.genJspFile(configs, "list");
        //生成Form页面
        //GenUtils.genJspFile(configs, "form");
    }

}