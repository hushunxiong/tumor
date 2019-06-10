package com.wonders.health.tumor.follow.service;

import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.dao.LicFollowDao;
import com.wonders.health.tumor.follow.entity.LicFollow;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.dao.CancerDicHospitalInfoDao;
import com.wonders.health.tumor.tumor.dao.LicRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CancerDicHospitalInfo;
import com.wonders.health.tumor.tumor.entity.LicRegcase;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 肝癌随访Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class LicFollowService {
    @Autowired
    private LicRegcaseDao licRegcaseDao;
    @Autowired
    private LicFollowDao licFollowDao;
    @Autowired
    private CancerDicHospitalInfoDao cancerDicHospitalInfoDao;

    private String[] IGNORE_FIELD = {"id", "primaryMark", "dengjijigouId", "dengjiDate", "dengjiId"};

    public LicRegcase getRegcase(String manageId, String checkYear) {
        LicRegcase regcase = licRegcaseDao.getByManageidAndYear(manageId, checkYear);
        return regcase;
    }

    public LicRegcase getRegcaseById(String id) {
        LicRegcase regcase = licRegcaseDao.get(id);
        return regcase;
    }

    /**
     * 肝癌随访记录取得推出诊断状态
     * @return
     */
    public String getDiagStatus(String checkId) {
        String status = "";
        FollowSearchVo searchVo = new FollowSearchVo();
        searchVo.setLicCheckId(checkId);

        DataGrid<LicFollow> grid = getFollowList(searchVo);
        long size = grid != null ? grid.getTotal() : 0;
        if (size > 0) {
            status = "已诊断";
        } else {
            status = "未诊断";
        }
        return status;
    }

    /**
     * 大肠癌随访列表取得
     * @return
     */
    public DataGrid<LicFollow> getFollowList(FollowSearchVo searchVo) {
        List<LicFollow> list = licFollowDao.getListByCheckId(searchVo.getLicCheckId());
        if (list != null && list.size() > 0) {
            for (LicFollow follow : list) {
                //随访医生名
                follow.setSuifangyishengName(AuthUtils.getUserById(follow.getSuifangyishengId()).getName());
            }
        }
        long size = list != null ? list.size() : 0;
        return new DataGrid<LicFollow>(size,list);
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        licFollowDao.delete(id);
    }

    public LicFollow getFollowById(String id) {
        return licFollowDao.get(id);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(LicFollow vo, User user) {
        if (vo != null) {

            if (StringUtils.isNotBlank(vo.getId())) {
                //修改
                LicFollow po = licFollowDao.get(vo.getId());
                if (po != null) {
                    String[] all_ignore = ArrayUtils.addAll(BaseEntity.IGNORES, IGNORE_FIELD);
                    BeanUtils.copyProperties(vo, po, all_ignore);
                    po.initByUpdate(user.getId());
                    //手术医疗机构名称
                    if (StringUtils.isNotBlank(po.getShoushujigouId())) {
                        CancerDicHospitalInfo shoushujigou = cancerDicHospitalInfoDao.get(po.getShoushujigouId());
                        if (shoushujigou != null) {
                            po.setShoushujigouName(shoushujigou.getHospitalName());
                        }
                    }
                    //放疗医疗机构名称
                    if (StringUtils.isNotBlank(po.getFangliaojigouId())) {
                        CancerDicHospitalInfo fangliaojigou = cancerDicHospitalInfoDao.get(po.getFangliaojigouId());
                        if (fangliaojigou != null) {
                            po.setFangliaojigouName(fangliaojigou.getHospitalName());
                        }
                    }
                    //化疗医疗机构名称
                    if (StringUtils.isNotBlank(po.getHualiaojigouId())) {
                        CancerDicHospitalInfo hualiaojigou = cancerDicHospitalInfoDao.get(po.getHualiaojigouId());
                        if (hualiaojigou != null) {
                            po.setHualiaojigouName(hualiaojigou.getHospitalName());
                        }
                    }

                    //诊断医疗机构名称
                    if (StringUtils.isNotBlank(po.getZhenduanjigouId())) {
                        CancerDicHospitalInfo zhenduanjigou = cancerDicHospitalInfoDao.get(po.getZhenduanjigouId());
                        if (zhenduanjigou != null) {
                            po.setZhenduanjigouName(zhenduanjigou.getHospitalName());
                        }
                    }
                    //随访医疗机构名称
                    if (StringUtils.isNotBlank(po.getSuifangjigouId())) {
                        CancerDicHospitalInfo suifangjigou = cancerDicHospitalInfoDao.get(po.getSuifangjigouId());
                        if (suifangjigou != null) {
                            po.setSuifangjigouName(suifangjigou.getHospitalName());
                        }
                    }
                    licFollowDao.update(po);
                }

                return new AjaxReturn<Map<String, String>>(true, "修改成功");

            } else {
                //新增
                LicFollow po = new LicFollow();
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.init(user.getId());
                //主键
                po.setId(IdGen.uuid());
                //随访编号
                po.setPrimaryMark(user.getOrgCode() + DateUtils.formatDate(new Date(), "yyyyMMddHHmm"));

                //手术医疗机构名称
                if (StringUtils.isNotBlank(po.getShoushujigouId())) {
                    CancerDicHospitalInfo shoushujigou = cancerDicHospitalInfoDao.get(po.getShoushujigouId());
                    if (shoushujigou != null) {
                        po.setShoushujigouName(shoushujigou.getHospitalName());
                    }
                }
                //放疗医疗机构名称
                if (StringUtils.isNotBlank(po.getFangliaojigouId())) {
                    CancerDicHospitalInfo fangliaojigou = cancerDicHospitalInfoDao.get(po.getFangliaojigouId());
                    if (fangliaojigou != null) {
                        po.setFangliaojigouName(fangliaojigou.getHospitalName());
                    }
                }
                //化疗医疗机构名称
                if (StringUtils.isNotBlank(po.getHualiaojigouId())) {
                    CancerDicHospitalInfo hualiaojigou = cancerDicHospitalInfoDao.get(po.getHualiaojigouId());
                    if (hualiaojigou != null) {
                        po.setHualiaojigouName(hualiaojigou.getHospitalName());
                    }
                }
                //诊断医疗机构名称
                if (StringUtils.isNotBlank(po.getZhenduanjigouId())) {
                    CancerDicHospitalInfo zhenduanjigou = cancerDicHospitalInfoDao.get(po.getZhenduanjigouId());
                    if (zhenduanjigou != null) {
                        po.setZhenduanjigouName(zhenduanjigou.getHospitalName());
                    }
                }
                //随访医疗机构名称
                if (StringUtils.isNotBlank(po.getSuifangjigouId())) {
                    CancerDicHospitalInfo suifangjigou = cancerDicHospitalInfoDao.get(po.getSuifangjigouId());
                    if (suifangjigou != null) {
                        po.setSuifangjigouName(suifangjigou.getHospitalName());
                    }
                }
                //登记医疗机构编码
                po.setDengjijigouId(user.getOrgCode());
                //登记日期
                po.setDengjiDate(new Date());
                //登记人ID
                po.setDengjiId(user.getId());
                licFollowDao.insert(po);

                return new AjaxReturn<Map<String, String>>(true, "保存成功");
            }
        } else {
            return new AjaxReturn<Map<String, String>>(false, "画面数据提交失败");
        }
    }
}
