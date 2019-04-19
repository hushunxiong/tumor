/**
 * 
 */
package com.wonders.health.tumor.tumor.service;

import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.tumor.dao.CancerPersonInfoDao;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import com.wonders.healthcloud.archive.client.entity.PersonAddress;
import com.wonders.healthcloud.archive.client.entity.PersonInfo;
import com.wonders.healthcloud.archive.client.util.ArchiveUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 新增筛查登记Service
 * @author menglianghai
 */
@Service
@Transactional(readOnly = true)
public class ScreeningService {

    @Value("${healthArchive}")
    private String healthArchive;

    @Autowired
    private CancerPersonInfoDao cancerPersonInfoDao;

    //根据证件类型和证件号码获取基本信息
    public CancerPersonInfo getBaseInfoByCardnoAndType(String personcardno,String type) {
        Optional<CancerPersonInfo>cancerPersonInfo=Optional.of(cancerPersonInfoDao.getByCardnoAndYType(personcardno,type));
        if(!cancerPersonInfo.isPresent()){
            return cancerPersonInfo.get();
        }

        CancerPersonInfo result=new CancerPersonInfo();
        PersonInfo personInfo=ArchiveUtil.getArchive(healthArchive+ "/api/get", type, personcardno);
        if(personInfo!=null){
            BeanUtils.copyProperties(personInfo, result);

            result.setPersoncard(personcardno);
            result.setTelephone(personInfo.getPhone());
            result.setMobile(personInfo.getMobilePhone());
            result.setPaymentSituation(personInfo.getMedicalPaymentcode());
            result.setPaddressDetail(personInfo.getPaddressOther());

            List<PersonAddress> personAddressList = personInfo.getAddresses();
            if(personAddressList!=null && personAddressList.size() > 0 ){
                //居住地址
                personAddressList.stream().filter((personAddress) -> "1".equals(personAddress.getIscurrentuse()))
                        .forEach(personAddress -> {
                            result.setAddressProvince(personAddress.getProvince());
                            result.setAddressCity(personAddress.getCity());
                            result.setAddressCounty(personAddress.getCounty());
                            result.setAddressTown(personAddress.getTown());
                            result.setAddressCommittee(personAddress.getCommittee());
                            result.setAddressDetail(personAddress.getOther());
                        });
            }
        }
        return result;
    }




    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CancerPersonInfo vo, String userId) {
        if (vo != null && StringUtils.isNotBlank(vo.getId())) { //修改
            CancerPersonInfo po = cancerPersonInfoDao.get(vo.getId());
            if (po != null) {
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.initByUpdate(userId);
                cancerPersonInfoDao.update(po);
                return new AjaxReturn<Map<String, String>>(true, "修改成功");
            } else {
                return new AjaxReturn<Map<String, String>>(false, "传入ID无法找到记录");
            }
        } else { //新增
            vo.setId(IdGen.uuid());
            vo.init(userId);
            cancerPersonInfoDao.insert(vo);
            return new AjaxReturn<Map<String, String>>(true, "保存成功");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        cancerPersonInfoDao.delete(id);
    }

}