package com.wonders.health.tumor.common.tags;


import com.wonders.health.auth.client.vo.Menu;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.utils.AuthUtils;

import java.util.List;

/**
 * Created by xuguobing on 2019/3/11.
 */
public class AuthData {

    public List<Menu> menus() {
        List<Menu> menus = AuthUtils.getMenus();
        return menus;
    }

    public User currUser() {
        return AuthUtils.getUser();
    }

}
