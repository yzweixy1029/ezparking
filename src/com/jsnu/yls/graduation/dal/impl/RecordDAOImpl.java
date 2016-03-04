package com.jsnu.yls.graduation.dal.impl;

import com.jsnu.yls.graduation.dal.BaseDAO;
import com.jsnu.yls.graduation.persistence.entities.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 停车记录模块持久层
 *
 * Created by WeiXY on 2016/2/22.
 */
@Repository
public class RecordDAOImpl extends BaseDAOImpl<Record> implements BaseDAO<Record> {

    /**
     * 获取指定车牌的未结停车记录
     *
     * @param plateNumber
     * @return
     */
    public Record getUnsettledRecordByPlateNumber(String plateNumber) {
        String jpql = "SELECT r FROM Record r WHERE r.plateNumber=?1 AND END_TIME is null";
        return (Record) this.uniqueResult(jpql, plateNumber);
    }

    /**
     * 获取所有停车单
     *
     * @return
     */
    public List<Record> getAllRecords() {
        String jpql = "FROM Record";
        return this.getEntitiesByJPQL(jpql);
    }

    /**
     * 重载排序方法
     *
     * @param order
     * @return
     */
    public List<Record> getAllRecords(String order) {
        String jpql = "FROM Record r ORDER BY r." + order;
        return this.getEntitiesByJPQL(jpql);
    }

    /**
     * 获取所有已结账停车单
     *
     * @return
     */
    public List<Record> getSettledRecords() {
        String jpql = "SELECT r FROM Record r WHERE r.cost!=0";
        return this.getEntitiesByJPQL(jpql);
    }


}
