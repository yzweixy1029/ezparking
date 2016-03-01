package com.jsnu.yls.graduation.persistence.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 停车位实体类
 *
 * Created by WeiXY on 2016/2/22.
 */

@Entity
@Table(name = "PARKING")
public class Parking {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    //停车位ID
    @Column(name = "PARKING_ID")
    @NotEmpty
    private String parkingID;

    /**
     * 停车位状态码
     * 1=>空闲
     * 2=>占用
     * 3=>检修
     */
    @Column(name = "STATUS")
    @NotNull
    private Integer status;

    //车牌号
    @Column(name = "PLATE_NUMBER")
    private String plateNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParkingID() {
        return parkingID;
    }

    public void setParkingID(String parkingID) {
        this.parkingID = parkingID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Parking() {
    }

    public Parking(Integer id) {
        this.id = id;
    }
}
