package com.wonders.health.tumor.common.utils;


import com.wonders.health.tumor.common.service.DictService;
import com.wonders.health.tumor.common.entity.CancerDic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/16.
 */

public class DictUtils {

    private static DictService dictService = SpringContextHolder.getBean(DictService.class);


    /**
     * 根据id取得list
     *
     * @param id 字典ID
     * @return
     */
    public static List<CancerDic> generals(String id) {
        String key = "cdc_generals:" + id;
        Object o = CacheUtils.getSessionCache(key);
        if (o != null) {
            return (List<CancerDic>) o;
        }
        try {
            List<CancerDic> list = dictService.getDictsById(id);

            if (list != null && list.size() > 0) {
                CacheUtils.putSessionCache(key, list);
                return list;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id取得map
     *
     * @param id 字典ID
     * @return
     */
    public static Map<String, CancerDic> generalForMap(String id) {
        List<CancerDic> generalVos = generals(id);
        if (generalVos != null && generalVos.size() > 0) {
            Map<String, CancerDic> jyxmMap = new HashMap<>();
            for (CancerDic gVo : generalVos) {
                jyxmMap.put(gVo.getCode(), gVo);
            }
            return jyxmMap;
        }
        return null;
    }
}
