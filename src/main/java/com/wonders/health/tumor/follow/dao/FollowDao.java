package com.wonders.health.tumor.follow.dao;

import com.wonders.health.tumor.common.model.DataGridSearch;
import com.wonders.health.tumor.tumor.entity.CancerPersonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/5/31.
 */
@Mapper
@Repository
public interface FollowDao {
    public int pageCount(DataGridSearch search);

    public List<CancerPersonInfo> pageList(DataGridSearch search);
}
