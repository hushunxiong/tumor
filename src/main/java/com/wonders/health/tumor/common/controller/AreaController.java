package com.wonders.health.tumor.common.controller;

import com.wonders.health.tumor.common.entity.CancerDicArea;
import com.wonders.health.tumor.common.model.DataOption;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.PinYinUtil;
import com.wonders.health.tumor.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyang on 2019/4/17.
 */
@Controller
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("query")
    @ResponseBody
    public List<DataOption> query(String level, String pcode) {
        if (StringUtils.isBlank(level)) {
            return null;
        }
        List<CancerDicArea> vos = areaService.findAreas(level, pcode);

        if (vos != null && vos.size() > 0) {
            List<DataOption> dos = new ArrayList<>();
            for (CancerDicArea vo : vos) {
                if (StringUtils.isNotBlank(vo.getCname())) {
                    dos.add(new DataOption(vo.getCcode(), vo.getCname(),
                            PinYinUtil.getFirstSpell(vo.getCname())));
                } else {
                    dos.add(new DataOption(vo.getCcode(), vo.getCname()));
                }
            }
            return dos;
        }

        return null;
    }
}
