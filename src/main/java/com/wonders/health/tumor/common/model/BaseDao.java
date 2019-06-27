package com.wonders.health.tumor.common.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuguobing on 2016/11/1 0001.
 */
public interface BaseDao<T> {

//    public T get(String id);

    public T get(T entity);

    public int insert(T entity);

    public int update(T entity);

//    public int delete(String id);

    public List<T> findAll();

    public int pageCount(DataGridSearch search);

    public List<T> pageList(DataGridSearch search);

    public T getByManageidAndYear(@Param("manageid") String manageid, @Param("checkyear") String checkyear);

    public T getByCheckid(@Param("checkid")String checkid);

    public List<T> getListByManageidAndYear(@Param("manageid") String manageid, @Param("checkyear") String checkyear);

    public List<String> checkIdnumber(@Param("manageid") String manageid, @Param("idnumber") String idnumber);

    public List<T> getListByPersonId(@Param("personId")String personId);

    public void deleteAllByPersonId(@Param("personId")String personId);
}
