package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.entity.CrcFollow;
import com.wonders.health.tumor.follow.service.CrcFollowService;
import com.wonders.health.tumor.follow.service.FollowService;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
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
        model.addAttribute("manageId", manageId);
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

    /**
     * 大肠癌随访一览数据删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", produces = "application/json; charset=utf-8")
    public AjaxReturn<String> delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            try {
                crcFollowService.deleteById(id);
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
        if (StringUtils.isNotBlank(manageId) && StringUtils.isNotBlank(checkYear)) {
            //大肠癌初筛信息
            CancerPersonInfo personInfo = followService.getPersonInfo(manageId);
            model.addAttribute("personInfo", personInfo);
            CrcRegcase regcase = crcFollowService.getRegcase(manageId, checkYear);
            model.addAttribute("regcase", regcase);
        }
        model.addAttribute("follow", new CrcFollow());
        model.addAttribute("flag", flag);
        return "/follow/crcForm";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model, String id, String flag) {
        if (StringUtils.isNotBlank(id)) {
            CrcFollow follow =  crcFollowService.getFollowById(id);
            if (follow != null) {
                //大肠癌初筛信息
                CrcRegcase regcase = crcFollowService.getRegcaseById(follow.getCrcCheckId());
                model.addAttribute("regcase", regcase);

                if (regcase != null) {
                    //初筛对象信息
                    CancerPersonInfo personInfo = followService.getPersonInfo(regcase.getManageid());
                    model.addAttribute("personInfo", personInfo);
                }
            }
            model.addAttribute("follow", follow);
            model.addAttribute("flag", flag);
        }
        return "/follow/crcForm";
    }
}
