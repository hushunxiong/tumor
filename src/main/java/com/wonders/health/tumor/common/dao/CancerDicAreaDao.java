package com.wonders.health.tumor.common.dao;

import com.wonders.health.tumor.common.entity.CancerDicArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */
@Mapper
@Repository
public interface CancerDicAreaDao {
    List<CancerDicArea> findAreas(@Param("level") String level, @Param("pcode") String pcode);

    List<CancerDicArea> findParent(@Param("code") String code);

    CancerDicArea get(@Param("ccode") String ccode);
}
