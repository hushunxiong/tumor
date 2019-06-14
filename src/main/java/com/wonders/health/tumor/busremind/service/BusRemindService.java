/**
 * 
 */
package com.wonders.health.tumor.busremind.service;

import com.wonders.health.tumor.busremind.dao.BusRemindDao;
import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.closingcase.dao.crcClosingCaseDao;
import com.wonders.health.tumor.closingcase.entity.crcClosingCase;
import com.wonders.health.tumor.common.entity.CancerDic;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.DictUtils;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.tumor.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务提醒Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class BusRemindService {

    @Autowired
    private BusRemindDao busRemindDao;

    public DataGrid<CancerPersonInfo> findPersoninfo(DataGridSearch search) {
        List<BusRemindResultVo> list = busRemindDao.getBasic(search);
        List<CancerPersonInfo> reslist= new ArrayList<>();
        reslist=list.stream().map(bus->{return bus.getCancerPersonInfo();}).collect(Collectors.toList());
        return new DataGrid<CancerPersonInfo>(reslist.size(),reslist);
    }


    public  DataGrid<BusRemindResultVo>getCrcFobtRemind(String personcard) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = busRemindDao.getCrcFobtRemind(personcard);
        list.stream().forEach(bus->{
            if(bus.getFobtR()!=null){
                String remindType1=bus.getFobtR().getFirstFobtRemindType();
                if(StringUtils.isNotBlank(remindType1)){
                    if(generalForMap.containsKey(remindType1)){
                        bus.getFobtR().setFirstFobtRemindType(generalForMap.get(remindType1).getName()+ DateUtils.formatDate(bus.getFobtR().getFirstFobtRemindDate()));
                    }
                }
                String remindType2=bus.getFobtR().getSecondFobtRemindType();
                if(StringUtils.isNotBlank(remindType2)){
                    if(generalForMap.containsKey(remindType2)){
                        bus.getFobtR().setSecondFobtRemindType(generalForMap.get(remindType2).getName()+ DateUtils.formatDate(bus.getFobtR().getSecondFobtRemindDate()));
                    }
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getCrcDiag(String personcard,String crcFlag) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = busRemindDao.getCrcDiag(personcard,crcFlag,null,null,null);
        list.stream().forEach(bus->{
            if(bus.getCrcDiag()!=null){
                String remindType1=bus.getCrcDiag().getFirstRemindType();
                if(StringUtils.isNotBlank(remindType1)){
                    if(generalForMap.containsKey(remindType1)){
                        bus.getCrcDiag().setFirstRemindType(generalForMap.get(remindType1).getName()+ DateUtils.formatDate(bus.getCrcDiag().getFirstRemindDate()));
                    }
                }
                String remindType2=bus.getCrcDiag().getSecondRemindType();
                if(StringUtils.isNotBlank(remindType2)){
                    if(generalForMap.containsKey(remindType2)){
                        bus.getCrcDiag().setSecondRemindType(generalForMap.get(remindType2).getName()+ DateUtils.formatDate(bus.getCrcDiag().getSecondRemindDate()));
                    }
                }
                String remindType3=bus.getCrcDiag().getThirdRemindType();
                if(StringUtils.isNotBlank(remindType3)){
                    if(generalForMap.containsKey(remindType3)){
                        bus.getCrcDiag().setThirdRemindType(generalForMap.get(remindType3).getName()+ DateUtils.formatDate(bus.getCrcDiag().getThirdRemindDate()));
                    }
                }
            }
            if(bus.getCrcRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getCrcRegcase().getCheckResult())){
                    bus.getCrcRegcase().setCheckResult(map.get(bus.getCrcRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getLicDiag(String personcard,String licFlag) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = busRemindDao.getLicDiag(personcard,null,licFlag,null,null);
        list.stream().forEach(bus->{
            if(bus.getLicDiag()!=null){
                String remindType1=bus.getLicDiag().getFirstRemindType();
                if(StringUtils.isNotBlank(remindType1)){
                    if(generalForMap.containsKey(remindType1)){
                        bus.getLicDiag().setFirstRemindType(generalForMap.get(remindType1).getName()+ DateUtils.formatDate(bus.getLicDiag().getFirstRemindDate()));
                    }
                }
                String remindType2=bus.getLicDiag().getSecondRemindType();
                if(StringUtils.isNotBlank(remindType2)){
                    if(generalForMap.containsKey(remindType2)){
                        bus.getLicDiag().setSecondRemindType(generalForMap.get(remindType2).getName()+ DateUtils.formatDate(bus.getLicDiag().getSecondRemindDate()));
                    }
                }
                String remindType3=bus.getLicDiag().getThirdRemindType();
                if(StringUtils.isNotBlank(remindType3)){
                    if(generalForMap.containsKey(remindType3)){
                        bus.getLicDiag().setThirdRemindType(generalForMap.get(remindType3).getName()+ DateUtils.formatDate(bus.getLicDiag().getThirdRemindDate()));
                    }
                }
            }
            if(bus.getLicRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getLicRegcase().getCheckResult())){
                    bus.getLicRegcase().setCheckResult(map.get(bus.getLicRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getLucDiag(String personcard,String lucFlag) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = busRemindDao.getLucDiag(personcard,null,null,null,lucFlag);
        list.stream().forEach(bus->{
            if(bus.getLucDiag()!=null){
                String remindType1=bus.getLucDiag().getFirstRemindType();
                if(StringUtils.isNotBlank(remindType1)){
                    if(generalForMap.containsKey(remindType1)){
                        bus.getLucDiag().setFirstRemindType(generalForMap.get(remindType1).getName()+ DateUtils.formatDate(bus.getLucDiag().getFirstRemindDate()));
                    }
                }
                String remindType2=bus.getLucDiag().getSecondRemindType();
                if(StringUtils.isNotBlank(remindType2)){
                    if(generalForMap.containsKey(remindType2)){
                        bus.getLucDiag().setSecondRemindType(generalForMap.get(remindType2).getName()+ DateUtils.formatDate(bus.getLucDiag().getSecondRemindDate()));
                    }
                }
                String remindType3=bus.getLucDiag().getThirdRemindType();
                if(StringUtils.isNotBlank(remindType3)){
                    if(generalForMap.containsKey(remindType3)){
                        bus.getLucDiag().setThirdRemindType(generalForMap.get(remindType3).getName()+ DateUtils.formatDate(bus.getLucDiag().getThirdRemindDate()));
                    }
                }
            }
            if(bus.getLucRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getLucRegcase().getCheckResult())){
                    bus.getLucRegcase().setCheckResult(map.get(bus.getLucRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getScDiag(String personcard,String scFlag) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = busRemindDao.getScDiag(personcard,null,null,scFlag,null);
        list.stream().forEach(bus->{
            if(bus.getScDiag()!=null){
                String remindType1=bus.getScDiag().getFirstRemindType();
                if(StringUtils.isNotBlank(remindType1)){
                    if(generalForMap.containsKey(remindType1)){
                        bus.getScDiag().setFirstRemindType(generalForMap.get(remindType1).getName()+ DateUtils.formatDate(bus.getScDiag().getFirstRemindDate()));
                    }
                }
                String remindType2=bus.getScDiag().getSecondRemindType();
                if(StringUtils.isNotBlank(remindType2)){
                    if(generalForMap.containsKey(remindType2)){
                        bus.getScDiag().setSecondRemindType(generalForMap.get(remindType2).getName()+ DateUtils.formatDate(bus.getScDiag().getSecondRemindDate()));
                    }
                }
                String remindType3=bus.getScDiag().getThirdRemindType();
                if(StringUtils.isNotBlank(remindType3)){
                    if(generalForMap.containsKey(remindType3)){
                        bus.getScDiag().setThirdRemindType(generalForMap.get(remindType3).getName()+ DateUtils.formatDate(bus.getScDiag().getThirdRemindDate()));
                    }
                }
            }
            if(bus.getScRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getScRegcase().getCheckResult())){
                    bus.getScRegcase().setCheckResult(map.get(bus.getScRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }




    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> updateCrcFobtRemind(CrcFobtRemind vo) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            busRemindDao.updateCrcFobtRemind(vo);
            return new AjaxReturn<Map<String, String>>(true, "操作成功");
        }
        return new AjaxReturn<Map<String, String>>(false, "操作失败");
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> updateCrcDiag(CrcDiagCheckRemind vo) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            busRemindDao.updateCrcDiag(vo);
            return new AjaxReturn<Map<String, String>>(true, "操作成功");
        }
        return new AjaxReturn<Map<String, String>>(false, "操作失败");
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> updateLicDiag(LicDiagCheckRemind vo) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            busRemindDao.updateLicDiag(vo);
            return new AjaxReturn<Map<String, String>>(true, "操作成功");
        }
        return new AjaxReturn<Map<String, String>>(false, "操作失败");
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> updateScDiag(ScDiagCheckRemind vo) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            busRemindDao.updateScDiag(vo);
            return new AjaxReturn<Map<String, String>>(true, "操作成功");
        }
        return new AjaxReturn<Map<String, String>>(false, "操作失败");
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> updateLucDiag(LucDiagCheckRemind vo) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            busRemindDao.updateLucDiag(vo);
            return new AjaxReturn<Map<String, String>>(true, "操作成功");
        }
        return new AjaxReturn<Map<String, String>>(false, "操作失败");
    }
}