package com.wonders.health.tumor.common.controller;

import com.google.common.collect.Lists;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.service.HospitalDicService;
import com.wonders.health.tumor.common.utils.PinYinUtil;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sunyang on 2019/4/19.
 */
@Controller
@RequestMapping("hospital")
public class HospitalDicController {

    @Autowired
    private HospitalDicService hospitalDicService;


    @RequestMapping("queryName")
    @ResponseBody
    public List<DataOption> queryName(String name) {
        List<CancerDicHospitalInfo> list = hospitalDicService.queryName(name);

        if (list != null && list.size() > 0) {
            List<DataOption> dos = Lists.newArrayList();
            for (CancerDicHospitalInfo vo : list) {
                if (StringUtils.isNotBlank(vo.getHospitalName())) {
                    dos.add(new DataOption(vo.getHospitalId(), vo.getHospitalName(),
                            PinYinUtil.getFirstSpell(vo.getHospitalName())));
                } else {
                    dos.add(new DataOption(vo.getHospitalId(), vo.getHospitalName()));
                }
            }
            return dos;
        }

        return null;
    }
}
