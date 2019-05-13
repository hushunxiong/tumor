package com.wonders.health.tumor.common.dao;

import com.wonders.health.tumor.common.entity.CancerDic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/4/16.
 */
@Mapper
@Repository
public interface CancerDicDao {
    List<CancerDic> getDictsById(@Param("id") String id);
}
