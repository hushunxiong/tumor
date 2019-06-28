package com.wonders.health.tumor.sys.web;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xuguobing on 2019/4/9.
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "/index";
    }

    @RequestMapping(value = {"/authFail"})
    public String authFail(AuthenticationException ae) {
        logger.error(ae.getMessage());
        return "/500";
    }

    @ResponseBody
    @RequestMapping(value = "/monitor")
    public String monitor() {
        return "success";
    }
}
