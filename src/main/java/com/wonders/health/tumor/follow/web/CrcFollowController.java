package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.follow.service.CrcFollowService;
import com.wonders.health.tumor.follow.service.FollowService;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 大肠癌随访Controller
 * @author sunyang
 */
@Controller
@RequestMapping("crcFollow")
public class CrcFollowController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CrcFollowController.class);
    @Autowired
    private FollowService followService;
    @Autowired
    private CrcFollowService crcFollowService;

    /**
     * 大肠癌随访一览
     * @return
     */
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model, String manageId, String checkYear) {
        model.addAttribute("checkYear", checkYear);
        CancerPersonInfo personInfo = followService.getPersonInfo(manageId);
        model.addAttribute("personInfo", personInfo);
        CrcRegcase regcase = crcFollowService.getRegcase(manageId, checkYear);
        model.addAttribute("regcase", regcase);
        return "/follow/crcFollowList";
    }



}
