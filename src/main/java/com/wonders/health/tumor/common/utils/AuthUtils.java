package com.wonders.health.tumor.common.utils;

import com.google.gson.Gson;
import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.health.auth.client.vo.Menu;
import com.wonders.health.auth.client.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * jsp使用的函数
 * Created by xuguobing on 2017/5/8.
 */
public class AuthUtils {

    private static AuthServiceI authService = SpringContextHolder.getBean(AuthServiceI.class);


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


    public static String toJson(Object o) {
        if (o == null || (o instanceof String && StringUtils.isBlank(o.toString()))) {
            return "";
        }
        return new Gson().toJson(o);
    }

}
