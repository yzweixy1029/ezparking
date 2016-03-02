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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 停车位模块业务逻辑层
 * <p>
 * Created by WeiXY on 2016/2/22.
 */

@Service(value = "parkingService")
public class ParkingServiceImpl implements BaseService<Parking> {

    @Autowired
    private ParkingDAOImpl parkingDAO;
    @Autowired
    private RecordDAOImpl recordDAO;
    @Autowired
    private PriceDAOImpl priceDAO;

    public Parking getParking(Integer id) {
        return parkingDAO.getEntity(id);
    }


    /**
     * 获取所有停车位
     *
     * @return
     */
    public List<Parking> getParkings() {
        return parkingDAO.getAllParkings();
    }


    /**
     * 返回按区域划分好的车位列表
     *
     * @return
     */
    public Map<String, List<Parking>> getOrderedParkings() {
        List<Parking> parkings;
        Map<String, List<Parking>> orderedParkings = new HashMap<>();
        List<String> regions = parkingDAO.getAllRegions();
        for (String s : regions) {
            parkings = new ArrayList<>();
            parkings = parkingDAO.getParkingsByCol(s);
            orderedParkings.put(s, parkings);
        }

        return orderedParkings;

    }

    /**
     * 返回所有停车位区域
     *
     * @return
     */
    public List<String> getAllRegions() {
        return parkingDAO.getAllRegions();
    }


    /**
     * 获取指定区域的停车位
     *
     * @param col
     * @return
     */
    public List<Parking> getParkingsByCol(String col) {
        return parkingDAO.getParkingsByCol(col);
    }


    /**
     * 在指定区域增加停车位
     *
     * @param col
     */
    public void addParking(String col) {
        List<Parking> parkings = parkingDAO.getParkingsByCol(col);

        String lastID = parkings.get(parkings.size() - 1).getParkingID();
        Integer lastNum = Integer.valueOf(lastID.substring(1));
        Parking newParking = new Parking();
        newParking.setParkingID(col + String.valueOf(++lastNum));
        newParking.setStatus(1);
        newParking.setRegion(col);
        parkingDAO.saveEntity(newParking);

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
     * 返回停车位情况
     *
     * @return
     */
    public Map<String, Integer> checkStatus() {
        Integer freeParkingNum = parkingDAO.getFreeParkingsNum();
        Integer occupiedParkingNum = parkingDAO.getOccupiedParkingNum();
        Integer overhauledParkingNum = parkingDAO.getOverhauledParkingNum();
        Map<String, Integer> parkingStatus = new HashMap<>();
        parkingStatus.put("freeNum", freeParkingNum);
        parkingStatus.put("occupiedNum", occupiedParkingNum);
        parkingStatus.put("overhauledNum", overhauledParkingNum);
        return parkingStatus;
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
        selectedParking.setStatus(2);
        selectedParking.setPlateNumber(plateNumber);
        parkingDAO.saveOrUpdateEntity(selectedParking);

        //创建停车记录
        Record record = new Record();
        record.setParkingID(selectedParking.getParkingID());
        record.setPlateNumber(plateNumber);
        record.setStartTime(new Date(System.currentTimeMillis()));
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
        occupiedParking.setStatus(1);
        occupiedParking.setPlateNumber("");
        parkingDAO.saveOrUpdateEntity(occupiedParking);


        //更新停车记录
        Record record = recordDAO.getUnsettledRecordByPlateNumber(plateNumber);
        record.setEndTime(new Date(System.currentTimeMillis()));
        //计算停车价格
        long parkMillis = System.currentTimeMillis() - record.getStartTime().getTime();
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
