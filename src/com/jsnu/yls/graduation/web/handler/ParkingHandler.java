package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.persistence.entities.Parking;
import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 停车位模块控制层
 * <p>
 * Created by WeiXY on 2016/2/22.
 */
@Controller
public class ParkingHandler {

    @Autowired
    private ParkingServiceImpl parkingService;

    @ModelAttribute
    public void getParking(@RequestParam(value = "id", required = false) Integer id,
                           Map<String, Object> map) {
        if (id != null) {
            map.put("parking", parkingService.getParking(id));
        }
    }


    /**
     * 车位列表页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/listParking")
    public String parkingList(Map<String, Object> map) {
        map.put("parkingList", parkingService.getOrderedParkings());
        map.put("parkingRegion", parkingService.getAllRegions());
        return "parking/parking_list";
    }


    /**
     * ajax增加停车位,并返回当前区域的车位
     *
     * @param col
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addParking/{col}")
    public List<Parking> addParking(@PathVariable String col) {
        parkingService.addParking(col.toUpperCase());
        return parkingService.getParkingsByCol(col);
    }


    /**
     * ajax删除停车位，并返回当前区域的车位
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dropParking/{id}")
    public List<Parking> dropParking(@PathVariable Integer id) {
        Parking parking = parkingService.getParking(id);
        parkingService.dropParking(id);
        return parkingService.getParkingsByCol(parking.getRegion());
    }

    /**
     * 模拟停车操作
     *
     * @param plateNumber
     * @return
     */
    @RequestMapping(value = "/park/{plateNumber}")
    public String park(@PathVariable String plateNumber) {
        try {
            String plateNumberCN = new String(plateNumber.getBytes("iso8859-1"), "utf-8");

            parkingService.park(plateNumber);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "redirect:/listParking";

    }

    /**
     * 模拟离开操作
     *
     * @param plateNumber
     * @return
     */
    @RequestMapping(value = "/leave/{plateNumber}")
    public String leave(@PathVariable String plateNumber) {
        try {
            String plateNumberCN = new String(plateNumber.getBytes("iso8859-1"), "utf-8");

            parkingService.leave(plateNumber);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "redirect:/listParking";
    }


}
