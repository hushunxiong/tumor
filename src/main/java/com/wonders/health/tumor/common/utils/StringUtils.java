package com.wonders.health.tumor.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final String STR_NA = "N/A";

    public static final String STR_ZERONUM = "0";

    public static final String STR_BLANK = "";

    public static final String STR_SPACE = " ";

    public static final String STR_ASTERISK = "*";

    public static final String STR_PERCENTAGE = "%";

    public static final String DATE_FORMAT = "yy-MM-dd";

    public static final String STR_CR = "\n";

    public static String strSub(String value, int max) {
        int n = 0;
        String chinese = "[\u4e00-\u9fa5]";
        StringBuffer buffer = new StringBuffer(2000);
        for (int i = 0; i < value.length(); i++) {
            if (n < max - 1) {
                String temp = value.substring(i, i + 1);
                if (temp.matches(chinese)) {
                    n += 2;
                } else {
                    n += 1;
                }
                buffer.append(temp);
            } else {
                break;
            }
        }
        return buffer.toString();
    }

    public static String safeString(String arg) {
        String returnValue = STR_BLANK;
        if (!isBlank(arg)) {
            returnValue = arg;
        }
        return returnValue;
    }

    public static BigDecimal strFloatToDecimal(String floatStr) {
        if (isBlank(floatStr)) {
            return new BigDecimal(0);
        } else {
            try {
                return new BigDecimal(Float.parseFloat(floatStr));
            } catch (NumberFormatException e) {
                return new BigDecimal(0);
            }
        }
    }

    public static BigDecimal strIntToDecimal(String intStr) {
        if (isBlank(intStr)) {
            return new BigDecimal(0);
        } else {
            try {
                return new BigDecimal(Integer.parseInt(intStr));
            } catch (NumberFormatException e) {
                return new BigDecimal(0);
            }
        }
    }

    /**
     * 判断传入的字符串是否有效(当字符串为空或为null时无效)
     *
     * @param str
     * @return 有效true无效false
     */
    public static boolean isValid(String str) {
        return !isBlank(str);
    }

    /**
     * String content is "" ?
     */
    public static boolean isBlank(String string) {
        return (string == null || string.trim().length() == 0);
    }

    /**
     * String[] is null or length==0 ?
     */
    public static boolean isBlank(String[] string) {
        return (string == null || string.length == 0);
    }

    /**
     * int[] is null or length==0 ?
     */
    public static boolean isBlank(int[] string) {
        return (string == null || string.length == 0);
    }

    /**
     * List is null or size==0 ?
     */
    public static boolean isBlank(List<? extends Object> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * String content is number ?
     */
    public static boolean isNum(String string) {
        if (!hasValue(string))
            return false;

        String regEx1 = "[\\-|\\+]?\\d+";
        String regEx2 = "[\\-|\\+]?\\d*[\\.]?\\d+";
        Pattern p;
        Matcher m1, m2;
        p = Pattern.compile(regEx1);
        m1 = p.matcher(string);
        p = Pattern.compile(regEx2);
        m2 = p.matcher(string);
        if (m1.matches() || m2.matches())
            return true;
        else
            return false;
    }

    /**
     * String content is int ?
     */
    public static boolean isInt(String string) {
        if (string == null)
            return false;

        String regEx1 = "[\\-|\\+]?\\d+";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    /**
     * String content is pure digital ?
     */
    public static boolean isPureDigital(String string) {
        if (isBlank(string))
            return false;
        String regEx1 = "\\d+";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    /**
     * String content is email address ?
     */
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    /**
     * Replace all "from" in "source" to "to"
     */
    public static String strReplace(String from, String to, String source) {
        StringBuffer bf = new StringBuffer("");
        StringTokenizer st = new StringTokenizer(source, from, true);
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            // System.out.println("*" + tmp);
            if (tmp.equals(from)) {
                bf.append(to);
            } else {
                bf.append(tmp);
            }
        }
        return bf.toString();
    }

    public static String replace(String oldstr, String newstr, String src) {
        StringBuffer dest = new StringBuffer();
        int beginIndex = 0;
        int endIndex = 0;
        while (true) {
            endIndex = src.indexOf(oldstr, beginIndex);
            if (endIndex >= 0) {
                dest.append(src.substring(beginIndex, endIndex));
                dest.append(newstr);
                beginIndex = endIndex + oldstr.length();
            } else {
                dest.append(src.substring(beginIndex));
                break;
            }
        }
        return dest.toString();
    }

    /**
     * is null? is ""? is excpet String?
     */
    public static boolean hasValue(String strIn, String strExcept) {
        if (strIn == null) {
            return false;
        }
        if (strIn.equals("")) {
            return false;
        }
        if (strIn.equals(strExcept)) {
            return false;
        }
        return true;
    }

    public static boolean hasValue(String strIn) {
        if (strIn == null) {
            return false;
        }
        if (strIn.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isDateString(String datevalue) {
        return isDateString(datevalue, DATE_FORMAT);
    }

    public static boolean isDateString(String datevalue, String dateFormat) {
        if (!hasValue(datevalue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            Date dd = fmt.parse(datevalue);
            if (datevalue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String AppendZeroPrefix(int length, String target) {
        return AppendPrefix(length, target, "0");
    }

    public static String AppendPrefix(int length, String target, String append) {
        StringBuffer sb = new StringBuffer();
        int len = target.length();
        for (int i = 0; i < length - len; i++) {
            sb.append(append);
        }
        sb.append(target);
        return sb.toString();
    }

    /**
     * 方法名:processUrl <br>
     * 方法说明:去掉action中的'/'和'.do' <br>
     *
     * @param request
     * @return String
     */
    public static String processUrl(String action) {
        if (isBlank(action)) {
            return action;
        }

        int spos = action.lastIndexOf("/");
        if (spos >= 0) {
            action = action.substring(spos + 1);
        }
        int epos = action.indexOf(".do");
        if (epos >= 0) {
            action = action.substring(0, epos);
        }
        return action;
    }

    /**
     * 将从画面获得的树组转换成全部连接起来的字符串
     *
     * @param temp 从画面获得的树组
     * @return 所需的字符串
     */
    public static String viewArrayToStr(String[] temp) {
        String needString = "";
        for (int i = 0; i < temp.length; i++) {
            needString = needString + temp[i];
        }
        return needString;
    }

    /**
     * 将从数据库中读出的字符串转换成画面所需的字符串数组
     *
     * @param dbStr     从数据库读出的字符串
     * @param compareTo 被比较的字符串
     * @param index     数组下标
     * @param needArray 所需数组
     * @return 数组
     */
    public static String[] dbStrToArray(String compareTo, String dbStr,
                                        int index, String[] needArray) {
        int i = dbStr.indexOf(compareTo);
        // 如果dbStr里含有compareTo
        if (i > -1) {
            needArray[index] = compareTo;
        }
        return needArray;
    }

    /**
     * 将数组转换成以逗号分隔的字符串
     *
     * @param needChange 需要转换的数组
     * @return 以逗号分割的字符串
     */
    public static String arrayToStrWithComma(String[] needChange) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < needChange.length; i++) {
            sb.append(needChange[i]);
            if ((i + 1) != needChange.length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 将从数据库读出的数组转换成以画面树组，只要dbArray含有画面所需的value（其中value必须是数字），则向needArray中插入。专为复选框提供。
     *
     * @param dbArray         从数据库读出的数组
     * @param needArrayLength 所需树组的长度，一般对应有几个复选项目
     * @return 所需要的树组
     */
    public static String[] dbArrayToNeedArray(String[] dbArray,
                                              int needArrayLength) {
        String[] needArray = new String[needArrayLength];
        for (int i = 0; i < dbArray.length; i++) {
            String temp = dbArray[i];
            for (int j = needArrayLength; j > 0; j--) {
                if (temp.indexOf(Integer.toString(j)) > -1) {
                    needArray[j - 1] = Integer.toString(j);
                }
            }
        }
        return needArray;
    }

    /**
     * 转换为null的字符串
     *
     * @param needChangeStr 需要转换的字符串
     * @return String
     */
    public static String strNotNull(String needChangeStr) {
        if (StringUtils.isBlank(needChangeStr)) {
            return "";
        } else {
            return needChangeStr;
        }
    }

    /**
     * 按号码产生字符串(如: getCurNo(11,8,"0") 将返回"00000011")
     *
     * @param curno   号码
     * @param length  总长度
     * @param fillStr 填充串
     * @return
     */
    public static String getCurNo(int curno, int length, String fillStr) {
        int temp = curno;
        StringBuffer sb = new StringBuffer(length);
        int count = 0;
        while (curno / 10 != 0) {
            curno = curno / 10;
            count++;
        }
        int size = length - count - 1;
        for (int i = 0; i < size; i++) {
            sb.append(fillStr);
        }
        sb.append(temp);
        return sb.toString();
    }

    /**
     * 按分隔符号读出字符串的内容
     *
     * @param strlist 含有分隔符号的字符串
     * @param ken     分隔符号
     * @return 列表
     */
    public static final List<? extends Object> parseStringToArrayList(
            String strlist, String ken) {
        StringTokenizer st = new StringTokenizer(strlist, ken);

        if (strlist == null || strlist.equals("") || st.countTokens() <= 0) {
            return new ArrayList<Object>();
        }

        int size = st.countTokens();
        List<String> strv = new ArrayList<String>();

        for (int i = 0; i < size; i++) {
            String nextstr = st.nextToken();
            if (!nextstr.equals("")) {
                strv.add(nextstr);
            }
        }
        return strv;
    }

    /**
     * 将字符串按照规则进行分段，如将"2001-10-12"按照"-"划分，则分为"2001"、"10"和"12"三段
     *
     * @param szSource 进行分段的字符串
     * @param token    分隔符号
     * @return 分段后的字符串数组
     */
    public static final String[] SplitString(String szSource, String token) {
        if (szSource == null || token == null)
            return null;
        StringTokenizer st1 = new StringTokenizer(szSource, token);
        String d1[] = new String[st1.countTokens()];
        for (int x = 0; x < d1.length; x++)
            if (st1.hasMoreTokens())
                d1[x] = st1.nextToken().trim();
        return d1;
    }

    public static boolean isNumber(String str) {
        if (isValid(str)) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException ne) {

            }
        }
        return false;
    }

    /**
     * 数组转String. 比如:<br>
     * <p>
     * <pre>
     * String str = ArrayToString(new String[] { &quot;a&quot;, &quot;b&quot; }, &quot;_&quot;, &quot;,&quot;);
     * System.out.println(str); // &quot;_a, _b&quot;
     * </pre>
     *
     * @param objs   数组
     * @param prefix 转换结果中每一项的前缀
     * @param delim  转换结果中各项的分隔符
     * @return
     */
    public static String ArrayToString(Object[] objs, String prefix,
                                       String delim) {
        StringBuffer sb = new StringBuffer();

        int len = objs == null ? 0 : objs.length;

        if (len > 0) {
            sb.append(prefix).append(objs[0]);

            for (int i = 1; i < len; i++) {
                sb.append(delim).append(prefix).append(objs[i]);
            }

        }

        return sb.toString();
    }

    /**
     * Object转String.
     *
     * @param obj Object
     * @return
     */
    public static String ObjectToString(Object obj) {
        if (null == obj) return "";
        return obj.toString();
    }

    /**
     * 将数字型货币转换为中文型货币<br>
     * <p>
     * <pre>
     *   String currency=&quot;1111111.111&quot;
     *   String str = StringUtils.getCurrency2CN(currency)
     *   System.out.println(str); // &quot;壹佰一十一万壹千壹佰一十一元一角一分&quot;
     * </pre>
     */
    final static String CARRY_SHI = "拾";

    final static String CARRY_BAI = "佰";

    final static String CARRY_QIAN = "仟";

    final static String CARRY_WAN = "万";

    final static String CARRY_YI = "亿";

    final static String CUR_YUAN = "元";

    final static String CUR_FEN = "分";

    final static String CUR_JIAO = "角";

    final static String[] digits = {"零", "壹", "貳", "叁", "肆", "伍", "陆", "柒",
            "捌", "玖",};

    public static String getValueOfCurrency2CN(String priValue) {
        StringBuffer sb = new StringBuffer();
        if (priValue.startsWith("-") || priValue.startsWith("+")) {
            if (priValue.startsWith("-"))
                sb.append("负");
            priValue = priValue.substring(1);
        }
        String value = String.valueOf(priValue);
        int dot_pos = String.valueOf(value).indexOf('.');
        String int_value;
        String fraction_value;
        if (dot_pos == -1) {
            int_value = value;
            fraction_value = "00";
        } else {
            int_value = value.substring(0, dot_pos);
            fraction_value = value.substring(dot_pos + 1, priValue.length())
                    + "00".substring(0, 2);
        }

        StringBuffer cn_currency = new StringBuffer();

        int len = int_value.length();
        boolean zero_flag = false;

        // if (len == 16)
        // int_value.substring(0, 15);
        if (len > 12 && len <= 16) {
            String temp = int_value.substring(0, len - 12);
            if (Integer.parseInt(temp) != 0) {
                cn_currency.append(
                        cell2CH(int_value.substring(0, len - 12), zero_flag))
                        .append(CARRY_WAN);
            }
            int_value = int_value.substring(len - 12, len);
            len = 12;
            zero_flag = true;
        }
        if (len > 8 && len <= 12) {
            String temp = int_value.substring(0, len - 8);
            if (Integer.parseInt(temp) != 0)
                cn_currency.append(
                        cell2CH(int_value.substring(0, len - 8), zero_flag))
                        .append(CARRY_YI);
            int_value = int_value.substring(len - 8, len);
            len = 8;

        }
        if (len > 4 && len <= 8) {
            // zero_flag = false;
            String temp = int_value.substring(0, len - 4);
            if (Integer.parseInt(temp) != 0)
                cn_currency.append(
                        cell2CH(int_value.substring(0, len - 4), zero_flag))
                        .append(CARRY_WAN);
            int_value = int_value.substring(len - 4, len);
            len = 4;
            zero_flag = true;
        }

        cn_currency.append(cell2CH(int_value.substring(0, len), zero_flag));

        if (!cn_currency.toString().equals(""))
            cn_currency.append(CUR_YUAN);

        int t = Character.getNumericValue(fraction_value.charAt(0));
        if (t != 0)
            cn_currency.append(digits[t]).append(CUR_JIAO);

        t = Character.getNumericValue(fraction_value.charAt(1));
        if (t != 0)
            cn_currency.append(digits[t]).append(CUR_FEN);

        String returnVaule = cn_currency.toString();
        returnVaule = StringUtils.replace("零零", "零", returnVaule);
        returnVaule = StringUtils.replace("零元", "元", returnVaule);
        returnVaule = StringUtils.replace("零万", "万", returnVaule);
        returnVaule = StringUtils.replace("零亿", "亿", returnVaule);
        if (returnVaule.endsWith(CUR_YUAN))
            returnVaule = returnVaule + "整";
        sb.append(returnVaule);
        return sb.toString();
    }

    private static String cell2CH(String cellValue, boolean zero_flag) {

        // 如果zero_flag=true,而且cell2CH的第二个值不是以"千"开头,则应该加上"零";
        // 比如:三万五百六十七 应该修改成 三万零五百六十七
        StringBuffer cellBuffer = new StringBuffer();
        cellValue = removeZero(cellValue);
        int pos = cellValue.length();
        int digit;
        if (pos == 4) {
            digit = Character.getNumericValue(cellValue.charAt(0));
            if (digit != 0)
                cellBuffer.append(digits[digit]).append(CARRY_QIAN);
            else
                cellBuffer.append(digits[0]);
            pos = pos - 1;
            cellValue = cellValue.substring(1, 4);
        }
        if (pos == 3) {
            digit = Character.getNumericValue(cellValue.charAt(0));
            if (digit != 0)
                cellBuffer.append(digits[digit]).append(CARRY_BAI);
            else
                cellBuffer.append(digits[0]);
            pos = pos - 1;
            cellValue = cellValue.substring(1, 3);
        }
        if (pos == 2) {
            digit = Character.getNumericValue(cellValue.charAt(0));
            if (digit != 0)
                cellBuffer.append(digits[digit]).append(CARRY_SHI);
            else
                cellBuffer.append(digits[0]);
            pos = pos - 1;
            cellValue = cellValue.substring(1, 2);
        }
        if (pos == 1) {
            digit = Character.getNumericValue(cellValue.charAt(0));
            if (digit != 0)
                cellBuffer.append(digits[digit]);
        }

        if (zero_flag && cellBuffer.length() >= 2
                && cellBuffer.charAt(1) != '仟')
            cellBuffer.insert(0, "零");
        if (zero_flag && cellBuffer.length() == 1)
            cellBuffer.insert(0, "零");
        return cellBuffer.toString();
    }

    private static String removeZero(String _cellValue) {
        String cellValue = _cellValue;
        if (cellValue.startsWith("0")) {
            cellValue = cellValue.substring(1);
            return cellValue = removeZero(cellValue);
        } else
            return cellValue;
    }

    /**
     * 将字符串得到按模式得到日期型的数据<br>
     * <p>
     * <pre>
     *   String dateString=&quot;1989-06-25 06:12:36&quot;
     *   String str = StringUtils.getDateString(dateString,HH:MM:SS)
     *   System.out.println(str); // &quot;06:12:36&quot;
     * </pre>
     */

    public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public final static String DEFAULT_TIME_PATTERN = "hh:mm:ss";

    public final static String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @SuppressWarnings("deprecation")
    public static String getDateString(String date, String pattern) {

        Date formatDate = null;
        char DatePatternMediator = '-';
        char TimePatternMediator = ':';

        if (date.indexOf(DatePatternMediator) != -1)
            if (date.indexOf(TimePatternMediator) != -1) {
                formatDate = getTimestamp(date, DEFAULT_TIMESTAMP_PATTERN);
            } else
                formatDate = getTimestamp(date, DEFAULT_DATE_PATTERN);
        else
            formatDate = getTimestamp(date, DEFAULT_TIME_PATTERN);

        StringBuffer sb = new StringBuffer();

        pattern = pattern.toLowerCase();

        if (pattern.equalsIgnoreCase("'YYYY年M月D日'")) {
            sb.append(formatDate.getYear() + 1900).append("年").append(
                    formatDate.getMonth() + 1).append("月").append(
                    formatDate.getDate()).append("日");
        }
        if (pattern.equalsIgnoreCase("'YYYY年MM月DD日'")) {
            sb.append(formatDate.getYear() + 1900).append("年").append(
                    fillZero(formatDate.getMonth() + 1)).append("月").append(
                    fillZero(formatDate.getDate())).append("日");
        }
        if (pattern.equals("'" + DEFAULT_TIMESTAMP_PATTERN.toLowerCase() + "'"))
            sb.append(formatDate.getYear() + 1900).append("-").append(
                    fillZero(formatDate.getMonth() + 1)).append("-").append(
                    fillZero(formatDate.getDate())).append(" ").append(
                    fillZero(formatDate.getHours())).append(":").append(
                    fillZero(formatDate.getMinutes())).append(":").append(
                    fillZero(formatDate.getSeconds()));
        if (pattern.equals("'" + DEFAULT_DATE_PATTERN.toLowerCase() + "'")) {
            sb.append(formatDate.getYear() + 1900).append("-").append(
                    fillZero(formatDate.getMonth() + 1)).append("-").append(
                    fillZero(formatDate.getDate()));
        }
        if (pattern.equals("'" + DEFAULT_TIME_PATTERN.toLowerCase() + "'")) {
            sb.append(fillZero(formatDate.getHours())).append(":").append(
                    fillZero(formatDate.getMinutes())).append(":").append(
                    fillZero(formatDate.getSeconds()));
        }

        if (pattern.equals("'yyyy-mm-dd hh:mm'"))
            sb.append(formatDate.getYear() + 1900).append("-").append(
                    fillZero(formatDate.getMonth() + 1)).append("-").append(
                    fillZero(formatDate.getDate())).append(" ").append(
                    fillZero(formatDate.getHours())).append(":").append(
                    fillZero(formatDate.getMinutes()));
        if (pattern.equals("'yyyy-mm-dd hh'"))
            sb.append(formatDate.getYear() + 1900).append("-").append(
                    fillZero(formatDate.getMonth() + 1)).append("-").append(
                    fillZero(formatDate.getDate())).append(" ").append(
                    fillZero(formatDate.getHours()));
        if (pattern.equals("'yyyy-mm'")) {
            sb.append(formatDate.getYear() + 1900).append("-").append(
                    fillZero(formatDate.getMonth() + 1));
        }
        if (pattern.equals("'mm-dd'")) {
            sb.append(fillZero(formatDate.getMonth() + 1)).append("-").append(
                    fillZero(formatDate.getDate()));
        }

        if (pattern.equals("'hh:mm'")) {

            sb.append(fillZero(formatDate.getHours())).append(":").append(
                    fillZero(formatDate.getMinutes()));
        }
        if (pattern.equals("'mm:ss'")) {

            sb.append(fillZero(formatDate.getMinutes())).append(":").append(
                    fillZero(formatDate.getSeconds()));
        }

        if (pattern.equals("'yyyy'"))
            sb.append(formatDate.getYear() + 1900);
        if (pattern.equals("'mm'"))
            sb.append(fillZero(formatDate.getMonth() + 1));
        if (pattern.equals("'dd'"))
            sb.append(fillZero(formatDate.getDate()));
        if (pattern.equals("'hh'"))
            sb.append(fillZero(formatDate.getHours()));
        return sb.toString();

    }

    private static String fillZero(int i) {
        if (i < 10)
            return "0" + String.valueOf(i);
        else
            return String.valueOf(i);
    }

    public static Timestamp getTimestamp(String timestamp, String pattern) {
        Timestamp t = null;

        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);

        try {
            t = new Timestamp(sdf.parse(timestamp).getTime());
        } catch (Exception e) {
        }

        return t;
    }

    /**
     * 判断传入的字符串数组是否有效(当字符串数组长度为0或为null或不等于length时无效)
     *
     * @param str
     * @param length 数组长度
     * @return
     */
    public static boolean isValid(String[] str, int length) {
        if (isValid(str)) {
            if (str.length == length)
                return true;
        }

        return false;
    }

    /**
     * 判断传入的字符串数组是否有效(当字符串数组长度为0或为null时无效)
     *
     * @param str
     * @return
     */
    public static boolean isValid(String[] str) {
        if (str != null && str.length > 0)
            return true;
        else
            return false;
    }

    /**
     * 判断数组中是否包含指定字符串
     *
     * @param src
     * @param value
     * @return 包含返回true否则false
     */
    public static boolean containValue(String[] src, String value) {
        if (!StringUtils.isValid(src))
            return false;
        boolean result = false;
        for (int i = 0; i < src.length; i++) {
            if (src[i].equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 两个字符串相除，保留一位小数（结果以百分比的形式保存）
     */
    public static String stringDivide(String num1, String num2) {
        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            if (n2 == 0) {
                return "-";
            }
            return String.valueOf(Math.round(n1 / n2 * 1000) / 10.0) + "%";
        } catch (Exception e) {
            return "-";
        }

    }

    /**
     * 累加文本中出现的数字
     *
     * @param str
     * @return
     */
    @SuppressWarnings("unchecked")
    public static int countNumInStr(String str) {
        int result = 0;
        if (StringUtils.isValid(str)) {
            HashMap<String, ?> numMap = new HashMap();
            numMap.put("0", null);
            numMap.put("1", null);
            numMap.put("2", null);
            numMap.put("3", null);
            numMap.put("4", null);
            numMap.put("5", null);
            numMap.put("6", null);
            numMap.put("7", null);
            numMap.put("8", null);
            numMap.put("9", null);

            int len = str.length();
            for (int i = 0; i < len; i++) {
                if (!numMap.containsKey(str.substring(i, i + 1))) {
                    str = str.substring(0, i) + " " + str.substring(i + 1, len);
                }
            }
            String[] numStr = StringUtils.SplitString(str, " ");
            if (StringUtils.isValid(numStr)) {
                for (int i = 0; i < numStr.length; i++) {
                    result += Integer.parseInt(numStr[i].trim());
                }
            }
        }

        return result;
    }

    /**
     * @param
     * @return String
     * @throws
     * @Title: replaceBlankSpace
     * @author Rolls
     * @Description:替换所有字符串中的空格
     * @date 2012-5-23 下午04:11:32
     * @version V1.0
     */
    public static String replaceBlankSpace(String str) {
        return str.replaceAll("\\s*", "");
    }

    /**
     * 获取对象属性，返回一个字符串数组
     *
     * @param o 对象
     * @return String[] 字符串数组
     */
    public static String[] getFiledName(Object o) {
        try {
            java.lang.reflect.Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * 取得对象的长度
     *
     * @param value 对象
     * @return int 对象的长度
     */
    public static int length(String value) {
        int valueLength = 0;

        if (isBlank(value)) {
            return valueLength;
        }

        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 用于判断用逗号分隔的字符串
     * 测试第一个字符串是否包含第二个字符串的内容
     * 若不包含则返回第一个不包含的内容
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String testContains(String str1, String str2) {
        if (StringUtils.isEmpty(str2)) {
            return null;
        }
        if (str1 == null) {
            str1 = StringUtils.EMPTY;
        }
        String result = null;
        // 去除空格并在两头添加逗号以方便比较
        str1 = "," + StringUtils.replaceBlankSpace(str1) + ",";
        String[] arr = str2.split(",", -1);
        for (int i = 0; i < arr.length; i++) {
            if (!str1.contains("," + arr[i] + ",")) {
                result = arr[i];
                break;
            }
        }
        return result;
    }

    /**
     * 判断是否是一个中文汉字
     *
     * @param c 字符
     * @return true表示是中文汉字，false表示是英文字母
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static boolean isChineseChar(char c)
            throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
        return String.valueOf(c).getBytes("GBK").length > 1;
    }

    /**
     * 按字节截取字符串
     *
     * @param orignal 原始字符串
     * @param count   截取位数
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static String substringForByte(String orignal, int count)
            throws UnsupportedEncodingException {
        // 原始字符不为null，也不是空字符串
        if (orignal != null && !"".equals(orignal)) {
            // 将原始字符串转换为GBK编码格式
            orignal = new String(orignal.getBytes(), "GBK");
            // 要截取的字节数大于0，且小于原始字符串的字节数
            if (count > 0 && count < orignal.getBytes("GBK").length) {
                StringBuffer buff = new StringBuffer();
                char c;
                for (int i = 0; i < count; i++) {
                    c = orignal.charAt(i);
                    buff.append(c);
                    if (isChineseChar(c)) {
                        // 遇到中文汉字，截取字节总数减1
                        --count;
                    }
                }
                return buff.toString();
            }
        }
        return orignal;
    }

    /**
     * 处理多选情况下的Checkbox值
     */
    public static String[] doCheckboxs(String val) {
        if (val != null && val.length() > 0) {
            return val.split(",");
        }
        return new String[]{};
    }

    /**
     * 获取学年
     */
    public static String getXn(String date) {
        if (date != null) {
            String[] dates = date.split("-");
            if (dates.length == 3) {
                int yf = Integer.parseInt(dates[1]);
                if (yf >= 9) {
                    return dates[0];
                } else {
                    int nf = Integer.parseInt(dates[0]);
                    return String.valueOf(nf - 1);
                }
            }
        }
        return "";
    }

    /**
     * 将对象属性值中的null变成""
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public static Object checkNull(Object vo) throws Exception {
        // 获取实体类的所有属性，返回Field数组
        Field[] field = vo.getClass().getDeclaredFields();
        // 获取属性的名字
        for (int i = 0; i < field.length; i++) {
            //可访问私有变量
            field[i].setAccessible(true);
            // 获取属性的名字
            String name = field[i].getName();
            // 获取属性类型
            String type = field[i].getGenericType().toString();
            // 将属性的首字母大写
            name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                    .toUpperCase());

            if (type.equals("class java.lang.String")) {
                // 如果type是类类型，则前面包含"class "，后面跟类名
                Method m = vo.getClass().getMethod("get" + name);
                // 调用getter方法获取属性值
                String value = (String) m.invoke(vo);
                if (!StringUtils.hasValue(value)) {
                    //给属性设值
                    field[i].set(vo, field[i].getType().getConstructor(field[i].getType()).newInstance(""));
                }
            }
        }
        return vo;
    }

    // 测试用
    public static void main(String[] args) {
//        System.out.println(getXn("2015-09-01"));
//        System.out.println(getXn("2015-08-01"));
        String value = "213132ew测试啊的方式dw";
        System.out.println(length(value));
        System.out.println(strSub(value, 10));
    }

    /**
     * 判断地区code是否属于上海地区
     * 上海地区code以310开头
     * @param ccode
     * @return
     */
    public static String belongToSh(String ccode) {
        String flag = "";
        if(StringUtils.isNotBlank(ccode)){
            if(ccode.startsWith("310")){
                flag = "yes";
            }else {
                flag = "no";
            }
        }else{
            flag = "no";
        }
        return flag;
    }
}