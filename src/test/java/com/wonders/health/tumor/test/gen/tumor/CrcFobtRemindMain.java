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
 * 大肠癌便隐血检查提醒表
 * @author menglianghai
 */
public class CrcFobtRemindMain {

	private static String[][] columns = new String[][]{
            {
                    "id",/*列名*/
                    "主键",/*描述*/
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
                    "crc_check_id",/*列名*/
                    "大肠癌初筛信息登记id",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "crcCheckId",/*JAVA字段名:*/
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
                    "first_fobt_remind_date",/*列名*/
                    "第一次便隐血提醒日期",/*描述*/
                    "DATE",/*JDBC类型*/
                    "java.util.Date",/*JAVA类型*/
                    "firstFobtRemindDate",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_BW,/*查询方式:*/
                    GenColumn.SHOW_TYPE_DATE,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            },
            {
                    "first_fobt_remind_type",/*列名*/
                    "第一次便隐血提醒方式 字典表 id=60047",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "firstFobtRemindType",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=2)"/*校验器:(多个#号隔开)*/,
                    "2"/*字段长度:*/
            },
            {
                    "second_fobt_remind_date",/*列名*/
                    "第二次便隐血提醒日期",/*描述*/
                    "DATE",/*JDBC类型*/
                    "java.util.Date",/*JAVA类型*/
                    "secondFobtRemindDate",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_BW,/*查询方式:*/
                    GenColumn.SHOW_TYPE_DATE,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            },
            {
                    "second_fobt_remind_type",/*列名*/
                    "第二次便隐血提醒方式 字典表 id=60047",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "secondFobtRemindType",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=2)"/*校验器:(多个#号隔开)*/,
                    "2"/*字段长度:*/
            },
            {
                    "per_fobt_remind_date",/*列名*/
                    "计划提醒时间",/*描述*/
                    "DATE",/*JDBC类型*/
                    "java.util.Date",/*JAVA类型*/
                    "perFobtRemindDate",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_BW,/*查询方式:*/
                    GenColumn.SHOW_TYPE_DATE,/*显示方式:*/
                    "",/*字典类型:*/
                    ""/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            },
            {
                    "fobt_remind_status",/*列名*/
                    "提醒状态 字典表 id=60049",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "fobtRemindStatus",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=2)"/*校验器:(多个#号隔开)*/,
                    "2"/*字段长度:*/
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
                    "@Length(max=32)#@NotNull"/*校验器:(多个#号隔开)*/,
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
                    "@NotNull"/*校验器:(多个#号隔开)*/,
                    "7"/*字段长度:*/
            }
    };

    public static void main(String[] args) {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put("packageName", "com.wonders.health.tumor"); //基础包路径
        configs.put("moduleName", "tumor");  //模块名
        configs.put("entityType","grid"); //tree为树机构，grid为表格格式
        configs.put("schema", "ZLZFX");     //库名称
        configs.put("tableName", "crc_fobt_remind"); //表名称
        configs.put("ClassName", "CrcFobtRemind"); //java实体类名
        configs.put("functionName", "大肠癌便隐血检查提醒表"); //表功能
        configs.put("functionAuthor", "menglianghai");//作者

        GenTable table = new GenTable();
        table.setSchema("ZLZFX");
        table.setTable("crc_fobt_remind");
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