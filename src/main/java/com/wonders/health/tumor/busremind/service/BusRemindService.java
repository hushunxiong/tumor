/**
 * 
 */
package com.wonders.health.tumor.busremind.service;

import com.wonders.health.tumor.busremind.dao.BusRemindDao;
import com.wonders.health.tumor.busremind.vo.BusRemindResultVo;
import com.wonders.health.tumor.busremind.vo.BusRemindSearchVo;
import com.wonders.health.tumor.closingcase.dao.CrcClosingCaseDao;
import com.wonders.health.tumor.closingcase.entity.CrcClosingCase;
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

    public DataGrid<CancerPersonInfo> findPersoninfo(BusRemindSearchVo search) {

        if(StringUtils.isNotBlank(search.getEndDate())){
            search.setEndDate(search.getEndDate()+" 23:59:59");
        }
        int pageindex=search.getPageIndex();
        int pageSize=search.getPageSize();

        search.setPageIndex(0);
        search.setPageSize(999999999);
        List<BusRemindResultVo> pagelist = busRemindDao.getBasic(search);

        search.setPageIndex(pageindex);
        search.setPageSize(pageSize);
        List<BusRemindResultVo> list = busRemindDao.getBasic(search);

        List<CancerPersonInfo> reslist= new ArrayList<>();
        reslist=list.stream().map(bus->{return bus.getCancerPersonInfo();}).collect(Collectors.toList());

        return new DataGrid<CancerPersonInfo>(pagelist.size(),reslist);
    }


    public  DataGrid<BusRemindResultVo>getCrcFobtRemind(String personcard,String status,String year) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = new ArrayList<>();
        list= busRemindDao.getCrcFobtRemind(personcard, status,year);
        list.stream().forEach(bus->{
            if(bus!=null && bus.getFobtR()!=null){
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

                if(bus.getCrcFobt()!=null){
                    if(StringUtils.isNotBlank(bus.getCrcFobt().getFirstFobtResult())){
                        if(bus.getCrcFobt().getFirstFobtResult()=="1" || "1".equals(bus.getCrcFobt().getFirstFobtResult())){
                            bus.getCrcFobt().setFirstFobtResult("阴性");
                        }else{
                            bus.getCrcFobt().setFirstFobtResult("阳性");
                        }
                    }

                    if(StringUtils.isNotBlank(bus.getCrcFobt().getSecondFobtResult())){
                        if(bus.getCrcFobt().getSecondFobtResult()=="1" || "1".equals(bus.getCrcFobt().getSecondFobtResult())){
                            bus.getCrcFobt().setSecondFobtResult("阴性");
                        }else{
                            bus.getCrcFobt().setSecondFobtResult("阳性");
                        }
                    }
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getCrcDiag(String personcard,String status,String crcFlag,String year) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = new ArrayList<>();
        list= busRemindDao.getCrcDiag(personcard, status,crcFlag,null,null,null,year,null,null,null);
        list.stream().forEach(bus->{
            if(bus!=null && bus.getCrcDiag()!=null){
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
            if(bus!=null && bus.getCrcRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getCrcRegcase().getCheckResult())){
                    bus.getCrcRegcase().setCheckResult(map.get(bus.getCrcRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getLicDiag(String personcard,String status,String licFlag,String year) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = new ArrayList<>();

        list= busRemindDao.getLicDiag(personcard, status,null,licFlag,null,null,year,null,null,null);

        BusRemindResultVo bb=new BusRemindResultVo();
        LicDiagCheckRemind li=new LicDiagCheckRemind();
        bb.setLicDiag(li);
        LicRegcase lr=new LicRegcase();
        bb.setLicRegcase(lr);

        if(list.size()==0){
            list.add(bb);
        }else if(list.get(0)==null){
            list.add(bb);
        }else{
            list.stream().forEach(bus->{
                if(bus!=null && bus.getLicDiag()!=null){
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
                if(bus!=null && bus.getLicRegcase()!=null){
                    Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                    if(StringUtils.isNotBlank(bus.getLicRegcase().getCheckResult())){
                        bus.getLicRegcase().setCheckResult(map.get(bus.getLicRegcase().getCheckResult()).getName());
                    }
                }
            });
        }

        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getLucDiag(String personcard,String status,String lucFlag,String year) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list= new ArrayList<>();
        list=busRemindDao.getLucDiag(personcard,status,null,null,null,lucFlag,year,null,null,null);
        list.stream().forEach(bus->{
            if(bus!=null && bus.getLucDiag()!=null){
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
            if(bus!=null && bus.getLucRegcase()!=null){
                Map<String, CancerDic> map=DictUtils.generalForMap("60001");
                if(StringUtils.isNotBlank(bus.getLucRegcase().getCheckResult())){
                    bus.getLucRegcase().setCheckResult(map.get(bus.getLucRegcase().getCheckResult()).getName());
                }
            }
        });
        return new DataGrid<BusRemindResultVo>(list.size(),list);
    }

    public  DataGrid<BusRemindResultVo>getScDiag(String personcard,String status,String scFlag,String year) {
        Map<String, CancerDic> generalForMap=DictUtils.generalForMap("60047");

        List<BusRemindResultVo> list = new ArrayList<>();
        list=busRemindDao.getScDiag(personcard, status,null,null,scFlag,null,year,null,null,null);
        list.stream().forEach(bus->{
            if(bus!=null && bus.getScDiag()!=null){
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
            if(bus!=null && bus.getScRegcase()!=null){
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