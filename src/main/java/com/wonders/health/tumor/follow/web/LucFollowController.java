package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.follow.entity.LucFollow;
import com.wonders.health.tumor.follow.service.LucFollowService;
import com.wonders.health.tumor.follow.vo.LucFollowListVo;
import com.wonders.health.tumor.follow.vo.LucFollowVo;
import com.wonders.health.tumor.tumor.service.CancerPersonInfoService;
import com.wonders.health.tumor.tumor.service.LucRegcaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 肺癌随访Controller
 * @author zhangyi
 */
@Controller
@RequestMapping("lucFollow")
public class LucFollowController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LucFollowController.class);

    @Autowired
    CancerPersonInfoService cancerPersonInfoService;
    @Autowired
    LucRegcaseService lucRegcaseService;
    @Autowired
    LucFollowService lucFollowService;
    @Autowired
    AreaService areaService;

    /**
     * 肺癌随访一览
     * @return
     */
    @RequestMapping(value = {"","list"},method = RequestMethod.GET)
    public String list(Model model, String manageId,String checkYear) {
        model.addAttribute("info", lucFollowService.lucFollowList(manageId,checkYear));
        return "/follow/lucFollowList";
    }
    /**
     * 肺癌随访一览列表
     */
    @RequestMapping(value = "getLucData")
    @ResponseBody
    public DataGrid<LucFollowListVo> getLucData(@RequestParam("lucRegcaseId")String lucRegcaseId) {
        List<LucFollowListVo> listLuc=new ArrayList<>();
        List<LucFollow> list=lucFollowService.queryLucFollowListByLucRegcaseId(lucRegcaseId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        list.forEach(luc ->{
            LucFollowListVo vo=new LucFollowListVo();
            vo.setId(luc.getId());
            vo.setSuifangDate(sdf.format(luc.getSuifangDate()));
            vo.setSuifangyishengName(AuthUtils.getUserById(luc.getSuifangyishengId()).getName());
            vo.setSuifangjigouName(luc.getSuifangjigouName());
            listLuc.add(vo);
        });
        return new DataGrid<>(listLuc.size(),listLuc);
    }
    /**
     * 肺癌随访页面
     */
    @RequestMapping(value = "openLucFollow")
    public String openLucFollow(Model model, @RequestParam("manageId")String manageId,@RequestParam("checkYear")String checkYear,@RequestParam("flag")String flag,@RequestParam(value = "id",required = false)String id,@RequestParam(value = "kp",required = false)String kp){
        model.addAttribute("info", lucFollowService.lucFollowForm(manageId,checkYear,flag,id));
        model.addAttribute("kp",kp);
        return "/follow/lucForm";
    }
    /**
     * 保存肺癌随访
     */
    @RequestMapping(value = "saveLucFollow")
    @ResponseBody
    public AjaxReturn<Boolean> saveLucFollow(@RequestBody LucFollowVo vo){
        vo.setUser(getSessionUser());
        AjaxReturn<Boolean> ajaxReturn = new AjaxReturn<>();
        if (lucFollowService.saveLucFollow(vo)){
            ajaxReturn.setMsg("保存成功!");
            ajaxReturn.setOk(true);
            ajaxReturn.setValue(true);
        }else{
            ajaxReturn.setMsg("保存失败!");
            ajaxReturn.setOk(false);
            ajaxReturn.setValue(false);
        }
        return ajaxReturn;
    }
    /**
     * 删除肺癌随访
     */
    @RequestMapping(value = "delLucFollow")
    @ResponseBody
    public AjaxReturn<Boolean> delLucFollow(@RequestParam("id")String id){
        AjaxReturn<Boolean> ajaxReturn = new AjaxReturn<>();
        if (lucFollowService.delLucFollow(id)){
            ajaxReturn.setMsg("删除成功!");
            ajaxReturn.setOk(true);
            ajaxReturn.setValue(true);
        }else{
            ajaxReturn.setMsg("删除失败!");
            ajaxReturn.setOk(false);
            ajaxReturn.setValue(false);
        }
        return ajaxReturn;
    }
}