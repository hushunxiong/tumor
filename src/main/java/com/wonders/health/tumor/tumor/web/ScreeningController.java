package com.wonders.health.tumor.tumor.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 新增筛查登记Controller
 * @author menglianghai
 */
@Controller
@RequestMapping("screening")
public class ScreeningController {

    private static Logger logger = LoggerFactory.getLogger(ScreeningController.class);



//    @RequiresPermissions("tumor:screening:form")
    @RequestMapping(value = {"", "form"}, method = RequestMethod.GET)
    public String form(Model model) {



        return "tumor/familyCancerHistoryList";
    }


}
