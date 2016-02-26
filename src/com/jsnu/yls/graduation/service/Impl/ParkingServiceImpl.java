package com.jsnu.yls.graduation.service.Impl;

import com.jsnu.yls.graduation.dal.impl.ParkingDAOImpl;
import com.jsnu.yls.graduation.dal.impl.PriceDAOImpl;
import com.jsnu.yls.graduation.dal.impl.RecordDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Parking;
import com.jsnu.yls.graduation.persistence.entities.Price;
import com.jsnu.yls.graduation.persistence.entities.Record;
import com.jsnu.yls.graduation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 停车位业务逻辑层
 * <p>
 * Created by chenwei on 2016/2/22.
 */

@Service(value = "parkingService")
public class ParkingServiceImpl implements BaseService<Parking> {

    @Autowired
    private ParkingDAOImpl parkingDAO;
    @Autowired
    private RecordDAOImpl recordDAO;
    @Autowired
    private PriceDAOImpl priceDAO;

    /**
     * 在指定区域增加停车位
     *
     * @param col
     */
    public void addParking(String col) {
        List<Parking> parkings = parkingDAO.getParkingsByCol(col);
        if (parkings.size() == 0) {
            Parking newParking = new Parking();
            newParking.setParkingID(col + "1");
            newParking.setStatu(1);
            parkingDAO.saveEntity(newParking);
        } else {
            String lastID = parkings.get(parkings.size() - 1).getParkingID();
            Integer lastNum = Integer.valueOf(lastID.substring(1));
            Parking newParking = new Parking();
            newParking.setParkingID(col + String.valueOf(++lastNum));
            newParking.setStatu(1);
            parkingDAO.saveEntity(newParking);
        }

    }

    /**
     * 删除车位
     *
     * @param ID
     */
    public void dropParking(Integer ID) {
        parkingDAO.deleteEntity(new Parking(ID));
    }


    /**
     * 模拟停车操作
     *
     * @param plateNumber
     */
    public void park(String plateNumber) {
        List<Parking> freeParkings = parkingDAO.getFreeParkings();

        Random random = new Random();
        int parkingID = random.nextInt(freeParkings.size());
        //随机选择的此次停车的车位
        Parking selectedParking = freeParkings.get(parkingID);

        //更新停车位状态
        selectedParking.setStatu(2);
        selectedParking.setPlateNumber(plateNumber);
        parkingDAO.saveOrUpdateEntity(selectedParking);

        //创建停车记录
        Record record = new Record();
        record.setParkingID(selectedParking.getParkingID());
        record.setPlateNumber(plateNumber);
        record.setStartTime(System.currentTimeMillis());
        recordDAO.saveEntity(record);

    }

    /**
     * 模拟取车操作
     *
     * @param plateNumber
     */
    public void leave(String plateNumber) {
        Parking occupiedParking = parkingDAO.getParkingByPlateNumber(plateNumber);

        //更新停车位状态
        occupiedParking.setStatu(1);
        occupiedParking.setPlateNumber("");
        parkingDAO.saveOrUpdateEntity(occupiedParking);


        //更新停车记录
        Record record = recordDAO.getUnsettledRecordByPlateNumber(plateNumber);
        record.setEndTime(System.currentTimeMillis());
        //计算停车价格
        long parkMillis = System.currentTimeMillis() - record.getStartTime();
        long parkHours = 0;
        if (parkMillis % (60 * 60 * 1000) == 0) {
            parkHours = parkMillis / (60 * 60 * 1000);
        } else {
            //不满1小时按1小时算
            parkHours = parkMillis / (60 * 60 * 1000) + 1;
        }
        //获取当前单价
        Price price = priceDAO.getEntity(1);
        float cost = price.getPrice() * parkHours;
        record.setCost(cost);
        recordDAO.saveOrUpdateEntity(record);

    }


}
