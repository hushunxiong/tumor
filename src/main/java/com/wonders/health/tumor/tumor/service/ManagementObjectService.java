package com.wonders.health.tumor.tumor.service;

import com.wonders.health.auth.client.vo.Hospital;
import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.tumor.dao.LucAppLdctXhDao;
import com.wonders.health.tumor.tumor.dao.LucRegcaseDao;
import com.wonders.health.tumor.tumor.dao.LucRiskAssessmentDao;
import com.wonders.health.tumor.tumor.entity.LucAppLdctXh;
import com.wonders.health.tumor.tumor.entity.LucRegcase;
import com.wonders.health.tumor.tumor.entity.LucRiskAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理对象Service
 *
 * @author wangyixian
 */
@Service
@Transactional(readOnly = true)
public class ManagementObjectService {


    @Autowired
    private LucRegcaseDao lucRegcaseDao;

    @Autowired
    private LucRiskAssessmentDao lucRiskAssessmentDao;

    @Autowired
    private LucAppLdctXhDao lucAppLdctXhDao;

    public List<LucRegcase> getLucRegcaseListByManageId(String manageId) {
        List<LucRegcase> result = new ArrayList<>();
        List<LucRegcase> list = lucRegcaseDao.getByManageId(manageId);
        for (LucRegcase item : list) {
            // 管理医疗机构的获取
            Hospital hos = AuthUtils.getHospitalByCode(item.getRegorg());
            if (hos != null) {
                item.setRegorg(hos.getName());
            }

            LucRiskAssessment lucRiskAssessment = lucRiskAssessmentDao.getByCheckid(item.getId());
            if (lucRiskAssessment != null) {
                item.setLucRiskAssessment(lucRiskAssessment);
                result.add(item);
            }
        }
        return result;
    }

    public List<LucAppLdctXh> getLucAppLdctXhListByManageId(String manageId) {
        List<LucAppLdctXh> list = lucAppLdctXhDao.findByManageId(manageId);
        for (LucAppLdctXh item : list) {
            // 管理医疗机构的获取
            Hospital hos = AuthUtils.getHospitalByCode(item.getAppointmentOrg());
            if (hos != null) {
                item.setAppointmentOrg(hos.getName());
            }

            // 预约医生
            User doctor = AuthUtils.getUserById(item.getAppointmentDoc());
            if (doctor != null){
                item.setAppointmentDoc(doctor.getName());
            }
        }
        return list;
    }
}
