package com.wonders.health.tumor.common.tags;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.common.entity.CancerDic;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by sunyang on 2019/4/16.
 */
public class DictData {

    private Gson gson = new Gson();

    public String generalName(String id, String codes) {
        if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(codes)) {
            List<CancerDic> list = DictUtils.generals(id);
            if (list != null && list.size() > 0) {
                Set<String> codeSet = Sets.newHashSet(codes.split(","));
                StringBuffer codeStr = new StringBuffer(50);
                for (CancerDic vo : list) {
                    if (codeSet.contains(vo.getCode())) {
                        if (codeStr.length() > 0) {
                            codeStr.append(";");
                        }
                        codeStr.append("<span style='display: block;'>");
                        codeStr.append(vo.getName());
                        codeStr.append("</span>");
                    }
                }
                return codeStr.toString();
            }
        }
        return "";
    }

    public String generals(String arr) {
        if (StringUtils.isBlank(arr)) {
            return "";
        }
        String type = null;
        //Set<String> codes = Sets.newHashSet();
        if (arr.contains(":")) {
            String[] arrs = arr.split(":");
            type = arrs[0];
            if (arrs.length == 2) {
                String[] ss = arrs[1].split(",");
                Map<String, CancerDic> map = DictUtils.generalForMap(type);
                if (map != null) {
                    Set<CancerDic> removeSet = new TreeSet<>(Comparator.comparing(CancerDic::getCode));
                    for (String s : ss) {
                        if (StringUtils.isNotBlank(s)) {
                            //codes.add(s.trim());
                            removeSet.add(map.get(s));
                        }
                    }
                    if (removeSet != null) {
                        return gson.toJson(removeSet);
                    }
                }
            }
        } else {
            type = arr;
        }
        List<CancerDic> list = DictUtils.generals(type);
        if (list != null) {
            return gson.toJson(list);
        }
        return "";
    }

}
