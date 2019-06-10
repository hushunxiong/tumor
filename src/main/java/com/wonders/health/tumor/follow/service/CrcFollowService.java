package com.wonders.health.tumor.follow.service;

import com.wonders.health.auth.client.vo.User;
import com.wonders.health.tumor.common.model.AjaxReturn;
import com.wonders.health.tumor.common.model.BaseEntity;
import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.common.utils.DateUtils;
import com.wonders.health.tumor.common.utils.IdGen;
import com.wonders.health.tumor.common.utils.StringUtils;
import com.wonders.health.tumor.follow.dao.CrcFollowDao;
import com.wonders.health.tumor.follow.entity.CrcFollow;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.dao.CancerDicHospitalInfoDao;
import com.wonders.health.tumor.tumor.dao.CrcRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 大肠癌随访Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class CrcFollowService {

    @Autowired
    private CrcRegcaseDao crcRegcaseDao;
    @Autowired
    private CrcFollowDao crcFollowDao;
    @Autowired
    private CancerDicHospitalInfoDao cancerDicHospitalInfoDao;

    private String[] IGNORE_FIELD = {"id", "primaryMark", "dengjijigouId", "dengjiDate", "dengjiId"};

    public CrcRegcase getRegcase(String manageId, String checkYear) {
        CrcRegcase regcase = crcRegcaseDao.getByManageidAndYear(manageId, checkYear);
        return regcase;
    }

    public CrcRegcase getRegcaseById(String id) {
        CrcRegcase regcase = crcRegcaseDao.get(id);
        return regcase;
    }

    /**
     * 大肠癌随访记录取得推出诊断状态
     * @return
     */
    public String getDiagStatus(String checkId) {
        String status = "";
        FollowSearchVo searchVo = new FollowSearchVo();
        searchVo.setCrcCheckId(checkId);

        DataGrid<CrcFollow> grid = getFollowList(searchVo);
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
    public DataGrid<CrcFollow> getFollowList(FollowSearchVo searchVo) {
        List<CrcFollow> list = crcFollowDao.getListByCheckId(searchVo.getCrcCheckId());
        if (list != null && list.size() > 0) {
            for (CrcFollow follow : list) {
                //随访医生名
                follow.setSuifangyishengName(AuthUtils.getUserById(follow.getSuifangyishengId()).getName());
            }
        }
        long size = list != null ? list.size() : 0;
        return new DataGrid<CrcFollow>(size,list);
    }

    @Transactional(readOnly = false)
    public AjaxReturn<Map<String, String>> saveOrUpdate(CrcFollow vo, User user) {
        if (vo != null) {

            if (StringUtils.isNotBlank(vo.getId())) {
                //修改
                CrcFollow po = crcFollowDao.get(vo.getId());
                if (po != null) {
                    String[] all_ignore = ArrayUtils.addAll(BaseEntity.IGNORES, IGNORE_FIELD);
                    BeanUtils.copyProperties(vo, po, all_ignore);
                    po.initByUpdate(user.getId());
                    //手术医疗机构名称
                    po.setShoushujigouName(cancerDicHospitalInfoDao.get(po.getShoushujigouId()).getHospitalName());
                    //放疗医疗机构名称
                    po.setFangliaojigouName(cancerDicHospitalInfoDao.get(po.getFangliaojigouId()).getHospitalName());
                    //化疗医疗机构名称
                    po.setHualiaojigouName(cancerDicHospitalInfoDao.get(po.getHualiaojigouId()).getHospitalName());
                    //肠镜医疗机构名称
                    po.setChangjingjigouName(cancerDicHospitalInfoDao.get(po.getChangjingjigouId()).getHospitalName());
                    //诊断医疗机构名称
                    po.setZhenduanjigouName(cancerDicHospitalInfoDao.get(po.getZhenduanjigouId()).getHospitalName());
                    //随访医疗机构名称
                    po.setSuifangjigouName(cancerDicHospitalInfoDao.get(po.getSuifangjigouId()).getHospitalName());
                    crcFollowDao.update(po);
                }

                return new AjaxReturn<Map<String, String>>(true, "修改成功");

            } else {
                //新增
                CrcFollow po = new CrcFollow();
                BeanUtils.copyProperties(vo, po, BaseEntity.IGNORES);
                po.init(user.getId());
                //主键
                po.setId(IdGen.uuid());
                //随访编号
                po.setPrimaryMark(user.getOrgCode() + DateUtils.formatDate(new Date(), "yyyyMMddHHmm"));

                //手术医疗机构名称
                po.setShoushujigouName(cancerDicHospitalInfoDao.get(po.getShoushujigouId()).getHospitalName());
                //放疗医疗机构名称
                po.setFangliaojigouName(cancerDicHospitalInfoDao.get(po.getFangliaojigouId()).getHospitalName());
                //化疗医疗机构名称
                po.setHualiaojigouName(cancerDicHospitalInfoDao.get(po.getHualiaojigouId()).getHospitalName());
                //肠镜医疗机构名称
                po.setChangjingjigouName(cancerDicHospitalInfoDao.get(po.getChangjingjigouId()).getHospitalName());
                //诊断医疗机构名称
                po.setZhenduanjigouName(cancerDicHospitalInfoDao.get(po.getZhenduanjigouId()).getHospitalName());
                //随访医疗机构名称
                po.setSuifangjigouName(cancerDicHospitalInfoDao.get(po.getSuifangjigouId()).getHospitalName());

                //登记医疗机构编码
                po.setDengjijigouId(user.getOrgCode());
                //登记日期
                po.setDengjiDate(new Date());
                //登记人ID
                po.setDengjiId(user.getId());
                crcFollowDao.insert(po);

                return new AjaxReturn<Map<String, String>>(true, "保存成功");
            }
        } else {
            return new AjaxReturn<Map<String, String>>(false, "画面数据提交失败");
        }
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        crcFollowDao.delete(id);
    }

    public CrcFollow getFollowById(String id) {
        return crcFollowDao.get(id);
    }
}
