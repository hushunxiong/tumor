package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 胃癌随访Controller
 * @author zhangyi
 */
@Controller
@RequestMapping("scFollow")
public class ScFollowController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ScFollowController.class);

    /**
     * 胃癌随访一览
     * @return
     */
    @RequestMapping("list")
    public String list() {
        return "/follow/list/scFollowList";
    }


}