package com.wonders.health.tumor.common.tags;



import com.google.gson.Gson;

import com.wonders.health.auth.client.vo.Menu;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

/**
 * Created by xuguobing on 2019/3/11.
 */
public class AuthData {

    private Gson gson = new Gson();

    public List<Menu> menus() {
        List<Menu> menus = AuthUtils.getMenus();
        return menus;
    }

    public User currUser() {
        return AuthUtils.getUser();
    }


    public String getAreas(String areaCode){
        List<DataOption> list = AuthUtils.getAreas(areaCode);
        if (list != null) {
            return gson.toJson(list);
        }
        return  "";
    }



}
