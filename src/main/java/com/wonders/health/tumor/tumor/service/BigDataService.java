package com.wonders.health.tumor.tumor.service;

import com.google.common.collect.Lists;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.entity.CancerDic;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.service.AreaService;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.tumor.dao.LucPushXhDao;
import com.wonders.health.tumor.tumor.dao.LucPushriskXhDao;
import com.wonders.health.tumor.tumor.entity.LucPushXh;
import com.wonders.health.tumor.tumor.entity.LucPushriskXh;
import com.wonders.health.tumor.tumor.vo.BigDataSearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 大数据筛查Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class BigDataService {
    @Autowired
    private AreaService areaService;
    @Autowired
    private LucPushXhDao lucPushXhDao;
    @Autowired
    private LucPushriskXhDao lucPushriskXhDao;

    public DataGrid<LucPushXh> findPage(BigDataSearchVo search) {
        String[] IGNORES = {"riskAccount"};
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60054");
        List<LucPushXh> list=null;
        Integer count=lucPushXhDao.pageCount(search);
        if(count>0){
            list=lucPushXhDao.pageList(search);
        }
        if(list!=null&&list.size()>0){
            for(LucPushXh xh:list){
                LucPushXh person = lucPushXhDao.getByPersoncard(xh.getPersoncard());
                BeanUtils.copyProperties(person, xh, IGNORES);

                //居住地址拼接
                xh.setAddressDetail(areaService.getFullAddress(
                        xh.getProvince(), xh.getCity(), xh.getCounty(),
                        xh.getTown(), xh.getCommittee(), xh.getDetail()));
                //手机优先，然后是固定电话
                xh.setMobile(StringUtils.isNotBlank(xh.getMobile())
                        ? xh.getMobile() : xh.getTelephone());

                String riskAccount = xh.getRiskAccount();
                if (StringUtils.isNotBlank(riskAccount)) {
                    List<String> riskList = Arrays.asList(riskAccount.split(","));
                    List<String> riskNewList =riskList.stream().sorted().map(n ->generalForMap.get(n).getName()).collect(Collectors.toList());
                    xh.setRiskAccount(riskNewList.stream().collect(Collectors.joining(",")));
                }
            }
        }
        return new DataGrid<LucPushXh>(count,list);
    }

    public LucPushXh getPushData(String id) {
        LucPushXh pushXh = lucPushXhDao.get(id);
        if (StringUtils.isNotBlank(pushXh.getPersoncard())) {
            String birth = pushXh.getPersoncard().substring(6, 14);
            birth = birth.substring(0,4) + "-" + birth.substring(4,6) + "-" + birth.substring(6,8);
            //生日
            pushXh.setBirth(DateUtils.parseDate(birth));
        }
        //居住地址拼接
        pushXh.setAddressDetail(areaService.getFullAddress(
                pushXh.getProvince(), pushXh.getCity(), pushXh.getCounty(),
                pushXh.getTown(), pushXh.getCommittee(), pushXh.getDetail()));
        return pushXh;
    }

    public List<LucPushriskXh> getPushRiskData(String id) {
        List<CancerDic> dictList =DictUtils.generals("60054");
        List<LucPushriskXh> list = Lists.newArrayList();

        //所有字典项添加到list
        dictList.stream().forEach(dic -> {
            LucPushriskXh xh = new LucPushriskXh();
            xh.setRiskAccount(dic.getCode());
            xh.setRiskFlag("0");
            list.add(xh);
        });

        //个人危险因素取得
        List<LucPushriskXh> riskList = lucPushriskXhDao.getRiskData(id);

        //匹配的设置flag为1
        list.stream().filter(xh -> riskList.stream().anyMatch(risk ->
            xh.getRiskAccount().equals(risk.getRiskAccount())
        )).forEach(xh -> xh.setRiskFlag("1"));

        return list;
    }

    public List<CancerDic> getStatusList() {
        List<CancerDic> dictList = DictUtils.generals("60057");
        List<CancerDic> list = dictList.stream().filter(dic -> !StringUtils.equals(dic.getCode(), "0")).collect(Collectors.toList());
        return list;
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(String pushid, String status, User user) {

        if (StringUtils.isNotBlank(pushid)) {
            //修改
            LucPushXh po = lucPushXhDao.get(pushid);
            if (po != null) {
                po.initByUpdate(user.getId());
                po.setVerifystatus(status);
                lucPushXhDao.update(po);
            }
            return new AjaxReturn<Map<String, String>>(true, "核实成功");
        } else {
            return new AjaxReturn<Map<String, String>>(false, "画面数据提交失败");
        }
    }
}
