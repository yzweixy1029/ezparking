package com.jsnu.yls.graduation.dal;

import java.util.List;

/**
 * 持久层接口
 *
 * Created by WeiXY on 2015/9/9.
 */
public interface BaseDAO<T> {

    //写
    void saveEntity(T t);

    void saveOrUpdateEntity(T t);

    void deleteEntity(T t);

    void batchEntityByHQL(String jpql, Object... objects);


    void executeSQL(String sql, Object... objects);

    //读
    T loadEntity(Integer id);

    T getEntity(Integer id);

    List<T> findEntityByHQL(String jpql, Object... objects);

    Object uniqueResult(String jpql, Object... objects);

    List executeSQLQuery(String sql, Object... objects);

    List<T> getEntitiesByJPQL(String jpgl);

}
