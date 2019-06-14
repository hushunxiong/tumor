package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.entity.LicFollow;
import com.wonders.health.tumor.follow.service.FollowService;
import com.wonders.health.tumor.follow.service.LicFollowService;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.health.tumor.tumor.entity.LicRegcase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * 肝癌随访Controller
 * @author sunyang
 */
@Controller
@RequestMapping("licFollow")
public class LicFollowController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CrcFollowController.class);
    @Autowired
    private FollowService followService;
    @Autowired
    private LicFollowService licFollowService;

    /**
     * 肝癌随访一览画面
     * @return
     */
    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Model model, String manageId, String checkYear) {
        model.addAttribute("manageId", manageId);
        model.addAttribute("checkYear", checkYear);
        model.addAttribute("personInfo", followService.getPersonInfo(manageId));

        LicRegcase regcase = licFollowService.getRegcase(manageId, checkYear);
        if (regcase != null) {
            model.addAttribute("checkId", regcase.getId());
            model.addAttribute("diagStatus", licFollowService.getDiagStatus(regcase.getId()));
        } else {
            regcase = new LicRegcase();
        }
        model.addAttribute("regcase", regcase);
        return "/follow/licFollowList";
    }

    /**
     * 肝癌随访一览数据取得
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "data", produces = "application/json; charset=utf-8")
    public DataGrid<LicFollow> data(FollowSearchVo search) {
        DataGrid<LicFollow> grid = licFollowService.getFollowList(search);
        return grid;
    }

    /**
     * 肝癌随访一览数据删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                licFollowService.deleteById(id);
                return new AjaxReturn<String>(true, "删除成功");
            } catch (Exception e) {
                logger.error("", e);
                return new AjaxReturn<String>(false, "删除异常");
            }
        } else {
            return new AjaxReturn<String>(false, "传入id不能为空");
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, String manageId, String checkYear, String flag) {
        String licCheckId = "";
        if (StringUtils.isNotBlank(manageId) && StringUtils.isNotBlank(checkYear)) {
            CancerPersonInfo personInfo = followService.getPersonInfo(manageId);
            model.addAttribute("personInfo", personInfo);
            //肝癌初筛信息
            LicRegcase regcase = licFollowService.getRegcase(manageId, checkYear);
            licCheckId = regcase.getId();
            model.addAttribute("regcase", regcase);
        }
        LicFollow follow = new LicFollow();
        follow.setSuifangyishengId(getSessionUser().getId());
        follow.setSuifangyishengName(getSessionUser().getName());
        follow.setSuifangjigouId(getSessionUser().getOrgCode());
        follow.setSuifangjigouName(AuthUtils.getHospitalByCode(getSessionUser().getOrgCode()).getName());
        follow.setSuifangDate(new Date());
        follow.setLicCheckId(licCheckId);
        follow.setPrimaryMark(getSessionUser().getOrgCode());

        model.addAttribute("follow", follow);
        model.addAttribute("flag", flag);
        model.addAttribute("manageId", manageId);
        model.addAttribute("checkYear", checkYear);
        return "/follow/licForm";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model, String id, String flag) {
        if (StringUtils.isNotBlank(id)) {
            LicFollow follow =  licFollowService.getFollowById(id);
            if (follow != null) {
                follow.setSuifangyishengName(AuthUtils.getUserById(follow.getSuifangyishengId()).getName());
                //肝癌初筛信息
                LicRegcase regcase = licFollowService.getRegcaseById(follow.getLicCheckId());
                model.addAttribute("regcase", regcase);

                if (regcase != null) {
                    //初筛对象信息
                    CancerPersonInfo personInfo = followService.getPersonInfo(regcase.getManageid());
                    model.addAttribute("personInfo", personInfo);
                    model.addAttribute("manageId", regcase.getManageid());
                    model.addAttribute("checkYear", regcase.getCheckYear());
                }
            }
            model.addAttribute("follow", follow);
            model.addAttribute("flag", flag);
        }
        return "/follow/licForm";
    }

    @ResponseBody
    @RequestMapping(value = "save", produces = "application/json; charset=utf-8")
    public AjaxReturn<Map<String, String>> save(@Valid LicFollow vo, BindingResult result) {

        if (result.hasErrors()) {
            return new AjaxReturn<Map<String, String>>(false, "校验失败");
        }

        try {
            return licFollowService.saveOrUpdate(vo, getSessionUser());
        } catch (Exception e) {
            logger.error("", e);
            return new AjaxReturn<Map<String, String>>(false, "保存异常");
        }
    }
}
