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
 * 医疗机构表
 * @author xuguobing
 */
public class CancerDicHospitalInfoMain {

	private static String[][] columns = new String[][]{
            {
                    "hospital_id",/*列名*/
                    "医疗机构代码",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalId",/*JAVA字段名:*/
                    "1",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=11)#@NotNull"/*校验器:(多个#号隔开)*/,
                    "11"/*字段长度:*/
            },
            {
                    "hospital_name",/*列名*/
                    "医疗机构名称",/*描述*/
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
                    "@Length(max=64)"/*校验器:(多个#号隔开)*/,
                    "64"/*字段长度:*/
            },
            {
                    "hospital_type",/*列名*/
                    "原：医院类型 01:综合医院 02：专科医院 03：卫生服务中心;新：医院类型 H1-专科疾病防治院、A1-综合医院、A2-中医医院、A3-中西医结合医院、A5-专科医院、A8-部队医院、B1-社区卫生服务中心",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalType",/*JAVA字段名:*/
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
                    "hospital_level",/*列名*/
                    "医院等级类型：1-社区卫生服务中心、2-二级、3-三级、9-未评",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalLevel",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=30)"/*校验器:(多个#号隔开)*/,
                    "30"/*字段长度:*/
            },
            {
                    "address_county",/*列名*/
                    "地址-县（区）",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "addressCounty",/*JAVA字段名:*/
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
                    "del_flag",/*列名*/
                    "",/*描述*/
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
                    "@Length(max=1)"/*校验器:(多个#号隔开)*/,
                    "1"/*字段长度:*/
            },
            {
                    "source_id",/*列名*/
                    "",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "sourceId",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=4)"/*校验器:(多个#号隔开)*/,
                    "4"/*字段长度:*/
            },
            {
                    "hospital_id_22",/*列名*/
                    "医疗机构代码-22位与医疗机构代码一一对应",/*描述*/
                    "VARCHAR2",/*JDBC类型*/
                    "java.lang.String",/*JAVA类型*/
                    "hospitalId22",/*JAVA字段名:*/
                    "0",/*是否主键:1,是，0,非*/
                    "1",/*是否编辑:1,是，0,非*/
                    "1",/*是否列表:1,是，0,非*/
                    "0",/*是否查询:1,是，0,非*/
                    GenColumn.QUERY_TYPE_EQ,/*查询方式:*/
                    GenColumn.SHOW_TYPE_INPUT,/*显示方式:*/
                    "",/*字典类型:*/
                    "@Length(max=22)"/*校验器:(多个#号隔开)*/,
                    "22"/*字段长度:*/
            }
    };

    public static void main(String[] args) {
        Map<String, Object> configs = Maps.newHashMap();
        configs.put("packageName", "com.wonders.health.tumor"); //基础包路径
        configs.put("moduleName", "tumor");  //模块名
        configs.put("entityType","grid"); //tree为树机构，grid为表格格式
        configs.put("schema", "ZLZFX");     //库名称
        configs.put("tableName", "CANCER_DIC_HOSPITAL_INFO"); //表名称
        configs.put("ClassName", "CancerDicHospitalInfo"); //java实体类名
        configs.put("functionName", "医疗机构表"); //表功能
        configs.put("functionAuthor", "xuguobing");//作者

        GenTable table = new GenTable();
        table.setSchema("ZLZFX");
        table.setTable("CANCER_DIC_HOSPITAL_INFO");
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