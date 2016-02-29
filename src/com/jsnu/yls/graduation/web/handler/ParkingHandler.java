package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.persistence.entities.Parking;
import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 停车位Handler
 * <p>
 * Created by chenwei on 2016/2/22.
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
     * ajax增加停车位
     *
     * @param col
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addParking/{col}")
    public List<Parking> addParking(@PathVariable String col){
        parkingService.addParking(col.toUpperCase());
        return parkingService.getParkings();
    }

    /**
     * ajax删除停车位
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dropParking/{id}")
    public List<Parking> dropParking(@PathVariable Integer id){
        parkingService.dropParking(id);
        return parkingService.getParkings();
    }



}
