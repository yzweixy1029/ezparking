package com.jsnu.yls.graduation.persistence.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * 停车记录实体类
 *
 * Created by WeiXY on 2016/2/22.
 */

@Entity
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //停车位ID
    @Column(name = "PARKING_ID")
    @NotEmpty
    private String parkingID;

    //此次停车消费
    @Column(name = "COST")
    @NotEmpty
    private float cost;

    //停车开始时间
    @Column(name = "START_TIME")
    @NotEmpty
    private Date startTime;

    //停车结束时间
    @Column(name = "END_TIME")
    @NotEmpty
    private Date endTime;

    //车牌号
    @Column(name = "PLATE_NUMBER")
    @NotEmpty
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", parkingID='" + parkingID + '\'' +
                ", cost=" + cost +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
