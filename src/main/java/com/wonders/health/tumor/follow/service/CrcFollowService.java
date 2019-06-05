package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.common.model.DataGrid;
import com.wonders.health.tumor.common.utils.AuthUtils;
import com.wonders.health.tumor.follow.dao.CrcFollowDao;
import com.wonders.health.tumor.follow.entity.CrcFollow;
import com.wonders.health.tumor.follow.vo.FollowSearchVo;
import com.wonders.health.tumor.tumor.dao.CrcRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public CrcRegcase getRegcase(String manageId, String checkYear) {
        CrcRegcase regcase = crcRegcaseDao.getByManageidAndYear(manageId, checkYear);
        return regcase;
    }

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
    public void deleteById(String id) {
        crcFollowDao.delete(id);
    }

    public CrcFollow getFollowById(String id) {
        return crcFollowDao.get(id);
    }
}
