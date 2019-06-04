package com.wonders.health.tumor.follow.service;

import com.wonders.health.tumor.follow.dao.CrcFollowDao;
import com.wonders.health.tumor.tumor.dao.CrcRegcaseDao;
import com.wonders.health.tumor.tumor.entity.CrcRegcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 大肠癌随访Service
 * @author sunyang
 */
@Service
@Transactional(readOnly = true)
public class CrcFollowService {

    @Autowired
    private CrcRegcaseDao crcRegcaseDao;

    public CrcRegcase getRegcase(String manageId, String checkYear) {
        CrcRegcase regcase = crcRegcaseDao.getByManageidAndYear(manageId, checkYear);
        return regcase;
    }
}
