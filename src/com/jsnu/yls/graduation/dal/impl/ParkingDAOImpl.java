package com.jsnu.yls.graduation.dal.impl;

import com.jsnu.yls.graduation.dal.BaseDAO;
import com.jsnu.yls.graduation.persistence.entities.Parking;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 停车位模块持久层
 * <p>
 * Created by WeiXY on 2016/2/22.
 */

@Repository(value = "parkingDAO")
public class ParkingDAOImpl extends BaseDAOImpl<Parking> implements BaseDAO<Parking> {

    /**
     * 修改停车位状态
     *
     * @param id
     * @param statu
     */
    public void changeParkingStatu(Integer id, Integer statu) {
        Parking parking = this.getEntity(id);
        parking.setStatus(statu);
        this.saveOrUpdateEntity(parking);
    }

    /**
     * 获取所有停车位
     *
     * @return
     */
    public List<Parking> getAllParkings() {
        String jpql = "FROM Parking";
        return this.getEntitiesByJPQL(jpql);
    }

    /**
     * 获取所有空闲车位
     *
     * @return
     */
    public List<Parking> getFreeParkings() {
        String jpql = "SELECT p FROM Parking p WHERE p.status=1";
        return this.getEntitiesByJPQL(jpql);
    }

    /**
     * 获取空闲车位数量
     *
     * @return
     */
    public Integer getFreeParkingsNum() {
        String jpql = "SELECT COUNT(p) FROM Parking p WHERE p.status=1";
        Long res = (Long) this.uniqueResult(jpql);
        return Integer.valueOf(String.valueOf(res));
    }

    /**
     * 获取占用车位数量
     *
     * @return
     */
    public Integer getOccupiedParkingNum() {
        String jpql = "SELECT COUNT(p) FROM Parking p WHERE p.status=2";
        Long res = (Long) this.uniqueResult(jpql);
        return Integer.valueOf(String.valueOf(res));
    }

    /**
     * 获取检修中车位数量
     *
     * @return
     */
    public Integer getOverhauledParkingNum(){
        String jpql = "SELECT COUNT(p) FROM Parking p WHERE p.status=3";
        Long res = (Long) this.uniqueResult(jpql);
        return Integer.valueOf(String.valueOf(res));
    }

    /**
     * 获取所有已占用车位
     *
     * @return
     */
    public List<Parking> getOccupiedParkings() {
        String jpql = "SELECT p FROM Parking p WHERE p.status=2";
        return this.getEntitiesByJPQL(jpql);
    }

    /**
     * 根据车牌号获取此已占用的车位
     *
     * @param plateNumber
     * @return
     */
    public Parking getParkingByPlateNumber(String plateNumber) {
        String jpql = "SELECT p FROM Parking p WHERE p.plateNumber=?1";
        return (Parking) this.uniqueResult(jpql, plateNumber);
    }

    /**
     * 根据区域取停车位
     *
     * @param col
     * @return
     */
    public List<Parking> getParkingsByCol(String col) {
        String jpql = "SELECT p FROM Parking p WHERE p.region=?1";
        return this.findEntityByHQL(jpql, col);
    }

    /**
     * 获取所有区域
     *
     * @return
     */
    public List<String> getAllRegions(){
        String sql = "SELECT distinct a.region FROM PARKING as a";
        return this.executeSQLQuery(sql);
    }


}
