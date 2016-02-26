package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 停车位Handler
 *
 * Created by chenwei on 2016/2/22.
 */
@Controller
public class ParkingHandler {

    @Autowired
    private ParkingServiceImpl parkingService;

}
