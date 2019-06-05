package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.follow.entity.CrcFollow;
import com.wonders.health.tumor.follow.service.CrcFollowService;
import com.wonders.health.tumor.follow.service.FollowService;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 大肠癌随访一览画面
     * @return
     */
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model, String manageId, String checkYear) {
        model.addAttribute("checkYear", checkYear);
        model.addAttribute("personInfo", followService.getPersonInfo(manageId));

        CrcRegcase regcase = crcFollowService.getRegcase(manageId, checkYear);
        model.addAttribute("regcase", regcase);

        if (regcase != null) {
            model.addAttribute("checkId", regcase.getId());
            model.addAttribute("diagStatus", crcFollowService.getDiagStatus(regcase.getId()));
        }
        return "/follow/crcFollowList";
    }

    /**
     * 大肠癌随访一览数据取得
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<CrcFollow> data(FollowSearchVo search) {
        DataGrid<CrcFollow> grid = crcFollowService.getFollowList(search);
        return grid;
    }





}
