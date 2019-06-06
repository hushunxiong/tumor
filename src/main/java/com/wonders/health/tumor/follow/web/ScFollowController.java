package com.wonders.health.tumor.follow.web;

import com.wonders.health.tumor.common.controller.BaseController;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.follow.entity.ScFollow;
import com.wonders.health.tumor.follow.service.ScFollowService;
import com.wonders.health.tumor.follow.vo.ScFollowListVo;
import com.wonders.health.tumor.follow.vo.ScFollowVo;
import com.wonders.health.tumor.tumor.service.CancerPersonInfoService;
import com.wonders.health.tumor.tumor.service.ScRegcaseService;
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
 * 胃癌随访Controller
 * @author zhangyi
 */
@Controller
@RequestMapping("scFollow")
public class ScFollowController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ScFollowController.class);

    @Autowired
    CancerPersonInfoService cancerPersonInfoService;
    @Autowired
    ScRegcaseService scRegcaseService;
    @Autowired
    ScFollowService scFollowService;
    @Autowired
    AreaService areaService;
    /**
     * 胃癌随访一览
     * @return
     */
    @RequestMapping(value = {"","list"},method = RequestMethod.GET)
    public String list(Model model, String manageId,String checkYear) {
        model.addAttribute("info", scFollowService.scFollowList(manageId,checkYear));
        return "/follow/scFollowList";
    }
    /**
     * 胃癌随访一览列表
     */
    @RequestMapping(value = "getScData")
    @ResponseBody
    public DataGrid<ScFollowListVo> getScData(@RequestParam("scRegcaseId")String scRegcaseId) {
        List<ScFollowListVo> listSc=new ArrayList<>();
        List<ScFollow> list=scFollowService.queryScFollowListByCsId(scRegcaseId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        list.forEach(sc ->{
            ScFollowListVo vo=new ScFollowListVo();
            vo.setId(sc.getId());
            vo.setSuifangDate(sdf.format(sc.getSuifangDate()));
            vo.setSuifangjigouName(sc.getSuifangjigouName());
            vo.setSuifangyishengName(AuthUtils.getUserById(sc.getSuifangyishengId()).getName());
            listSc.add(vo);
        });
        return new DataGrid<>(listSc.size(),listSc);
    }
    /**
     * 胃癌随访页面
     */
    @RequestMapping(value = "openScFollow")
    public String openScFollow(Model model, @RequestParam("manageId")String manageId,@RequestParam("checkYear")String checkYear,@RequestParam("flag")String flag,@RequestParam(value = "id",required = false)String id){
        model.addAttribute("info", scFollowService.scFollowForm(manageId,checkYear,flag,id));
        return "/follow/scForm";
    }
    /**
     * 保存胃癌随访
     */
    @RequestMapping(value = "saveScFollow")
    @ResponseBody
    public AjaxReturn<Boolean> saveScFollow(@RequestBody ScFollowVo vo){
        vo.setUser(getSessionUser());
        AjaxReturn<Boolean> ajaxReturn = new AjaxReturn<>();
        if (scFollowService.saveScFollow(vo)){
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
     * 删除胃癌随访
     */
    @RequestMapping(value = "delScFollow")
    @ResponseBody
    public AjaxReturn<Boolean> delScFollow(@RequestParam("id")String id){
        AjaxReturn<Boolean> ajaxReturn = new AjaxReturn<>();
        if (scFollowService.delScFollow(id)){
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