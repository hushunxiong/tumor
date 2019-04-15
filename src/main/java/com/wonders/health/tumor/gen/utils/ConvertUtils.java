package com.wonders.health.tumor.gen.utils;

import com.google.common.collect.Maps;

import java.sql.Clob;
import java.util.Date;
import java.util.Map;

/**
 * Created by xuguobing on 2016/11/1 0001.
 */
public class ConvertUtils {

    private static final char SEPARATOR = '_';
    public static Map<String, Class> TYPE = Maps.newHashMap();
    public static Map<String, String> ANN = Maps.newHashMap();
    public static Map<String, String> VDS = Maps.newHashMap();

    static {
        //下面是mysql部分字段转换
        TYPE.put("VARCHAR", String.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("TEXT", String.class);
        TYPE.put("BLOB", byte[].class);
        TYPE.put("INTEGER", Long.class);
        TYPE.put("INT", Integer.class);
        TYPE.put("TINYINT", Integer.class);
        TYPE.put("SMALLINT", Integer.class);
        TYPE.put("MEDIUMINT", Integer.class);
        TYPE.put("BIT", Boolean.class);
        TYPE.put("BIGINT", Integer.class);
        TYPE.put("FLOAT", Float.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", Float.class);
        TYPE.put("BOOLEAN", Integer.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Date.class);
        TYPE.put("DATETIME", Date.class);
        TYPE.put("TIMESTAMP", Date.class);
        TYPE.put("YEAR", Date.class);
        //oracle部分对mysql字段的补充
        TYPE.put("VARCHAR2", String.class);
        TYPE.put("NVARCHAR2", String.class);
        TYPE.put("LONG", String.class);
        TYPE.put("NUMBER", Integer.class);
        TYPE.put("RAW", byte[].class);
        TYPE.put("LONGRAW", byte[].class);
        TYPE.put("CLOB", Clob.class);
        TYPE.put("TIMESTAMP(6)", Date.class);


        ANN.put("Null", "javax.validation.constraints.Null");
        ANN.put("NotNull", "javax.validation.constraints.NotNull");
        VDS.put("NotNull", "required");
        ANN.put("AssertTrue", "javax.validation.constraints.AssertTrue");
        ANN.put("AssertFalse", "javax.validation.constraints.AssertFalse");
        ANN.put("Min", "javax.validation.constraints.Min");
        ANN.put("Max", "javax.validation.constraints.Max");
        ANN.put("DecimalMax", "javax.validation.constraints.DecimalMax");
        ANN.put("DecimalMin", "javax.validation.constraints.DecimalMin");
        ANN.put("Size", "javax.validation.constraints.Size");
        ANN.put("Digits", "javax.validation.constraints.Digits");
        ANN.put("Past", "javax.validation.constraints.Past");
        ANN.put("Future", "javax.validation.constraints.Future");
        ANN.put("Pattern", "javax.validation.constraints.Pattern");
        ANN.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
        ANN.put("Email", "org.hibernate.validator.constraints.Email");
        ANN.put("Length", "org.hibernate.validator.constraints.Length");
        VDS.put("Length", "maxLength");
        ANN.put("NotEmpty", "org.hibernate.validator.constraints.NotEmpty");
        VDS.put("NotEmpty", "required");
        ANN.put("Range", "org.hibernate.validator.constraints.Range");
        ANN.put("MemberOf", "com.wonders.common.code.gen.validator.MemberOf");
    }

    public static String getTypeSimpleName(String key) {
        if (key != null) return null;
        Class clazz = TYPE.get(key.toUpperCase());
        if (clazz != null) {
            return clazz.getSimpleName();
        }
        return null;
    }

    public static Boolean isDate(String key){
        return "DATE".equals(key);
    }

    public static Boolean isDateTime(String key){
        if (key == null) return false;
        if (key.indexOf('(') >0){
            String tmp = key.substring(0, key.indexOf('('));
            return isDateTime(tmp);
        } else {
            return "DATETIME".equals(key)
                    || "TIMESTAMP".equals(key)
                    || "TIME".equals(key);
        }
    }

    public static String getTypeName(String key) {
        if (key == null) return null;
        key = key.toUpperCase();
        Class clazz = TYPE.get(key);
        if (clazz != null) {
            return clazz.getName();
        } else if (key.indexOf('(') > 0) {
            String tmp = key.substring(0, key.indexOf('('));
            return getTypeName(tmp);
        }
        return null;
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toCamelCase("_word"));
        System.out.println(getTypeName("TIMESTAMP(5)"));
    }
}
