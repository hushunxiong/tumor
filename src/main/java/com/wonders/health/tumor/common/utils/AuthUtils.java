package com.wonders.health.tumor.common.utils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.health.auth.client.vo.Hospital;
import com.wonders.health.auth.client.vo.Menu;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.tumor.entity.DicHospitalInfo;
import com.wonders.health.tumor.tumor.service.DicHospitalInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

/**
 * jsp使用的函数
 * Created by xuguobing on 2017/5/8.
 */
public class AuthUtils {

    private static AuthServiceI authService = SpringContextHolder.getBean(AuthServiceI.class);



    private static DicHospitalInfoService dicHospitalInfoService = SpringContextHolder.getBean(DicHospitalInfoService.class);

    public static String[] qxcode = {"310101000000",  "310104000000", "310105000000", "310106000000", "310107000000",
            "310108000000","310109000000", "310110000000", "310112000000", "310113000000", "310114000000", "310115000000",
             "310116000000", "310117000000","310118000000", "310120000000", "310230000000"};
    public static String[] qxName = {"黄浦区",  "徐汇区", "长宁区", "静安区", "普陀区",
            "闸北区" ,"虹口区", "杨浦区", "闵行区", "宝山区", "嘉定区", "浦东新区",
            "金山区","松江区",  "青浦区", "奉贤区", "崇明区"};
    public static String[] qjkCode = {"31010101300",  "31010401900", "31010500500", "31010601600", "31010700300",
            "31010800100","31010901100", "31011000500", "31011201800", "31011300600", "31011400600", "31011501800",
            "31011600400", "31011700300","31011800400", "31012000500", "31023001200","C1010800000"};
    /**
     * 获取登陆用户的所有菜单
     *
     * @return
     */
    public static List<Menu> getMenus() {
        Object ms = CacheUtils.getSessionCache("user_menus");
        if (ms != null) {
            return (List<Menu>) ms;
        }
        List<Menu> menus = null;
        User user = getUser();
        if (user != null) {
            menus = authService.findMenusForTree(user.getId(), "ZLZFX");
            CacheUtils.putSessionCache("user_menus", menus);
        }
        return menus;
    }

    /**
     * 获取登陆用户
     *
     * @return
     */
    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Object ms = CacheUtils.getSessionCache("login_user");
        if (ms != null) {
            return (User) ms;
        }
        try {
            User user = authService.findUserByUsername(username);
            CacheUtils.putSessionCache("login_user", user);
            return user;
        } catch (Exception e) {
            return new User();
        }
    }

    /**
     * 获取指定用户
     *
     * @return
     */
    public static User getUserById(String id) {
        try {
            User user = authService.findUserById(id);
            return user;
        } catch (Exception e) {
            return new User();
        }
    }

    /**
     * 获取指定医疗机构
     *
     * @return
     */
    public static Hospital getHospitalByCode(String code) {
        try {
            Hospital hospital = authService.findHospitalByCode(code);
            return hospital;
        } catch (Exception e) {
            return new Hospital();
        }
    }


    public static String toJson(Object o) {
        if (o == null || (o instanceof String && StringUtils.isBlank(o.toString()))) {
            return "";
        }
        return new Gson().toJson(o);
    }

    /**
     * 判断医疗机构属于市疾控、区疾控、社区
     *
     * @param yljgdm
     * @return
     */
    public static String judgeRole(String yljgdm) {
        if (org.apache.commons.lang3.StringUtils.isBlank(yljgdm)) {
            return null;
        }
        if ("31010502300".equals(yljgdm)) {
            return "sjk";
        } else {
            if (Arrays.asList(qjkCode).contains(yljgdm)) {
                return "qjk";
            }
        }
        return "bsq";
    }

    /**
     * 根据登录角色获取区县
     * 市疾控---上海市所有区
     * 区疾控---当前登录所属区
     * 社区--当前登录所属区
     * @param areaCode
     * @return
     */
    public  static List<DataOption> getAreas(String areaCode){
        List<DataOption> offices = Lists.newArrayList();
        String role=judgeRole(getUser().getOrgCode());
        if("sjk".equals(role)){
            if(qxcode!=null&&qxName!=null){
                for(int i=0;i<qxcode.length;i++){
                    if(StringUtils.isNotBlank(qxcode[i])&&StringUtils.isNotBlank(qxName[i]))
                    {
                        offices.add(new DataOption(qxcode[i], qxName[i], PinYinUtil.getFirstSpell(qxName[i])));
                    }
                }
            }
        }else{
            offices.add(new DataOption(areaCode, qxName[Arrays.asList(qxcode).indexOf(areaCode)], PinYinUtil.getFirstSpell(qxName[Arrays.asList(qxcode).indexOf(areaCode)])));
        }
        return  offices;
    }



}
